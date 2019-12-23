package Auk;
import java.util.*;
public final class PCB{
	String flag;
	char status;//0新建，1就绪，2运行，3退出
	double startTime,runTime,TT,endTime,wasRun;
	// TODO count获取当前进程数
	int count=UI.getnowCount();
	double ATT;
	public String toString() {
		return flag+"\t"+String.format("%.3f",startTime)+"\t"+String.format("%.3f",runTime)+"\t"+String.format("%.3f",endTime)+
				"\t"+TT+"\t"+String.format("%.3f",ATT)+"\n";
	}
	public PCB(){
		flag=String.valueOf(++count);
		status='0';
		startTime = 0;
		runTime = 0;
	}
	public PCB(double a,double b) {
		flag=String.valueOf(++count);
		status='0';
		startTime=a;
		runTime=b;
	}
	public PCB(String c,double a,double b) {
		flag=c;
		status='0';
		startTime=a;
		runTime=b;
	}
	public PCB(PCB a) {
		flag = a.flag;
		status='0';
		startTime=a.getStartTime();
		runTime=a.getRunTime();
	}
	public void setStatus(char a) {status=a;}
	public void setWasRun(double a) {wasRun=a;}
	public void setTT(double a) {TT=a;}
	public void setATT(double a) {ATT=a;}
	public void setEndTime(double a) {endTime=a;}
	public double getEndTime() {return endTime;}
	public double getStartTime() {return startTime;}
	public double getRunTime() {return runTime;}
	public double getTT() {return TT;}
	public double getATT() {return ATT;}
	public char getStatus() {return status;}
	public double getWasRun() {return wasRun;}
	public String getFlag() {return flag;}
}
class startTimeSort implements Comparator<PCB>{
	@Override
	public int compare(PCB o1, PCB o2) {
		// TODO Auto-generated method stub
		if(o1.startTime-o2.startTime==0)
			return o1.flag.compareTo(o2.flag);
	else return (int)(o1.startTime-o2.startTime);
	}
}
class startTimeSort1 implements Comparator<PCB>{
	@Override
	public int compare(PCB o1, PCB o2) {
		// TODO Auto-generated method stub
		if(o1.startTime-o2.startTime==0)
			if(o1.runTime==o2.runTime)
				return o1.flag.compareTo(o2.flag);
			else return (int)(o1.runTime-o2.runTime);
		else return (int)(o1.startTime-o2.startTime);
	}
}
class runTimeSort implements Comparator<PCB>{
	@Override
	public int compare(PCB o1, PCB o2) {
		// TODO Auto-generated method stub
		if(o1.runTime==o2.runTime)
			return o1.flag.compareTo(o2.flag);
		return (int)(o1.runTime-o2.runTime);
	}
}
