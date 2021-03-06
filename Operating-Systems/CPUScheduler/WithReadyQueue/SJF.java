import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CPUSJF {
	Vector allProcs = new Vector();
	Vector jobQueue = new Vector();
	PriorityQueue<Process> readyQueue; 
	long idle = 0, currentTime = 0, busy = 0;
	long totalWaitingTime = 0;
	int procsOut = 0;
	TreeMap turnAroundTime = new TreeMap();
	static long totalUtilityTime = 0;
	int tempPID = 0;
	int timeArr[] = new int[99];
	int time = 0;
	int index = 0;
	
	Process activeJobInCPU = null;
	Process activeJobInIO = null;
	
	CPUSJF(String filename) {
		for(int i=0; i< timeArr.length; i++)
			timeArr[i] = 98;
		
		readyQueue =  new PriorityQueue<Process>(new Comparator<Process>(){
			public  int compare(Process p1, Process p2){
				return Long.compare(p1.getBurstTime(), p2.getBurstTime());
				}
			}
		);
		Process proc = null;
		String s = null;
		double arrivalTime = 0;
		double burstTime = 0;
		int priority = 0;
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while ((s = input.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, ",");
				arrivalTime = Double.parseDouble(st.nextToken());
				burstTime = Double.parseDouble(st.nextToken());
				arrivalTime = arrivalTime * 10;
				burstTime = burstTime * 10;
				proc = new Process((long)arrivalTime, (long)burstTime, (long)priority);
				allProcs.add(proc);
			}

		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
		LoadJobQueue(allProcs);
		LoadReadyQueue();
	}
	
	private void LoadJobQueue(Vector jobs) {
		Process p;
		for (int i = 0; i < jobs.size(); i++) {
			p = (Process) jobs.get(i);
			jobQueue.add(p);
		}
	}
	
	private void LoadReadyQueue() {
		Process p;
		for (int i = 0; i < jobQueue.size(); i++) {
			p = (Process) jobQueue.get(i);
			readyQueue.add(p);
		}
	}
	
	public void startSimulate() {
		while (nextCycle())
			;
	}

	private boolean nextCycle() {
		boolean moreCycles = false;
		if (readyQueue.isEmpty()){
			System.out.print("|");
			time++;
			timeArr[index] = time;
			moreCycles = false;
			printStats();
		}
		else{
			moreCycles = true;
			performCPU();
			cleanup();
			//rotateQueue();
			currentTime++;
		}
		return moreCycles;
	}

	private void performCPU() {	

		if(!readyQueue.isEmpty()){

			Process p = null;
			boolean flag = false;

			Iterator i1 = readyQueue.iterator();
			while(i1.hasNext()){
				p = (Process)i1.next();
				if(p.getArrivalTime() == 0){
					p.cpuExecuting(currentTime);
					flag = true;
					break;
				}
			}

			if(flag == false)
				p = readyQueue.peek();

			if(p.PID != tempPID){
				tempPID = p.PID;
				if(time % 10 == 0){
					System.out.print("P" + tempPID);
					timeArr[index] = time;
					index++;
				}
				time++;
			}
			else{
				if(time % 10 == 0){
					System.out.print("-");
					timeArr[index] = 99;
					index++;
				}
				time++;
			}
			if(flag == false)
				p.cpuExecuting(currentTime);
			busy++;
			totalUtilityTime++;
			Iterator i = readyQueue.iterator();
			Process temp;
			while(i.hasNext()){
				temp = (Process)i.next();
				if(temp.active != true)
					temp.cpuWaiting(currentTime);
			}
		}else{
			idle++;
		}
	}

	private void rotateQueue() {
		if (!readyQueue.isEmpty()){
			Process p = readyQueue.peek();
			if(p.isCPUExecutingTimeFinished()){
				Process temp = readyQueue.remove();
			}
		}
	}
	
	private void cleanup(){
		if(!readyQueue.isEmpty()){
			long temp = 0;


			Iterator i = readyQueue.iterator();
			Process p;
			while(i.hasNext()){
				p = (Process)i.next();
				if(p.active == true){

					if(p.isCPUTotalBurstTimeFinished()){
						timeArr[index] = 99;
						index++;
						System.out.print("-");
						readyQueue.remove(p);
						temp = p.lifeTime - p.getArrivalTime();
						turnAroundTime.put(p.PID, temp);
						totalWaitingTime = totalWaitingTime + p.cpuQueueWaitTime;
						procsOut++;
					}
				}
			}
		}
	}
	
	private void printStats(){
		double cpuUtilization = 0, idlePercentage = 0, totalTurnAroundTime = 0;
		double throughput = (double)10 / totalUtilityTime;
		cpuUtilization = (double)busy / currentTime * 100;
		idlePercentage = (double)idle / currentTime * 100;
		System.out.println();
		for(int i =0; i < timeArr.length; i++){
			if (timeArr[i] == 99){
				System.out.print(" ");
			}
			else if(timeArr[i] == 98){
				break;
			}
			else{
				System.out.print(timeArr[i] /10+ " ");
			}
		}
		System.out.println();
		//System.out.println("CPU Utilization: " + cpuUtilization + "% (Idle:"+ idlePercentage +"%)");
		//System.out.println("Throughput: " + throughput);
		Set set = turnAroundTime.entrySet();
	    Iterator i = set.iterator();
	    System.out.println();
	      while(i.hasNext()) {
	         Map.Entry processId = (Map.Entry)i.next();
	         System.out.print("Turn-around time Process " + processId.getKey() + ": ");
	         totalTurnAroundTime = totalTurnAroundTime + (Long)processId.getValue();
	         System.out.println(totalTurnAroundTime/ 10);
	      }
	    System.out.println("\nAverage Turnaround Time: " + totalTurnAroundTime / 30);  
	    //System.out.println("Mean waiting time in ready queue: " + totalWaitingTime + "s");
	}
}
