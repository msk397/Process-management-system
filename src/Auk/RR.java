package Auk;

import java.util.*;


public class RR {
	static double nowTime=0;
	static int PCBCount = 0;
	static List<PCB> b = new ArrayList<PCB>();
	public static List<PCB> Rmain(List<PCB> pCBCount2,int time) {
		List<PCB> ready = new ArrayList<PCB>();
		List<PCB> end = new ArrayList<PCB>();
		Collections.sort(pCBCount2,new startTimeSort());
		while(true) {
			boolean f=false;
			Collections.sort(pCBCount2,new startTimeSort());
			for(int j=0;j<pCBCount2.size();j++) {
				PCB i = pCBCount2.get(j);
				if(i.getStartTime()<=nowTime&&i.getStatus()=='0') {i.setStatus('1');ready.add(i);}
				else {
					int count=0;
					for(PCB ii:pCBCount2) {
						if(ii.getStatus()=='3') count++;
					}
					if(count==pCBCount2.size()) {f=true;break;}
				}
			}
			Show.ShowReady(ready);
			if(f)break;
			if(ready.size()>0) {
				PCB nowPCB = ready.get(0);
				nowPCB.setStatus('2');
				double remain=nowPCB.getRunTime()-nowPCB.getWasRun();
				if(remain<=time) {
					nowPCB.setWasRun(nowPCB.getRunTime());
					nowPCB.setEndTime(nowTime+remain);
					try {
						b = Add.addNewPCB(nowPCB,nowTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(PCB i:b) {pCBCount2.add(new PCB(i));PCBCount++;}
					b.clear();
					Show.name.add(nowPCB.getFlag());
					Show.count.add(nowTime);
					nowTime = nowPCB.getEndTime();
					nowPCB.setStatus('3');
					Collections.sort(pCBCount2,new startTimeSort());
					Show.appShow(nowPCB.toString());
					for(int j=0;j<pCBCount2.size();j++) {
						PCB i = pCBCount2.get(j);
						if(i.getStartTime()<=nowTime&&i.getStatus()=='0') {i.setStatus('1');ready.add(i);}}
					Show.ShowReady(ready);
					nowPCB.setTT(nowPCB.getEndTime()-nowPCB.getStartTime());
					nowPCB.setATT(nowPCB.getTT()*1.0/nowPCB.getRunTime());
					end.add(nowPCB);
					ready.remove(0);
					Show.ShowReady(ready);
				}
				else {
					nowPCB.setWasRun(nowPCB.getWasRun()+time);
					try {
						b = Add.addNewPCB(nowPCB,nowTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(PCB i:b) {pCBCount2.add(new PCB(i));PCBCount++;}
					b.clear();
					Show.name.add(nowPCB.getFlag());
					Show.count.add(nowTime);
					nowTime+=time;
					nowPCB.setStatus('1');
					ready.remove(0);
					Collections.sort(pCBCount2,new startTimeSort());
					for(int j=0;j<pCBCount2.size();j++) {
						PCB i = pCBCount2.get(j);
						if(i.getStartTime()<=nowTime&&i.getStatus()=='0') {i.setStatus('1');ready.add(i);}}
					ready.add(nowPCB);
				}	
			}
			else nowTime++;
		}
		ready.clear();
		UI.setnowCount(UI.getnowCount()+PCBCount);
		nowTime=0;
		return end;
	}
}