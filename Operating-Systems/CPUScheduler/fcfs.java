import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;


public class CPUScheduler {
	Vector allProcs = new Vector();
	Vector jobQueue = new Vector();
	Vector readyQueue = new Vector();
	int idle = 0, busy = 0, currentTime = 0, procsIn = 0, procOut = 0, activeIndex = 0;
	Process activeJob = null;

	CPUScheduler(String filename) {
		Process proc = null;
		String s = null;
		double arrivalTime = 0, burstTime = 0;
		long p = 0;
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while ((s = input.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, ",");
				arrivalTime = Double.parseDouble(st.nextToken());
				burstTime = Double.parseDouble(st.nextToken());

				proc = new Process(arrivalTime, burstTime);
				allProcs.add(proc);
			}

		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
		LoadJobQueue(allProcs);
	}

	private void LoadJobQueue(Vector jobs) {
		Process p;
		double arTime = 0;
		for (int i = 0; i < jobs.size(); i++) {
			p = (Process) jobs.get(i);
			//System.out.println("Process " + i + "added to job queue at " + currentTime);
			jobQueue.add(p);
		}
	}

	public void startSimulate() {
		while (nextCycle())
			;
	}

	private boolean nextCycle() {
		boolean moreCycles;
		if (jobQueue.isEmpty()) {
			moreCycles = false;
		}
		else{
			LoadReadyQueue();
			moreCycles = true;
			if (readyQueue.isEmpty()) {
				System.out.print("Idle" + " ");
				idle++;
			} else {
				Schedule();
				busy++;
				cleanUp();
			}
			currentTime++;
		}
		return moreCycles;
	}

	private void LoadReadyQueue() {
		Process p;
		for (int i = 0; i < jobQueue.size(); i++) {
			p = (Process) jobQueue.get(i);
			if (p.getArrivalTime() == currentTime) {
				//System.out.println("Process " + i + "added to ready queue at " + currentTime);
				readyQueue.add(p);
				procsIn++;
			}
		}
	}

	private void Schedule() {
		fcfs(readyQueue);
		update();
	}

	void update() {
		Process p = null;
		activeJob.executing(currentTime);
		if(currentTime%10 == 0){
			System.out.print("P" +   activeJob.PID + " ");
		}
		for (int i = 0; i < readyQueue.size(); ++i) {
			p = (Process) readyQueue.get(i);
			if (p.getPID() != activeJob.getPID()) {
				p.waiting(currentTime);
			}
		}

	}

	private void cleanUp() {
		PurgeReadyQueue();
		PurgeJobQueue();
	}

	private void fcfs(Vector jq) {
		try {
			if (busy == 0 || activeJob.getBurstTime() == 0) {
				activeJob = findEarliestJob(jq);
				activeIndex = jq.indexOf(activeJob);
			}
		} catch (NullPointerException e) {
		}
	}

	Process findEarliestJob(Vector que) {
		Process p = null, earliest = null;
		int time = 0, arrTime = 0;

		for (int i = 0; i < que.size(); ++i) {
			p = (Process) que.get(i);
			time = p.getArrivalTime();
			if ((time < arrTime) || (i == 0)) {
				arrTime = time;
				earliest = p;
			}
		}
		return earliest;
	}

	void PurgeReadyQueue() {
		Process p;
		for (int i = 0; i < readyQueue.size(); i++) {
			p = (Process) readyQueue.get(i);
			if (p.isFinished() == true) {
				//System.out.println("Process " + i + "removed from Ready queue at time " + currentTime);
				readyQueue.remove(i);
				procOut++;
			}
		}
	}

	/** Get rid of jobs that are done */
	void PurgeJobQueue() {
		Process p;
		for (int i = 0; i < jobQueue.size(); i++) {
			p = (Process) jobQueue.get(i);
			if (p.isFinished() == true) {
				jobQueue.remove(i);
				//System.out.println("Process " + i + "removed from Job queue at time " + currentTime);
			}
		}
	}

}
