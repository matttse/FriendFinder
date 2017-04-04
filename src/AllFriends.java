import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import dhl.UserInputHandler;
import dhl.ArrayQueue;

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
			ArrayList<int[]> friendValues = new ArrayList<int[]>();
			String[] namesArray = null;
			String[] row = null;
			int[] intData = null;
			AllFriends output = new AllFriends();
			//while there is an expression to read
			while ((line = bufferedReader.readLine()) != null) {
				if (cnt == 0) {//first line is number of people in file
					try {
						size = Integer.parseInt(line.trim().replaceAll("ï»¿", "").replaceAll("\\s+", ""));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (cnt == 1) {//store names
					namesArray = new String[size];
					namesArray = line.trim().replaceAll("Â", " ").replaceAll("\\s", " ").split(" ");
				} else {	
					row = line.trim().replaceAll(" ", " ").replaceAll("\\s+", " ").split(" ");
					intData = new int[(row.length-1)];//set array length offset name
					for (int i = 0; i < (row.length-1); i++) {
						intData[i] = Integer.parseInt(row[i+1]);
					}
					friendValues.add(intData);					
				}
				cnt++;
			}//end while expressions
			fileReader.close();
			
			System.out.println("Friend List:");
			
			for (int i = 0; i < namesArray.length; i++) {
				if (i == 0) {//remove tabbed or spaces from first name
					System.out.println(namesArray[i].trim().replaceAll(" ", "").replaceAll("\\s", ""));
				} else {
					System.out.println(namesArray[i]);

				}				
			}
			//for debugging purposes ONLY
			for (int j = 0; j < friendValues.size(); j++) {
				int[] temp = friendValues.get(j);
				for (int i = 0; i < temp.length; i++) {
					System.out.print(temp[i]);	
				}		
				System.out.print("\n");
			}
			//for debugging purposes ONLY			
			
			// instantiate the handler
			UserInputHandler<String> processInput = new UserInputHandler<String>();

			// Option selector
			while (completeFlag == 0) {
				name = processInput.getAlphaNum("Enter then name of a person whose friends you want to find (enter 0 for exit): ");				
				if (name.equals("0")) {
					completeFlag = 1;
					System.out.println("Thank you and goodbye");
					exit(0);
				} else {
					output.getFriendList(name, friendValues, namesArray);
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}//end trycatch
	}
	
	
	public String getFriendList(String name, ArrayList<int[]> friendValues, String[] namesArray) {
		String friendList = "";
		int idx = -1;
		int[] temp;
		ArrayList<Integer> friendListIdx = new ArrayList<Integer>();
		ArrayQueue friendQ = new ArrayQueue();
		//get index of desired name
		for (int i = 0; i < namesArray.length; i++) {			
			if (name.equals(namesArray[i])) {
				idx = i;
				break;
			}
		}
		if (idx != -1) {//check name found
			temp = friendValues.get(idx);//unpack friend identifier row
			//get friend indexes (non zero)
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] != 0) {
					friendList += namesArray[i] + ", ";	
					friendListIdx.add(i);
				}
			}
			if (friendList != "") {
				System.out.println(friendList);
			} else {
				friendList = "No Friends Found for " + name;
				System.out.println(friendList);					
			}
				
		} else {
			System.out.println("Name not found");
		}

		
		return friendList;
	}
	
	
	// standard system exit.
	public static void exit(int status) {
		System.exit(status);
		return;
	}//end system exit
}
