package bus_simulation;

public class Bus implements Runnable{
	Thread bus;
	String threadName;
	
	Bus(String threadName){
		this.threadName = threadName;
	}
	
	public void run(){
		printNumbers(Thread.currentThread().getName());
		System.out.println("inside run");
	}
		
	public void printNumbers(String threadName){
		System.out.println(threadName);
	}
	
	public void start(){
		bus = new Thread(this, threadName);
		bus.start();
	}
}
