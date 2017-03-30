import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

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
			AllFriends output = new AllFriends();
			//while there is an expression to read
			while ((line = bufferedReader.readLine()) != null) {
				if (cnt == 0) {//first line is number of people in file
					size = Integer.parseInt(line);
				} else if (cnt == 1) {//store names
					namesArray = new String[size];
					namesArray = line.replaceAll("\\s+", " ").split(" ");
				} else {	
					row = line.split("\\s+");
					intData = new int[(row.length-1)];//set array length offset name
					for (int i = 0; i < (row.length-1); i++) {
						intData[i] = Integer.parseInt(row[i+1]);
					}
					friendValues.add(intData);					
				}
				cnt++;
			}//end while expressions
			fileReader.close();
			
			System.out.println("Contents of file:");
			
			for (int i = 0; i < namesArray.length; i++) {
				if (i == 0) {//remove tabbed or spaces from first name
					System.out.println(namesArray[i].trim().replaceAll(" ", "").replaceAll("\\s", ""));
//					friendList.put(namesArray[i].trim().replaceAll(" ", "").replaceAll("\\s", ""), arg1);	
				} else {
					System.out.println(namesArray[i]);

				}				
			}
			for (int j = 0; j < friendValues.size(); j++) {
				int[] temp = friendValues.get(j);
				for (int i = 0; i < temp.length; i++) {
					System.out.print(temp[i]);	
				}
				System.out.print("\n");
				
			}
//			Set<Entry<String, ArrayList>> set = friendList.entrySet();
//			
//			Iterator<Entry<String, ArrayList>> idx = set.iterator();
//			
//			while(idx.hasNext()) {
//				Map.Entry<String, ArrayList> fList = (Map.Entry<String, ArrayList>)idx.next();
//				
//				System.out.println(fList.getValue());
//			}
			
			
			// instantiate the handler
			UserInputHandler<String> processInput = new UserInputHandler<String>();
			// instantiate regex runner object
			regexInput myVal = new regexInput();
			// Option selector
			while (completeFlag == 0) {
				name = processInput.getAlphaNum("Enter then name of a person whose friends you want to find (enter 0 for exit): ");
				if (output.getFriendList(name, friendValues, namesArray) != null) {
					output.getFriendList(name, friendValues, namesArray);	
				} else if (name.equals("0")) {
					completeFlag = 1;
					System.out.println("Thank you and goodbye");
					exit(0);
				} else {
					System.out.println("Name Not Found, Try Again.");
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}//end trycatch
	}
	
	
	public String getFriendList(String name, ArrayList<int[]> friendValues, String[] namesArray) {
		String friendList = null;
		int idx = -1;
		for (int i = 0; i < namesArray.length; i++) {
			if (name.equals(namesArray[i])) {
				idx = i;
				break;
			}
		}
		
		
		return friendList;
	}
	
	
	// standard system exit.
	public static void exit(int status) {
		System.exit(status);
		return;
	}//end system exit
}
