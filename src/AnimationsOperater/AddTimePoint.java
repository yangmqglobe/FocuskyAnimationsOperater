package AnimationsOperater;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class AddTimePoint extends JDialog {
	private MainFrame mainframe;
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	private JLabel labelValue = null;
	private JSlider sliderTimePoint = null;
	private JRadioButton rdbtnUnitPercent = null;
	private JRadioButton radioButtonTransparent = null;
	private JRadioButton radioButtonOpaque = null;
	private int index;
	private boolean flag = false;//false为添加时间的，true为编辑

	/**
	 * Create the dialog.
	 */
	public AddTimePoint(boolean flag,int index,MainFrame mainframe) {
		super(mainframe);
		this.mainframe=mainframe;
		this.index = index;
		
		setTitle("\u6DFB\u52A0\u65F6\u95F4\u70B9");
		setBounds(100, 100, 441, 362);
		setLocationRelativeTo(null);
		addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				cancel();
			}
		});
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u65F6\u95F4\u70B9\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(50, 38, 72, 18);
		getContentPane().add(label);
		
		JLabel lblX = new JLabel("X\u8F74\uFF1A");
		lblX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblX.setBounds(50, 108, 72, 18);
		getContentPane().add(lblX);
		
		JLabel lblY = new JLabel("Y\u8F74\uFF1A");
		lblY.setHorizontalAlignment(SwingConstants.RIGHT);
		lblY.setBounds(50, 139, 72, 18);
		getContentPane().add(lblY);
		
		JLabel lblZ = new JLabel("Z\u8F74\uFF1A");
		lblZ.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZ.setBounds(50, 170, 72, 18);
		getContentPane().add(lblZ);
		
		JLabel label_1 = new JLabel("\u900F\u660E\u5EA6\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(50, 201, 72, 18);
		getContentPane().add(label_1);
		
		sliderTimePoint = new JSlider(1,99);
		sliderTimePoint.setBounds(136, 38, 140, 23);
		sliderTimePoint.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				labelValue.setText(sliderTimePoint.getValue()+"%");
			}
			
		});
		getContentPane().add(sliderTimePoint);
		
		labelValue = new JLabel("");
		labelValue.setBounds(290, 38, 46, 18);
		labelValue.setText(sliderTimePoint.getValue()+"%");
		getContentPane().add(labelValue);
		
		JLabel label_3 = new JLabel("\u5355\u4F4D\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(50, 77, 72, 18);
		getContentPane().add(label_3);
		
		JRadioButton rdbtnUnitPx = new JRadioButton("px");
		rdbtnUnitPx.setSelected(true);
		rdbtnUnitPx.setBounds(146, 70, 63, 27);
		getContentPane().add(rdbtnUnitPx);
		
		rdbtnUnitPercent = new JRadioButton("%");
		rdbtnUnitPercent.setBounds(250, 73, 63, 27);
		getContentPane().add(rdbtnUnitPercent);
		
		ButtonGroup buttonGroupUnit = new ButtonGroup();
		buttonGroupUnit.add(rdbtnUnitPx);
		buttonGroupUnit.add(rdbtnUnitPercent);
		
		textFieldX = new JTextField();
		textFieldX.setBounds(123, 105, 213, 24);
		getContentPane().add(textFieldX);
		textFieldX.setColumns(10);
		
		textFieldY = new JTextField();
		textFieldY.setColumns(10);
		textFieldY.setBounds(123, 139, 213, 24);
		getContentPane().add(textFieldY);
		
		textFieldZ = new JTextField();
		textFieldZ.setColumns(10);
		textFieldZ.setBounds(123, 170, 213, 24);
		getContentPane().add(textFieldZ);
		
		radioButtonOpaque = new JRadioButton("\u4E0D\u900F\u660E");
		radioButtonOpaque.setSelected(true);
		radioButtonOpaque.setBounds(146, 197, 83, 27);
		getContentPane().add(radioButtonOpaque);
		
		radioButtonTransparent = new JRadioButton("\u900F\u660E");
		radioButtonTransparent.setBounds(250, 197, 83, 27);
		getContentPane().add(radioButtonTransparent);
		
		ButtonGroup buttonGroupOpacity = new ButtonGroup();
		buttonGroupOpacity.add(radioButtonTransparent);
		buttonGroupOpacity.add(radioButtonOpaque);
		
		JButton buttonEnter = new JButton("\u786E\u5B9A");
		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enter();
			}
		});
		buttonEnter.setBounds(80, 252, 72, 27);
		getContentPane().add(buttonEnter);
		
		JButton buttonCancel = new JButton("\u53D6\u6D88");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		buttonCancel.setBounds(241, 252, 72, 27);
		getContentPane().add(buttonCancel);

		if(flag){
			this.flag=flag;
			this.setTitle("编辑时间点");
			sliderTimePoint.setMaximum(100);
			sliderTimePoint.setMinimum(0);
			TimePoint timepoint = mainframe.getanimation().getTimepointList().get(index);
			sliderTimePoint.setValue(timepoint.getPercent());
			if(sliderTimePoint.getValue()==0||sliderTimePoint.getValue()==100){
				sliderTimePoint.setEnabled(false);
			}
			if(timepoint.getUnit()==1){
				rdbtnUnitPercent.setSelected(true);
			}
			textFieldX.setText(timepoint.getTranslateX()+"");
			textFieldY.setText(timepoint.getTranslateY()+"");
			textFieldZ.setText(timepoint.getTranslateZ()+"");
			if(timepoint.getOpacity()==0){
				radioButtonTransparent.setSelected(true);
			}
			//mainframe.getanimation().getTimepointList().remove(index);
		}
	}
	
	private void cancel(){	
		mainframe.setEnabled(true);
		this.dispose();
	}
	private void enter(){
		if(textFieldX.getText().length()<1){
			textFieldX.setText("0");
		}
		if(textFieldY.getText().length()<1){
			textFieldY.setText("0");
		}
		if(textFieldZ.getText().length()<1){
			textFieldZ.setText("0");
		}
		if(textFieldX.getText().matches("[^\\d]")){
			textFieldX.setText("");
			textFieldX.requestFocus();
			JOptionPane.showMessageDialog(this, "仅接受数字！","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(textFieldY.getText().matches("[^\\d]")){
			textFieldY.setText("");
			textFieldY.requestFocus();
			JOptionPane.showMessageDialog(this, "仅接受数字！","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(textFieldZ.getText().matches("[^\\d]")){
			textFieldZ.setText("");
			textFieldZ.requestFocus();
			JOptionPane.showMessageDialog(this, "仅接受数字！","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int percent = sliderTimePoint.getValue();
		int translateX = Integer.parseInt(textFieldX.getText());
		int translateY = Integer.parseInt(textFieldY.getText());
		int translateZ = Integer.parseInt(textFieldZ.getText());
		int opacity = 1;
		int unit = 0;
		if(rdbtnUnitPercent.isSelected()){
			unit = 1;
		}
		if(radioButtonTransparent.isSelected()){
			opacity = 0;
		}
		//System.out.println("AddTimePoint类enter"+unit+opacity);
		Animations a = mainframe.getanimation();
		if(!flag){
			if(a.isTimePointExist(percent)>-1){
				JOptionPane.showMessageDialog(this, "该时间点已经存在！","重复",JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				a.addTimepoint(percent, translateX, translateY, translateZ, opacity, unit);
				//System.out.println("添加"+opacity);
			}
		}else{
			a.getTimepointList().remove(index);
			a.addTimepoint(percent, translateX, translateY, translateZ, opacity, unit);
		}
		mainframe.setEnabled(true);
		this.dispose();
		mainframe.refreshTable();
	}
}
