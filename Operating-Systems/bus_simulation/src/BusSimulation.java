/**
* 
* The BusSimulation class acts as a start point to the application
* it starts a simulated clock (8 hours) and make calls to bus stops and buses 
* to update their function and also it prints minimum, average and maximum table 
* after an each successive hour (simulated time)
* 
* @author Monish Kumar Appusamy
* @version 1.0
* 
*/

package bus_simulation;
import org.apache.log4j.Logger;

public class BusSimulation{	
	private BusStops allStops = new BusStops();
	private BusImpl busImpl = new BusImpl();
	private int[][] queueStatus = new int[15][30000];
	private int[][] averageByHour = new int[8][15];
	private int[][] maximumByHour = new int[8][15];
	private int[][] minimumByHour = new int[8][15];
	private int counter = 0;
	
	final static Logger logger = Logger.getLogger(BusSimulation.class);
	
	/**
	 * The main program.
	 */ 
	
	public static void main(String args[]){
		logger.info("Inside main method");
		BusSimulation bus = new BusSimulation();
		bus.runSimulation();		
	}
	
	/**
	 * initializes 5 buses, adds random amount of passengers to queue, updates bus position for each second
	 * computes average, minimum, maximum amount of passengers in a queue for each successive hour
	 */ 
	public void runSimulation(){
		busImpl.createBus(5);
		for(int clock=1; clock<28801; clock++){
			logger.info("----------------------------------------------------------------");
			logger.info("At clock time: "+ clock);
			logger.info("----------------------------------------------------------------");
			logger.info("Operation 1: Calling add passengers");
			allStops.addPassengers(clock);
			//System.out.println("At clock" + clock + ":" + allStops.allStopsQueueSize());
			allStops.clearLog();
			
			logger.info("Operation 2: Updating bus positions");
			busImpl.updateBusPosition(clock, allStops);
			//System.out.println("At clock" + clock + ":" + busImpl.getLog());
			logger.info("At clock" + clock + ":" + busImpl.getLog());
			//System.out.println("At clock" + clock + ":" + busImpl.getBoardedPassengerLog());
			busImpl.clearLog();
			
			computeQueueSize(clock);
			if(clock%3600 == 0){
				computeAverage(clock, counter);
				computeMaximum(clock, counter);
				computeMinimum(clock, counter);
				counter = counter + 1;
			}
		}
		
		printAverageTable();
		printMinimumTable();
		printMaximumTable();
	}
	
	/**
	 * compute queue size for each second and add it to a matrix
	 * @param clock current simulated time
	 */ 
	public void computeQueueSize(int clock){
		for(int i=0; i<15; i++){
			queueStatus[i][clock] = allStops.getQueueSize(i);
		}
	}
	
	/**
	 * compute compute average size of a queue for an hour
	 * @param clock current simulated time
	 * @param counter increments 1 for each successive hour 
	 */ 
	public void computeAverage(int clock, int counter){
		for(int stopNumber = 0; stopNumber<15;stopNumber++){
			int temp = 0;
			for(int start = clock-3600; start < clock; start++){
				temp = temp + queueStatus[stopNumber][start];
			}
			averageByHour[counter][stopNumber] = temp / 3600;
		}
	}
	
	/**
	 * compute maximum size of a queue for an hour
	 * @param clock current simulated time
	 * @param counter increments 1 for each successive hour 
	 */ 
	public void computeMaximum(int clock, int counter){		
		for(int stopNumber = 0; stopNumber<15;stopNumber++){
			int temp = 0;
			for(int start = clock-3600; start < clock; start++){
				if(queueStatus[stopNumber][start] > temp){
					temp = queueStatus[stopNumber][start];
				}
			}
			maximumByHour[counter][stopNumber] = temp ;
		}
	}
	
	/**
	 * compute minimum size of a queue for an hour
	 * @param clock current simulated time
	 * @param counter increments 1 for each successive hour 
	 */
	public void computeMinimum(int clock, int counter){	
		for(int stopNumber = 0; stopNumber<15;stopNumber++){
			int tempVar = clock-3600;
			int temp = queueStatus[stopNumber][tempVar];
			for(int start = clock-3600; start < clock; start++){
				if(queueStatus[stopNumber][start] < temp){
					temp = queueStatus[stopNumber][start];
				}
			}
			minimumByHour[counter][stopNumber] = temp;
		}
	}
	
	/**
	 * prints final average table in a multi-dimensional matrix
	 */ 
	public void printAverageTable(){
		System.out.println();
		System.out.println("y axis represents hour no.");
		System.out.println("x axis represents bus stop number");
		System.out.println("Average queue size of bus stops for hours (1 to 8)...");
		for(int i=0; i<8;i++){
			System.out.print((i+1)+" |");
			for(int stopNumber = 0; stopNumber < 15; stopNumber++){
				System.out.print(" " + averageByHour[i][stopNumber]);
			}
			System.out.println();
			}
	}
	
	/**
	 * prints final minimum table in a multi-dimensional matrix
	 */ 
	public void printMinimumTable(){
		System.out.println();
		System.out.println("Minimum queue size of bus stops for hours (1 to 8)...");
		for(int i = 0; i<8;i++){
			System.out.print((i+1)+" |");
			for(int stopNumber = 0; stopNumber < 15; stopNumber++){
				System.out.print(" " + minimumByHour[i][stopNumber]);
			}
			System.out.println();
			}
	}
	
	/**
	 * prints final maximum table in a multi-dimensional matrix
	 */ 
	public void printMaximumTable(){
		System.out.println();
		System.out.println("Maximum queue size of bus stops for hours (1 to 8)...");
		for(int i = 0; i<8;i++){
			System.out.print((i+1)+" |");
			for(int stopNumber = 0; stopNumber < 15; stopNumber++){
				System.out.print(" " + maximumByHour[i][stopNumber]);
			}
			System.out.println();
			}
	}
}
