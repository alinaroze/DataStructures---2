import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Skyline is created
 * 
 * @author Alina Rozenbaum
 * 
 */
public class skyline {

	private String skyfile1 = "src/sky1.dat";
	private String skyfile2 = "src/sky2.dat";
	private String skyfile3 = "src/sky3.dat";

	/**
	 * Main method to run the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		skyline sky = new skyline();
		sky.run();
	} // end main

	/**
	 * The program
	 */
	public void run() {
		skyline sky = new skyline();
		sky.skyline(skyfile1);
		sky.skyline(skyfile2);
		sky.skyline(skyfile3);

	}

	/**
	 * Creates the skyline
	 * 
	 * @param file - File to be read from
	 */
	public void skyline(String file) {

		int[] skyline = new int[100]; // blank skyline

		for (int i = 0; i < skyline.length; i++) {
			skyline[i] = 0;
		}
		try {
			skyline = readBuilding(skyline, file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		for (int i = 0; i < skyline.length; i++) {
			System.out.print(skyline[i] + ",");

		}
		System.out.println();
		skylineNotation(skyline);
	}

	/**
	 * Read building data from files, add to ongoing skyline
	 * 
	 * @param skyline
	 *            []
	 * @return modified skyline
	 * @throws FileNotFoundException
	 */
	public int[] readBuilding(int[] skyline, String file)
			throws FileNotFoundException {
		Scanner sc = new Scanner(new File(file));
		while (sc.hasNextInt()) {
			int L = sc.nextInt();
			int H = sc.nextInt();
			int R = sc.nextInt();
			for (int i = L; i < R; i++) {
				if (H > skyline[i]) {
					skyline[i] = H;
				}
			}
		}// end while
		sc.close();
		return skyline;

	}

	/**
	 * Changes skyline array into skyline notation for printing.
	 * 
	 * @param skyline
	 */
	public void skylineNotation(int[] skyline) {
		int[] skylineNotation = new int[skyline.length];
		for (int i = 0; i < skylineNotation.length; i++) {
			skylineNotation[i] = 0;
		}
		int height = 1; // middle integer in building notation, increments by 3
		int pos = 0;
		if (skyline[0] != 0) // find leftmost starting point for skyline if it
								// is at zero
			skylineNotation[0] = 0;
		else {
			int s = 0;
			boolean done = false;
			while (!done) { // find the leftmost starting point for skyline non
							// zero
				if (skyline[s] == 0) {
					s++;
				} else {
					skylineNotation[1] = skyline[s];
					skylineNotation[0] = s;
					done = true;
					pos = s;
				}
			}
		}

		for (int i = 1; i < skyline.length; i++) { // find the rest
			if (skyline[i] != skyline[i - 1]) { // building changes height at
												// skyline[i]
				skylineNotation[height] = skyline[i]; // height selected for
														// this segment of
														// skyline
				if (height < skyline.length - 3)
					height = height + 3; // increment to next segment

				skylineNotation[pos - 1] = i;
				if (pos > 2) {
					skylineNotation[pos - 2] = i;
				}
				pos = pos + 3;
			}
		}
		for (int i = 0; i < skyline.length; i++) {
			System.out.print(skylineNotation[i]);
			System.out.print(", ");
			if ((i + 1) % 3 == 0)
				System.out.println();
		}

	}
}// end class