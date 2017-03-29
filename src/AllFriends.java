import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dhl.UserInputHandler;

/**
 * @author Matthew Tse
 *
 */
/*
 *
6
             Smith Adams Steward Stein Mankell Yorst
Smith           0    0     0       0     1       0
Adams           0    0     1       0     0       1
Steward         0    1     0       0     0       0
Stein           0    0     0       0     1       0
Mankell         1    0     0       1     0       0
Yorst           0    1     0       0     0       0  
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
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			int completeFlag = 0;
			String name = "";
			int size = 0;
			int cnt = 0;
			String[] namesArray = null;
			String[] fileData;
			String data = "";
			//while there is an expression to read
			while ((line = bufferedReader.readLine()) != null) {
				if (cnt == 0) {//first line is number of people in file
					size = Integer.parseInt(line);
				} else if (cnt == 1) {//store names
					namesArray = new String[size];
					namesArray = line.split("\\s");
				} else {					
					stringBuffer.append(line);
					stringBuffer.append("\n");
				}
				
				
				cnt++;
			}//end while expressions
			//cleanup
			stringBuffer.trimToSize();
			fileReader.close();
			fileData = stringBuffer.toString().split("\\s+");
			System.out.println("Contents of file:");
//			System.out.println(size);
			for (int i = 0; i < fileData.length; i++) {
				//^\\s+|\\s+$|(\\s)+
				/*
				System.out.format("[%s]%n",
				    test.replaceAll("^ +| +$|( )+", "$1")
				*/
				data += fileData[i].trim().replaceAll(" ", "").replaceAll("\\s", "");
				System.out.println(fileData[i]);	
				
			}
			for (int i = 0; i < size; i++) {
				if (i == 0) {//remove tabbed or spaces from first name
					System.out.println(namesArray[i].trim().replaceAll(" ", "").replaceAll("\\s", ""));	
				} else {
					System.out.println(namesArray[i]);
				}
				
			}
			System.out.println(data);
			
			// instantiate the handler
			UserInputHandler<String> processInput = new UserInputHandler<String>();
			// Option selector
			while (completeFlag == 0) {
				name = processInput.getAlphaNum("Enter then name of a person whose friends you want to find (enter 0 for exit): ");
				try {
					if (Integer.parseInt(name) != 0) {
						System.out.println("Not a valid number");
					} else if (Integer.parseInt(name) == 0) {
						completeFlag = 0;
						System.out.println("Thank you and goodbye");
						exit(0);
					}
				} catch (Exception e) {
					System.out.println("Input Error caused by NFE: " + e);
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
