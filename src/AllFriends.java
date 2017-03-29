import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import dhl.UserInputHandler;
import dhl.regexInput;

/**
 * @author Matthew Tse
 *
 */
/*
 *
6
             Smith Adams Steward Stein Mankell Yorst
Smith           0    0     0       0     1       0
Adams           0    0     1       0     0       1
Steward         0    1     0       0     0       0
Stein           0    0     0       0     1       0
Mankell         1    0     0       1     0       0
Yorst           0    1     0       0     0       0
 * */

public class AllFriends {

	/**
	 * @param args
	 */
	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		try {
			File file = new File("C:/friendMatrix.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			StringBuffer stringBuffer = new StringBuffer();
			String line;
			int completeFlag = 0;
			String name = "";
			int size = 0;
			int cnt = 0;
			// instantiate the the customer list object	
			TreeMap<String, ArrayList> friendList = new TreeMap<String, ArrayList>();
			ArrayList<int[]> friendValues = new ArrayList<int[]>();
			String[] namesArray = null;
			String[] row = null;
			int[] intData = null;
			//while there is an expression to read
			while ((line = bufferedReader.readLine()) != null) {
				if (cnt == 0) {//first line is number of people in file
					size = Integer.parseInt(line);
				} else if (cnt == 1) {//store names
					namesArray = new String[size];
					namesArray = line.split("\\s");
				} else {	
//					line = line.replaceAll(" ", "");
					row = line.split("\\s+");
					intData = new int[row.length];
					for (int i = 1; i < row.length; i++) {
						if (i == 1) {
							intData[0] = Integer.parseInt(row[i+1]);	
						} else {
							intData[i] = Integer.parseInt(row[i]);
						}
						
					}
					friendValues.add(intData);
					friendList.put(namesArray[cnt], friendValues);
				}
				
				
				cnt++;
			}//end while expressions
			fileReader.close();
			
			System.out.println("Contents of file:");
			
			for (int i = 0; i < size; i++) {
				if (i == 0) {//remove tabbed or spaces from first name
					System.out.println(namesArray[i].trim().replaceAll(" ", "").replaceAll("\\s", ""));
//					friendList.put(namesArray[i].trim().replaceAll(" ", "").replaceAll("\\s", ""), arg1);	
				} else {
					System.out.println(namesArray[i]);
				}				
			}
			for (int i = 0; i < size; i++) {
				System.out.println(friendList.get(namesArray[i]).iterator());
			}
			
			// instantiate the handler
			UserInputHandler<String> processInput = new UserInputHandler<String>();
			// instantiate regex runner object
			regexInput myVal = new regexInput();
			// Option selector
			while (completeFlag == 0) {
				name = processInput.getAlphaNum("Enter then name of a person whose friends you want to find (enter 0 for exit): ");


				if (name.equals("0")) {
					completeFlag = 1;
					System.out.println("Thank you and goodbye");
					exit(0);
				} 				
			}


		} catch (IOException e) {
			e.printStackTrace();
		}//end trycatch
	}
	// standard system exit.
	public static void exit(int status) {
		System.exit(status);
		return;
	}//end system exit
}
