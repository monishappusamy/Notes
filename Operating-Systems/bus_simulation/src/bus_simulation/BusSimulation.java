package bus_simulation;

public class BusSimulation{
	public static void main(String args[]){
		Bus bus1 = new Bus ("bus1");
		bus1.start();
		Bus bus2 = new Bus ("bus2");
		bus2.start();
		Bus bus3 = new Bus ("bus3");
		bus3.start();
		Bus bus4 = new Bus ("bus4");
		bus4.start();
		Bus bus5 = new Bus ("bus5");
		bus5.start();
		
		/*int[] stops = new int[15];
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName);
		try {
			for(int i=1; i<=stops.length;i++){
				System.out.println("The bus is at stop no."+i);
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after sleep");*/
	}
}
