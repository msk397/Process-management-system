package Auk;
import java.util.*;

public class FCFS {
	static double nowTime=0;
	static int PCBCount = 0;
	static List<PCB> b = new ArrayList<PCB>();
	public static List<PCB> Fmain(List<PCB> pCBCount2) {
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
				nowPCB.setEndTime(nowTime+nowPCB.getRunTime());
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
				nowPCB.setTT(nowPCB.getEndTime()-nowPCB.getStartTime());
				nowPCB.setATT(nowPCB.getTT()*1.0/nowPCB.getRunTime());
				Show.appShow(nowPCB.toString());
				end.add(nowPCB);
				ready.remove(0);
				Show.ShowReady(ready);
				
			}
			else nowTime++;
		}
		ready.clear();
		UI.setnowCount(UI.getnowCount()+PCBCount);
		nowTime=0;
		return end;
	}
}
