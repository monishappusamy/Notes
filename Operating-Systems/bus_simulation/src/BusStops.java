package bus_simulation;
import org.apache.log4j.Logger;

public class BusStops {
	final static Logger logger = Logger.getLogger(BusSimulation.class);
	PassengerQueue[] stop = new PassengerQueue[15];
	String log = "";
	
	public BusStops(){
		for(int i=0; i<stop.length; i++)
			stop[i] = new PassengerQueue("stop"+i);
	}
	
	public void addPassengers(int clock){
		if (clock % 12 == 0){
			for(int i=0; i<stop.length; i++){
				logger.info("---------------------------");
				logger.info("Adding passengers to queue for stop no: " + i);
				logger.info("Queue size before adding is: " + stop[i].size());
				int totalPassengersAdded = stop[i].checkforNewPassenger(clock);
				logger.info("Total passengers added: " + totalPassengersAdded);
				logger.info("Queue size after adding is: " + stop[i].size());
				log = log + " " + stop[i].size(); 
			}
		}
		else{
			logger.info("we are not adding passengers this time at: "+ clock);
		}
	}
	
	public PassengerQueue getStopHandle(int stopNumber){
		return stop[stopNumber];
	}
	
	public int getQueueSize(int i){
		return stop[i].size();
	}
	
	public void clearLog(){
		log = "";
	}
	
	public String allStopsQueueSize(){
		return log;
	}
}
