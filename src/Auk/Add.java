package Auk;

import java.util.*;
import javax.swing.JOptionPane;

public class Add {
 public static PCB singleAdd(String a) {
	 String[] b = a.split(" ");
	 if(b.length!=3)JOptionPane.showMessageDialog(null, "输入错误，请重新输入", "錯誤",JOptionPane.ERROR_MESSAGE); 
	 String a1 = b[0];
	 double a2 = Double.parseDouble(b[1]);
	 double a3 = Double.parseDouble(b[2]);
	 PCB i = new PCB(a1,a2,a3);
	 boolean f = pd(i);
	 if(!f) JOptionPane.showMessageDialog(null, "进程名重复，请重新输入", "錯誤",JOptionPane.ERROR_MESSAGE); 
	 else UI.NowCount++;
	 return f? i:null;
 }
 public static List<PCB> massAdd(String a){
	 List<PCB> t = new ArrayList<PCB>();
	 StringTokenizer st = new StringTokenizer(a);
	 while( st.hasMoreElements() ){
		 String a1 = st.nextToken();
		 double a2 = Double.parseDouble(st.nextToken());
		 double a3 = Double.parseDouble(st.nextToken());
		 PCB i = new PCB(a1,a2,a3);
		 boolean f = pd(i);
		 for(PCB z:t) {
				if(z.flag.equals(i.flag)) f=false;
			}
		 if(!f) {JOptionPane.showMessageDialog(null, i.getFlag()+"进程名重复，请重新输入", "錯誤",JOptionPane.ERROR_MESSAGE);;continue;}
		 else UI.NowCount++;
		 t.add(i);
	 }
	 return t;
 }
 public static List<PCB> addNewPCB(PCB i,double a) throws InterruptedException {
		List<PCB> b = new ArrayList<PCB>();
		int n = JOptionPane.showConfirmDialog(null, "是否在"+i.getFlag()+"运行过程中插入新进程？", "询问",JOptionPane.YES_NO_OPTION);
		while(true) {
			if(n==0){
				String d = JOptionPane.showInputDialog(null,i.getFlag()+"进程的开始运行时间为"+a+"请确保输入进程的开始时间不小于"+a+"\n","插入进程",JOptionPane.PLAIN_MESSAGE); 
				if(d == null || (d != null && ("".equals(d))))   
				{
				    return b;
				}
				String[] e = d.split(" ");
				if(e.length<3) {JOptionPane.showMessageDialog(null, "输入有误，请重新输入", "錯誤",JOptionPane.ERROR_MESSAGE);continue;}
				 String a1 = e[0];
				 double a2 = Double.parseDouble(e[1]);
				 if(a2<a){JOptionPane.showMessageDialog(null, "输入有误，请重新输入", "錯誤",JOptionPane.ERROR_MESSAGE);continue;}
				 double a3 = Double.parseDouble(e[2]);
				 PCB z = new PCB(a1,a2,a3);
				 boolean f = pd(z);
				 if(!f) {JOptionPane.showMessageDialog(null, "进程名重复，请重新输入", "錯誤",JOptionPane.ERROR_MESSAGE);continue;}
				 else {
					 UI.NowCount++;
					 b.add(z);
					return b;
				 }
			}
			else if(n==1) {
				return b;
			}
			return b;
		}
	}

public static boolean pd(PCB a) {
	for(PCB i:UI.PCBCount) {
		if(i.flag.equals(a.flag)) return false;
	}
	return true;
}}