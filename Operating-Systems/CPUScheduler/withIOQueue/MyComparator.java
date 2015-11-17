package problem3;

import java.util.Comparator;

class MyComparator implements Comparator<Long>{
	 
    @Override
    public int compare(Long burstTime1, Long burstTime2) {
        return burstTime1.compareTo(burstTime2);
    }
     
}
