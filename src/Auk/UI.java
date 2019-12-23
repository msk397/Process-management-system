package Auk;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI {
	static List<PCB> PCBCount = new ArrayList<PCB>();
	public static int NowCount=0;
	public static JTextArea show,textArea;
	public static ButtonGroup group1;
	public static boolean isClick=false,isChoose=false;
	public static JFrame frame;
	public static JTextField name;
	public static JTextField start;
	public static int timeSlice=0;
	public static JTextField run;
	public static Canvas canvas;
	private JTextField timeArea;
	public static JTextField textField;
	private  Font f1 = new Font("宋体",Font.PLAIN,15);
	/**
	 * Launch the application.
	 */
	public static int getnowCount() {return PCBCount.size();}
	public static void setnowCount(int a) {NowCount=a;}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("进程调度系统");
		frame.setFont(f1);
		frame.setBounds(100, 100, 941, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8FDB\u7A0B\u540D\u79F0");
		label.setBounds(25, 41, 54, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5230\u8FBE\u65F6\u95F4");
		label_1.setBounds(101, 41, 54, 15);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u8FD0\u884C\u65F6\u95F4");
		label_2.setBounds(177, 41, 54, 15);
		frame.getContentPane().add(label_2);
		
		name = new JTextField();
		name.setBounds(13, 66, 66, 21);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		start = new JTextField();
		start.setBounds(89, 66, 66, 21);
		frame.getContentPane().add(start);
		start.setColumns(10);
		
		run = new JTextField();
		run.setBounds(165, 66, 66, 21);
		name.setEditable(false);
		start.setEditable(false);
		run.setEditable(false);
		frame.getContentPane().add(run);
		run.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(355, 373, 502, 68);
		frame.getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		timeArea = new JTextField();
		timeArea.setBounds(13, 158, 66, 21);
		frame.getContentPane().add(timeArea);
		timeArea.setColumns(10);
		
		JRadioButton Fcfs = new JRadioButton("FCFS");
		Fcfs.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(Fcfs.isSelected()) {
					show.setText("");
					textArea.setText("");
					Show.appShow("进程名称\t到达时间\t运行时间\t结束时间\t周转时间\t带权周转时间\t\n");
					PCBCount = FCFS.Fmain(PCBCount);
					Show.Showmain(PCBCount,"FCFS");
				}
			}
		});
		Fcfs.setBounds(281, 37, 66, 23);
		frame.getContentPane().add(Fcfs);
		
		JRadioButton Spf = new JRadioButton("SPF");
		Spf.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(Spf.isSelected()) {
					show.setText("");
					textArea.setText("");
					Show.appShow("进程名称\t到达时间\t运行时间\t结束时间\t周转时间\t带权周转时间\t\n");
						PCBCount = SPF.Smain(PCBCount);
						Show.Showmain(PCBCount,"SPF");
				}
			}
		});
		Spf.setBounds(281, 65, 48, 23);
		frame.getContentPane().add(Spf);
		
		JRadioButton Rr = new JRadioButton("RR");
		Rr.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(Rr.isSelected()) {
					show.setText("");
					textArea.setText("");
					Show.appShow("进程名称\t到达时间\t运行时间\t结束时间\t周转时间\t带权周转时间\t\n");
						timeSlice =Integer.parseInt(timeArea.getText());
						PCBCount = RR.Rmain(PCBCount,timeSlice);
						Show.Showmain(PCBCount,"RR");
				}
			}
		});
		Rr.setBounds(281, 90, 66, 23);
		frame.getContentPane().add(Rr);
		
		ButtonGroup group2 = new ButtonGroup ();
		group2.add (Fcfs);
		group2.add (Spf);
		group2.add (Rr);
		
		JButton add = new JButton("\u6DFB \u52A0");
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String a=name.getText()+" "+start.getText()+" "+run.getText();
				PCB jj = Add.singleAdd(a);
				if(jj!=null) PCBCount.add(jj);
				name.setText("");
				start.setText("");
				run.setText("");
			}
		});
		add.setBounds(165, 99, 66, 23);
		frame.getContentPane().add(add);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(355, 66, 560, 277);
		frame.getContentPane().add(scrollPane);
		show = new JTextArea();
		show.setRows(6);
		scrollPane.setViewportView(show);
		show.setEditable(false);
		
		
		JTextArea addArea = new JTextArea();
		addArea.setBounds(25, 246, 221, 195);
		addArea.setEditable(false);
		addArea.setRows(3);
		addArea.setColumns(100);
		frame.getContentPane().add(addArea);
		
		JRadioButton single = new JRadioButton("\u5355\u4E2A\u6DFB\u52A0\u8FDB\u7A0B");
		single.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(single.isSelected()) {
					addArea.setEditable(false);
					name.setEditable(true);
					start.setEditable(true);
					run.setEditable(true);
				}
			}
		});
		single.setBounds(20, 6, 97, 23);
		frame.getContentPane().add(single);
		
		JRadioButton mass = new JRadioButton("\u6279\u91CF\u6DFB\u52A0\u8FDB\u7A0B");
		mass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(mass.isSelected()) {
					addArea.setEditable(true);
					name.setEditable(false);
					start.setEditable(false);
					run.setEditable(false);
				}
			}
		});
		mass.setBounds(25, 195, 97, 23);
		frame.getContentPane().add(mass);
		ButtonGroup group = new ButtonGroup ();
		group.add (mass);
		group.add (single);
		
		JLabel label_3 = new JLabel("	   \u9009\u62E9\u7B97\u6CD5");
		label_3.setBounds(265, 10, 79, 15);
		frame.getContentPane().add(label_3);
		
		JButton button = new JButton("\u6DFB \u52A0");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				group2.clearSelection();
				PCBCount.clear();
				String a = addArea.getText();
				List<PCB>  aa = new ArrayList<PCB>();
				aa.addAll(Add.massAdd(a));
				for(PCB i:aa)
					if(i!=null)
				PCBCount.add(i);
			}
		});
		button.setBounds(165, 195, 66, 23);
		frame.getContentPane().add(button);
		
		JLabel lblps = new JLabel("\u65F6\u95F4\u7247\uFF1A(PS.\u9009\u62E9\u65F6\u95F4\u7247\u8F6E\u8F6C\u7B97\u6CD5\u8F93\u5165)");
		lblps.setBounds(13, 133, 218, 15);
		frame.getContentPane().add(lblps);
		
		
		
		JLabel label_4 = new JLabel("\u8FDB\u7A0B\u540D\u79F0");
		label_4.setBounds(35, 221, 54, 15);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u5230\u8FBE\u65F6\u95F4");
		label_5.setBounds(101, 221, 54, 15);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u8FD0\u884C\u65F6\u95F4");
		label_6.setBounds(165, 221, 54, 15);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u5C31\u7EEA\u961F\u5217\uFF1A");
		label_7.setBounds(355, 7, 97, 21);
		frame.getContentPane().add(label_7);
		
		textField = new JTextField();
		textField.setBounds(355, 26, 502, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

}
