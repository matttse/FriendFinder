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
			ArrayQueue namesQ = new ArrayQueue();
			
			
			//while there is an expression to read
			//reads infix expression from input file
			while ((line = bufferedReader.readLine()) != null) {
				if (cnt == 0) {//first line is number of people in file
					size = Integer.parseInt(line);
				} 
				stringBuffer.append(line);
				stringBuffer.append(";");	
				cnt++;
			}//end while expressions
			//cleanup
			stringBuffer.trimToSize();
			fileReader.close();
//			namesQ.add(stringBuffer.toString().split(";"));
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

			System.out.println("Contents of file:");		
			System.out.println(stringBuffer.toString());
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
