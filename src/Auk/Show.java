package Auk;

import java.util.*;

import javax.swing.JScrollPane;

public class Show {
	static List<String> name = new ArrayList<String>();
	static List<Double> count = new ArrayList<Double>();
public static void Showmain(List<PCB> PCB,String a) {
	double sumATT=0.0;
	double sumTT=0;
	double endtime=0;
	for(PCB i:PCB) {
		sumATT+=i.getATT();
		sumTT+=i.getTT();
		if(i.equals(PCB.get(PCB.size()-1))) endtime=i.getEndTime();
		UI.show.paintImmediately(UI.show.getBounds());
	}
	appText(a+"的平均带权周转时间："+String.format("%.3f", sumATT/UI.getnowCount())+"\n");
	appText(a+"的平均周转时间："+String.format("%.3f", sumTT*1.0/UI.getnowCount())+"\n");
	name.clear();count.clear();
	UI.PCBCount.clear();
	}
public static void appShow(String string) {
	UI.show.append(string);
	UI.show.paintImmediately(UI.show.getBounds());
}
public static void appText(String string) {
	UI.textArea.append(string);
	UI.textArea.paintImmediately(UI.textArea.getBounds());
}
public static void appTextField(String string) {
	UI.textField.setText(string);
	UI.textField.paintImmediately(UI.textField.getBounds());
}
public static void ShowReady(List<PCB> PCB) {
	String str="";
	for(PCB i:PCB) 
		str+=i.getFlag()+" ";
	Show.appTextField(str);
}
}