package AnimationsOperater;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MainFrame extends JFrame {

	private Animations animation = null;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldDescription;
	private JTextField textFieldVersion;
	private JTextField textFieldID;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JComboBox comboBoxUnit = null;
	private JComboBox comboBoxSpeed = null;
	private JComboBox comboBoxType = null;
	private JButton buttonSave = null;
	private JButton buttonEdit = null;
	private JButton buttonTEdit = null;
	JButton btnExport = null;
	JButton buttonAdd = null;
	JButton buttonDelete = null;
	private String[] typelist = {"进入动画"};
	private String[] speedlist = {"匀速","加速"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1168, 515);
		setTitle("Focusky自定义动画编辑器");
		setLocationRelativeTo(null);
		setResizable(false);
		//setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u52A8\u753B\u540D\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(37, 93, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u52A8\u753B\u7C7B\u578B\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(37, 134, 75, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u63CF\u8FF0\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(37, 178, 75, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u7248\u672C\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(37, 221, 72, 18);
		contentPane.add(label_3);
		
		JLabel lblId = new JLabel("ID\uFF1A");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(37, 263, 72, 18);
		contentPane.add(lblId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 31, 789, 388);
		contentPane.add(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) { 
			    return false;
			   } 
		};
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,r);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablemodel = (DefaultTableModel)table.getModel();
		tablemodel.setColumnIdentifiers(new Object[] { "时间点", "X轴", "Y轴", "Z轴", "透明度" });
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(125, 90, 163, 24);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setColumns(10);
		textFieldDescription.setBounds(125, 175, 163, 24);
		contentPane.add(textFieldDescription);
		
		textFieldVersion = new JTextField();
		textFieldVersion.setColumns(10);
		textFieldVersion.setBounds(123, 218, 163, 24);
		contentPane.add(textFieldVersion);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(125, 260, 163, 24);
		contentPane.add(textFieldID);
		
		comboBoxType = new JComboBox(typelist);
		comboBoxType.setBounds(126, 131, 162, 24);
		contentPane.add(comboBoxType);
		
		buttonEdit = new JButton("\u7F16\u8F91");
		buttonEdit.setEnabled(false);
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit();
			}
		});
		buttonEdit.setBounds(78, 336, 63, 27);
		contentPane.add(buttonEdit);
		
		buttonSave = new JButton("\u4FDD\u5B58");
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		/*buttonSave.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				buttonSave.setForeground(Color.BLACK);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				buttonSave.setForeground(Color.RED);
			}

			
		});
		
		buttonSave.setContentAreaFilled(false);
		buttonSave.setBorderPainted(false);
		buttonSave.setForeground(Color.RED);*/
		buttonSave.setBounds(191, 336, 63, 27);
		contentPane.add(buttonSave);
		
		JLabel label_4 = new JLabel("\u52A8\u753B\u65F6\u95F4\u70B9\u5217\u8868");
		label_4.setBounds(712, 13, 105, 18);
		contentPane.add(label_4);
		
		JLabel label_6 = new JLabel("\u901F\u5EA6\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(37, 294, 72, 18);
		contentPane.add(label_6);
		
		comboBoxSpeed = new JComboBox(speedlist);
		comboBoxSpeed.setBounds(125, 297, 163, 24);
		contentPane.add(comboBoxSpeed);
		
		buttonAdd = new JButton("\u6DFB\u52A0");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addT();
			}
		});
		buttonAdd.setBounds(722, 432, 75, 27);
		buttonAdd.setEnabled(false);
		contentPane.add(buttonAdd);
		
		buttonTEdit = new JButton("\u7F16\u8F91");
		buttonTEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editT();
			}
		});
		buttonTEdit.setBounds(567, 432, 75, 27);
		buttonTEdit.setEnabled(false);
		contentPane.add(buttonTEdit);
		
		buttonDelete = new JButton("\u5220\u9664");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteT();
			}
		});
		buttonDelete.setBounds(873, 432, 75, 27);
		buttonDelete.setEnabled(false);
		contentPane.add(buttonDelete);
		
		/*textFieldName.setText("a");
		textFieldDescription.setText("a");
		textFieldVersion.setText("a");
		textFieldID.setText("a");*/
		
		btnExport = new JButton("\u5BFC\u51FA\u52A8\u753B");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				export();
			}
		});
		btnExport.setBounds(78, 399, 176, 34);
		btnExport.setEnabled(false);
		contentPane.add(btnExport);
		
		JLabel label_5 = new JLabel("\u81EA\u5B9A\u4E49\u52A8\u753B\u5C5E\u6027");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(78, 41, 176, 18);
		contentPane.add(label_5);
	}
	
	//导出动画文件
	protected void export() {
		JFileChooser chooser = new JFileChooser("C:\\Program Files (x86)");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("选择Focusky的安装目录");
		int re = chooser.showDialog(this,"确定");
		if(re==JFileChooser.CANCEL_OPTION){
			return;
		}
		File f = new File(chooser.getSelectedFile().getAbsolutePath()+"\\resources\\animations");
		if(!f.exists()){
			JOptionPane.showMessageDialog(this, "你选择的不是Focusky的安装目录！","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		f = new File(chooser.getSelectedFile().getAbsolutePath()+"\\resources\\animations\\"+animation.getId()+".animate");
		if(f.exists()){
			JOptionPane.showMessageDialog(this, "该ID已经存在，请更改ID！","错误",JOptionPane.ERROR_MESSAGE);
			textFieldID.setText("");
			textFieldID.requestFocus();
			this.edit();
			return;
		}
		FileOutputStream fos = null;
		OutputStreamWriter w = null;
		try {
			fos = new FileOutputStream(f);
			w = new OutputStreamWriter(fos, "utf-8");
			List<String> out = animation.getText();
			for(int i=0;i<out.size();i++){
				w.write(out.get(i));
				w.write("\n");
			}
			w.close();
			fos.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		InputStream inputstream = MainFrame.class.getClassLoader().getResourceAsStream("res/img.png");
		f = new File(chooser.getSelectedFile().getAbsolutePath()+"\\resources\\animations\\thumbs\\"+animation.getId()+".png"); 
		ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[4672];
        int numBytesRead = 0;
        try {
			while ((numBytesRead = inputstream.read(buf)) != -1) {
			    output.write(buf, 0, numBytesRead);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        FileImageOutputStream imageOutput;
		try {
			imageOutput = new FileImageOutputStream(f);
			imageOutput.write(buf, 0, buf.length);
			imageOutput.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Object[] options = {"立即启动Focusky","我自己重启"};
		int returnVal=JOptionPane.showOptionDialog(this, "动画导出成功，重新启动你的Focusky查看效果！","动画导出成功",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		if(returnVal==0){
			Runtime run = Runtime.getRuntime();
			try {
				run.exec(chooser.getSelectedFile().getAbsolutePath()+"\\Focusky.exe ");
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}	
	}

	//删除时间点
	protected void deleteT() {
		int index = table.getSelectedRow();
		int percent=animation.getTimepointList().get(index).getPercent();
		if(percent==0||percent==100){
			JOptionPane.showMessageDialog(this, "该时间点不可删除！","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Object[] options = { "删除", "取消" };
		int returnVal = JOptionPane.showOptionDialog(null, "确定删除改时间点？", "删除",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (returnVal == 0) {
			animation.getTimepointList().remove(index);
			this.refreshTable();
		}
		return;
	}

	//编辑时间点
	protected void editT() {
		if(table.getSelectedRow()<0){
			JOptionPane.showMessageDialog(this, "请先选择一个时间点！","提示",JOptionPane.WARNING_MESSAGE);
			return;
		}
		AddTimePoint addtimepoint = new AddTimePoint(true,table.getSelectedRow(),this);
		addtimepoint.setVisible(true);
		this.setEnabled(false);
	}

	//保存动画属性
	public void save(){
		if(textFieldName.getText().length()<1){
			textFieldName.requestFocus();
			JOptionPane.showMessageDialog(this, "动画名不可为空！","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(textFieldDescription.getText().length()<1){
			textFieldDescription.requestFocus();
			JOptionPane.showMessageDialog(this, "描述不可为空！","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(textFieldDescription.getText().length()<1){
			textFieldDescription.requestFocus();
			JOptionPane.showMessageDialog(this, "描述不可为空！","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(textFieldVersion.getText().length()<1){
			textFieldVersion.requestFocus();
			JOptionPane.showMessageDialog(this, "版本不可为空！","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(textFieldVersion.getText().length()<1){
			textFieldVersion.requestFocus();
			JOptionPane.showMessageDialog(this, "版本不可为空！","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(textFieldID.getText().length()<1){
			textFieldID.requestFocus();
			JOptionPane.showMessageDialog(this, "ID不可为空！","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(animation==null){
			animation = new Animations(comboBoxSpeed.getSelectedIndex());
		}
		animation.setName(textFieldName.getText());
		animation.setType(comboBoxType.getSelectedIndex());
		animation.setDescription(textFieldDescription.getText());
		animation.setVersion(textFieldVersion.getText());
		animation.setId(textFieldID.getText());
		animation.setSpeed(comboBoxSpeed.getSelectedIndex());
		buttonSave.setEnabled(false);
		buttonEdit.setEnabled(true);
		textFieldName.setEditable(false);
		textFieldDescription.setEditable(false);
		textFieldVersion.setEditable(false);
		textFieldID.setEditable(false);
		comboBoxType.setEnabled(false);
		comboBoxSpeed.setEnabled(false);
		buttonAdd.setEnabled(true);
		buttonTEdit.setEnabled(true);
		buttonDelete.setEnabled(true);
		btnExport.setEnabled(true);
		this.refreshTable();
	}
	
	//编辑时间点
	public void edit(){
		buttonSave.setEnabled(true);
		buttonEdit.setEnabled(false);
		textFieldName.setEditable(true);
		textFieldDescription.setEditable(true);
		textFieldVersion.setEditable(true);
		textFieldID.setEditable(true);
		comboBoxType.setEnabled(true);
		comboBoxSpeed.setEnabled(true);
		buttonAdd.setEnabled(false);
		buttonTEdit.setEnabled(false);
		buttonDelete.setEnabled(false);
		btnExport.setEnabled(false);
	}
	
	//刷新表格
	public void refreshTable(){
		tablemodel.setRowCount(0);
		animation.sortTimePoint();
		List<TimePoint> timepoint = animation.getTimepointList();
		String percent = null;
		String translateX = null;
		String translateY = null;
		String translateZ = null;
		String opacity = null;
		int unit;
		for(int i=0;i<timepoint.size();i++){
			percent = timepoint.get(i).getPercent()+"%";
			unit = timepoint.get(i).getUnit();
			//System.out.println("获得"+timepoint.get(i).getOpacity()+unit);
			if(unit==0){
				translateX=timepoint.get(i).getTranslateX()+"px";
				translateY=timepoint.get(i).getTranslateY()+"px";
				translateZ=timepoint.get(i).getTranslateZ()+"px";
			}else{
				translateX=timepoint.get(i).getTranslateX()+"%";
				translateY=timepoint.get(i).getTranslateY()+"%";
				translateZ=timepoint.get(i).getTranslateZ()+"%";
			}
			//System.out.println(timepoint.get(i).getOpacity());
			if(timepoint.get(i).getOpacity()==0){
				opacity = "透明";
			}else{
				opacity = "不透明";
			}
			tablemodel.addRow(new Object[]{percent,translateX,translateY,translateZ,opacity});
		}
	}
	
	//添加时间点
	private void addT(){
		AddTimePoint addtimepoint = new AddTimePoint(false,-1,this);
		addtimepoint.setVisible(true);
		this.setEnabled(false);
	}
	
	//获得动画对象
	public Animations getanimation(){
		return animation;
	}
}
