package AnimationsOperater;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Animations {
	/**
	 * 动画类，抽象整个动画
	 */
	private String name;
	private int type;
	private String description;
	private String version;
	private String id;
	private int unit;//0表示px,1表示%
	private int speed;//0表示匀速，1表示加速
	private List<TimePoint> timepoint = new ArrayList<TimePoint>();
	
	//构造方法
	public Animations(int speed){
		this.setSpeed(speed);
		this.addTimepoint(0, 0, 0, 0, 1,0);
		this.addTimepoint(100, 0, 0, 0, 1, 0);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	//判断某个时间点时候已经存在，返回其所在的下标，不存在返回-1
	public int isTimePointExist(int percent){
		for(int i=0;i<timepoint.size();i++){
			if(timepoint.get(i).getPercent()==percent){
				return i;
			}
		}
		return -1;
	}
	
	//添加一个时间点
	public void addTimepoint(int percent,int translateX,int translateY,int translateZ,int opacity,int unit){
		//System.out.println("Animations类addTimepoint"+unit+opacity);
		int index = this.isTimePointExist(percent);
		if(index>-1){
			TimePoint newTimePoint = new TimePoint(percent,translateX,translateY,translateZ,opacity,unit);
			timepoint.set(index, newTimePoint);
			return;
		}
		TimePoint newTimePoint = new TimePoint(percent,translateX,translateY,translateZ,opacity,unit);
		timepoint.add(newTimePoint);
	}
	
	//获得整个动画的文本
	public List<String> getText(){
		List<String> result = new ArrayList<String>();
		result.add("/*");
		result.add("  Name:			"+name+"");
		//result.add("  Type:			"+type+"");
		if(type==0){
			result.add("  Type:			Entrance");
		}
		result.add("  Description:	"+description+"");
		result.add("  Version:		"+version+"");
		result.add(" */");
		result.add("");
		result.add("@keyframes "+id+" {");
		this.sortTimePoint();
		String str = "  ";
		for(int i=0;i<timepoint.size()-1;i++){
			str=str+" "+timepoint.get(i).getPercent()+"%,";
		}
		str+=" 100% {";
		result.add(str);
		if(speed==1){
			result.add("    animation-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);");
		}
		result.add("  }");
		for(int i=0;i<timepoint.size();i++){
			List<String> onetimepoint = timepoint.get(i).getText();
			for(int j=0;j<onetimepoint.size();j++){
				result.add(onetimepoint.get(j));
			}
		}
		result.add("}");
		result.add("");
		result.add("."+id+" {");
		result.add("  animation-name: "+id+";");
		result.add("}");
		return result;
	}
	
	//对时间点进行排序
	public void sortTimePoint(){
		Collections.sort(timepoint,new Comparator<TimePoint>(){
			@Override
			public int compare(TimePoint t1, TimePoint t2) {
				return t1.getPercent()-t2.getPercent();
			}	
		});
	}
	
	//获得时间点列表
	public List<TimePoint> getTimepointList(){
		return timepoint;
	}
}
