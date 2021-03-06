import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dhl.UserInputHandler;

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
			String line;
			int completeFlag = 0;
			String name = "";
			int size = 0;
			int cnt = 0;
			ArrayList<int[]> friendValues = new ArrayList<int[]>();
			String[] namesArray = null;
			String[] row = null;
			int[] intData = null;
			//while there is an expression to read
			while ((line = bufferedReader.readLine()) != null) {
				if (cnt == 0) {//first line is number of people in file
					try {
						size = Integer.parseInt(line.trim().replaceAll("﻿", "").replaceAll("\\s+", ""));
					} catch (Exception e) {
						e.printStackTrace();
						System.out.print("Problem parsing data file @line 0");
					}
				} else if (cnt == 1) {//store names
					namesArray = new String[size];
					namesArray = line.trim().replaceAll("�", " ").replaceAll("\\s+", " ").split(" ");
				} else {	
					row = line.trim().replaceAll(" ", " ").replaceAll("\\s+", " ").split(" ");
					intData = new int[(row.length-1)];//set array length offset name
					for (int i = 0; i < (row.length-1); i++) {
						try {
							intData[i] = Integer.parseInt(row[i+1]);	
						} catch (Exception e) {
							e.printStackTrace();
							System.out.print("Problem parsing data file @line 3");
						}
					}
					friendValues.add(intData);					
				}
				cnt++;
			}//end while expressions
			fileReader.close();
			
			System.out.println("Friend List:");
			
			for (int i = 0; i < namesArray.length; i++) {
				if (i == 0) {//remove tabbed or spaces from first name
					System.out.println(namesArray[i].trim().replaceAll("�", "").replaceAll("\\s", ""));
				} else {
					System.out.println(namesArray[i]);

				}				
			}
			//for debugging purposes ONLY
//			for (int j = 0; j < friendValues.size(); j++) {
//				int[] temp = friendValues.get(j);
//				for (int i = 0; i < temp.length; i++) {
//					System.out.print(temp[i]);	
//				}		
//				System.out.print("\n");
//			}
			//for debugging purposes ONLY			
			
			// instantiate the handler
			UserInputHandler<String> processInput = new UserInputHandler<String>();

			// Option selector
			while (completeFlag == 0) {
				//process input
				name = processInput.getAlphaNum("Enter then name of a person whose friends you want to find (enter 0 for exit): ");				
				if (name.equals("0")) {//exit code
					completeFlag = 1;//exit loop
					System.out.println("Thank you and goodbye");//message
					exit(0);//exit method
				} else {//prints/gets friend list
					getFriendList(name, friendValues, namesArray);
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("Problem with read io file.");
		}//end trycatch
	}
	
	/*
	 * @Name: getFriendList
	 * 
	 * @Function/Purpose: get friends and friends' friends
	 * 
	 * @Parameters:
	 * 		{vc} name
	 * 		{ArrayList<int[]} friendValues
	 * 		{String[]} namesArray
	 * 
	 * @Additionl Comments:
	 * 
	 * @Return idx
	 */
	public static int getFriendList(String name, ArrayList<int[]> friendValues, String[] namesArray) {
		int idx = -1;
		int[] intData = null;
		ArrayList<String> friendList = new ArrayList<String>();
		int degrees = 1;

		//check name
		for (int i = 0; i < namesArray.length; i++) {			
			if (name.equals(namesArray[i])) {
				idx = i;
				break;
			}
		}
		if (idx != -1) {//check name found otherwise skip recusrion
			//get friend indexes (non zero)
			for (int i = 0; i < namesArray.length; i++) {
					intData = friendValues.get(getPersonId(namesArray, name));//set friend data row
					if (!friendList.contains(name)) {//only add if name is not in list
						friendList.add(name);	
					}
					findFriends(intData, namesArray, friendValues, name, friendList, degrees);
			}			
					
		} else {//default message
			System.out.println("Name not found");
		}
		
		
		return idx;
	}//end getFriendList method
	
	/*
	 * @Name: findFriends
	 * 
	 * @Function/Purpose: find and print friends
	 * 
	 * @Parameters:
	 * 		{int[]} friendData
	 * 		{String[]} namesArray
	 * 		{ArrayList<int[]} friendValues
	 * 		{vc} name
	 * 		{ArrayList<String>} friendList 
	 * 		{int} degrees
	 * 
	 * @Additionl Comments:
	 * 
	 * @Return null
	 */	
	public static void findFriends(int[] friendData, String[] namesArray, ArrayList<int[]> friendValues
			, String name, ArrayList<String> friendList, int degrees) {
		for (int i = 0; i < friendData.length; i++) {//loop through each row of whether a friend is marked
			if (friendData[i] != 0 || !friendList.contains(name)) {//check if already been visited, is ownself, and is a friend
				name = namesArray[i];//set found name to current name
				if (!friendList.contains(name)) {//only add if name is not in list			
					friendList.add(name);
					System.out.println(name);
					if (degrees == 1) {//check how many degrees of separation
						friendData = friendValues.get(i);//set new row to check
						degrees = degrees - 1;//decrement to avoid finding infinite recusrion
						findFriends(friendData, namesArray, friendValues, name, friendList, degrees);//recursive						
					}

				}	

			} 
		}
	}//end findFriends method
	
	/*
	 * @Name: getPersonId
	 * 
	 * @Function/Purpose: get name index
	 * 
	 * @Parameters:
	 * 		{String[]} namesArray
	 * 	 	{vc} name
	 * 
	 * @Additionl Comments:
	 * 
	 * @Return idx
	 */
	public static int getPersonId(String[] namesArray, String name) {
		int idx = -1;
		//get index of desired name
		for (int i = 0; i < namesArray.length; i++) {			
			if (name.equals(namesArray[i])) {
				idx = i;
				break;//exit on find
			}
		}
		return idx;
	}//end getPersonId method
	
	// standard system exit.
	public static void exit(int status) {
		System.exit(status);
		return;
	}//end system exit
}
