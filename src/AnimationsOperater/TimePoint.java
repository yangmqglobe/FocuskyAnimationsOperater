package AnimationsOperater;
import java.util.ArrayList;
import java.util.List;

class TimePoint {
	/**
	 * 时间点类，抽象各个时间点
	 */
	private int percent;
	private int translateX;
	private int translateY;
	private int translateZ;
	private int opacity;//0为透明，1为不透明
	private int unit;//单位，0为px，1为百分比
	private int rotateX=0;
	private int rotateY=0;
	private int rotateZ=0;
	private int rotate=0;
	private int originX=50;
	private int originY=50;
	
	//无参构造
	public TimePoint(){}
	
	//有参构造
	public TimePoint(
			int percent
			,int translateX,int translateY,int translateZ
			,int opacity,int unit){
		this.setPercent(percent);
		this.setTranslateX(translateX);
		this.setTranslateY(translateY);
		this.setTranslateZ(translateZ);
		this.setOpacity(opacity);
		this.setUnit(unit);
		//System.out.println("TimePoint类"+opacity+unit);
	}
	
	public TimePoint(
			int percent
			,int translateX,int translateY,int translateZ
			,int opacity,int unit
			,int rotateX,int rotateY,int rotateZ
			,int rotate,int originX,int originY){
		this.setPercent(percent);
		this.setTranslateX(translateX);
		this.setTranslateY(translateY);
		this.setTranslateZ(translateZ);
		this.setOpacity(opacity);
		this.setUnit(unit);
		this.setRotateX(rotateX);
		this.setRotateY(rotateY);
		this.setRotateZ(rotateZ);
		this.setRotate(rotate);
		this.setOriginX(originX);
		this.setOriginY(originY);
		//System.out.println("TimePoint类"+opacity+unit);
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public int getTranslateX() {
		return translateX;
	}
	public void setTranslateX(int translateX) {
		this.translateX = translateX;
	}
	public int getTranslateY() {
		return translateY;
	}
	public void setTranslateY(int translateY) {
		this.translateY = translateY;
	}
	public int getTranslateZ() {
		return translateZ;
	}
	public void setTranslateZ(int translateZ) {
		this.translateZ = translateZ;
	}
	public int getOpacity() {
		return opacity;
	}
	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	
	public int getRotateX() {
		return rotateX;
	}

	public void setRotateX(int rotateX) {
		this.rotateX = rotateX;
	}

	public int getRotateY() {
		return rotateY;
	}

	public void setRotateY(int rotateY) {
		this.rotateY = rotateY;
	}

	public int getRotateZ() {
		return rotateZ;
	}

	public void setRotateZ(int rotateZ) {
		this.rotateZ = rotateZ;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	public int getOriginX() {
		return originX;
	}

	public void setOriginX(int originX) {
		this.originX = originX;
	}

	public int getOriginY() {
		return originY;
	}

	public void setOriginY(int originY) {
		this.originY = originY;
	}

	//将整个时间点转换成文本
	public List<String> getText(){
		List<String> result = new ArrayList<String>();
		/*0% {
		    opacity: 1;
		    transform: translate3d(0, -3000px, 0);
		  }*/
		result.add("  "+percent+"% {");
		result.add("    opacity: "+opacity+";");
		if(originX!=50&&originY!=50){
			
		}
		if(unit==0){
			String transform = "transform: translate3d("+translateX+"px, "+translateY+"px, "+translateZ+"px)";
			if(rotateX!=0&&rotateY!=0&&rotateZ!=0&&rotate!=0){
				transform+="rotate3d("+rotateX+", "+rotateY+", "+rotateZ+", "+rotate+"deg);";
			}else{
				transform+=";";
			}
			result.add(transform);
		}else{
			String transform = "transform: translate3d("+translateX+"%, "+translateY+"%, "+translateZ+"%);";
			if(rotateX!=0&&rotateY!=0&&rotateZ!=0&&rotate!=0){
				transform+="rotate3d("+rotateX+", "+rotateY+", "+rotateZ+", "+rotate+"deg);";
			}else{
				transform+=";";
			}
			result.add(transform);
		}
		result.add("}");
		return result;
	}
	
}
