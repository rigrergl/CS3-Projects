import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
this is student code, in particular the solve method
in this lab, it just reads the data and populates the 1D array.
results are displayed for the student by calling myView.updateView()
 */

public class ShelbyModel
{
	private int[]      monthData;
	private ShelbyView view;
	private String     fileName;

	public ShelbyModel ( String file, ShelbyView myView )
	{
		view = myView;
		fileName = file;
	}

	public void solve()
	{
		/*
		 *  student code here
		 */
		monthData = new int[13];
		File file = new File(fileName);
		
		
		Scanner sc;
		try {
			 sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		sc.nextLine();
		sc.nextLine();
		
		int lineNum = 3;
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			Scanner ln = new Scanner(line);
			ln.useDelimiter("/");
			ln.next();
			
			String month = ln.next();
			System.out.println(month + " " + line);
			lineNum++;
			monthData[Integer.parseInt(month)]++;
		}
		
		
		//int month
		//monthData[month-1] += 1

		view.updateView(monthData); //don't remove
	}
}
