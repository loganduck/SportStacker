import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Client driver class used to take input, creates a SportStacker, and output results. Reads in a file
 * as args[0]. ex.txt is an example file that would be read in. Line 1 = name and line 2 = times to 
 * add to an array.
 *
 * The following class implements the methods in SportStacker. Output and implementations
 * do not follow the attached project description exactly to stability and user readability.
 * 
 * @author Logan Duck (logan.duck@yahoo.com)
 * @version 01/06/18
 */
public class SportStackerApp {
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0) {
			System.out.println("File name expected as run arguement.\nProgram ending...\n");
			System.exit(1);
		}
		String file = args[0];
		Scanner scanFile = new Scanner(new File(file));
		String name = scanFile.nextLine();
		int numOfTimes = 0;
		double[] times = {};
		Scanner scan = new Scanner(System.in);
		while (scanFile.hasNext()) {
			scanFile.nextDouble();
			numOfTimes++;
		}
		times = new double[numOfTimes];
		scanFile = new Scanner(new File(file));
		name = scanFile.nextLine();
		for (int i = 0; i < numOfTimes; i++) {
				times[i] = scanFile.nextDouble();
		}
		SportStacker stacker = new SportStacker(name, numOfTimes, times);
		System.out.println("File read in and SportStacker created.\nStudentInvoice System Menu.");
		System.out.println("P- Print Report\nA- Add A New Time\n"
			+ "D- Delete Worst Time\nF- Find Fastest Time\nQ- Quit");
		char c;
		do {
			scan = new Scanner(System.in);
			System.out.print("\nEnter Code [P, A, D, F, or Q]: ");
			c = scan.nextLine().toUpperCase().charAt(0);
			switch (c) {
				case 'P':
					System.out.println(stacker.toString());
					break;

				case 'A':
					System.out.print("Time to add: ");
					try {
						double timeToAdd = scan.nextDouble();
						stacker.addTime(timeToAdd);
					} catch (InputMismatchException e) {
						System.out.println("Invalid Input.\nProgram ending...\n");
						return;
					}
					System.out.print("Time added successfully.\n");
					break;

				case 'D':
					scanFile = new Scanner(new File(file));
					System.out.println("Slowest time deleted: " + stacker.removeSlowestTime());
					break;

				case 'F':
					System.out.println("Fastest time: " + stacker.findFastestTime());
					break;
					
				case 'Q':
					System.out.println("Program ended.\n");
					return;

				default:
					System.out.println("Unknown input. Please try again.");
			} 
		} while (c != 'Q');
	}
}