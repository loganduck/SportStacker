import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Sport Stacking consists of an individual or team stacking and un-stacking cups in
 * pre-determined sequences, competing against the clock or another player. This project
 * will create two classes: (1) SportStacker is a class representing a competition sport
 * stacker and (2) SportStackerApp is a driver class with a main method that reads input
 * from a file specified as a command line argument, creates a SportStacker object, then
 * provides options for printing the report, adding a new time, deleting the worst time;
 * finding the fastest time, and quitting the application.
 *
 * The following class implements the methods in SportStacker. Output and implementations
 * do not follow the attached project description exactly to stability and user readability.
 *
 * @author Logan Duck (logan.duck@yahoo.com)
 * @version 01/06/18
 */
public class SportStacker {
	private String name;
	private double[] times;
	private int numOfTimes;

	public SportStacker(String nameIn, int numOfTimesIn, double[] timesIn) {
		name = nameIn;
		numOfTimes = numOfTimesIn;
		times = timesIn;
	}

	public String getName() {
		return name;
	}

	public double[] getTimes() {
		return times;
	}

	public int getNumTimesRecorded() {
		return numOfTimes;
	}

     public String toString() {
     	String timesStr = "";
     	for (double time : times) {
     		timesStr += time + " ";
     	}
     	String output = "Sport Stacker Name: " + getName()
     		+ "\nTimes: " + timesStr.trim()
     		+ "\nAverage Time: " + computeAvgTimes();
     	return output;
     }

     /* addTime adds a time to the times array. Increasing the index by 1 using the
      * increaseSize method if adding the value exceeds index range.
      */
     public void addTime(double time) {
     	if (numOfTimes >= times.length)
     		increaseSize();
     	times[times.length - 1] = time;
     }

     public void increaseSize() {
     	double[] temp = new double[times.length + 1];
     	for (int i = 0; i < times.length; i++) {
     		temp[i] = times[i];
     	}
     	times = temp;
     }

     /* finds the slowest time of the times and returns it. Once the slowest time is
      * found, all elements to the right of the index of the value is shifted to the
      * left. A new array is created to adjust the size of the array when a value is
      * removed.
      */
     public double removeSlowestTime() {
          if (times.length == 0) {
               System.out.println("Error: times empty.\nProgram ending...\n");
               System.exit(1);
          }
          if (times.length == 1) {
               System.out.println("Error: only one time remaining.\nProgram ending...\n");
               System.exit(1);
          }
          double slowest = times[0];
          int indexOfSlowest = 0;
          int length = times.length - 1;
          for (int i = 0; i < times.length; i++) {
               if (times[i] > slowest) {
                    slowest = times[i];
                    indexOfSlowest = i;
               }
          }
          for (int j = 0; j < times.length - 1; j++) {
               if (j >= indexOfSlowest)
                    times[j] = times[j + 1];
          }
          double[] timesAfterDelete = new double[length];
          for (int k = 0; k < length; k++) {
               timesAfterDelete[k] = times[k];   
          }
          times = timesAfterDelete;
          return slowest;
     }

     public double findFastestTime() {
     	double fastest = times[0];
     	for (int i = 0; i < times.length; i++) {
     		if (times[i] < fastest)
     			fastest = times[i];
     	}
     	return fastest;
     }

     public double computeAvgTimes() {
     	double sum = 0;
     	int divisor = times.length;
     	for (double time : times) {
     		sum += time;
     	}
     	DecimalFormat format = new DecimalFormat("#0.00");
     	return Double.parseDouble(format.format(sum / divisor));
     }
}