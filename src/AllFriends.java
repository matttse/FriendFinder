import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dhl.UserInputHandler;

/**
 * @author Matthew Tse
 *
 */
public class AllFriends {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File file = new File("C:/infixExpressions.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			int completeFlag = 0;
			String name = "";
			
			//while there is an expression to read
			//reads infix expression from input file
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");	
				

			}//end while expressions
			//cleanup
			stringBuffer.trimToSize();
			fileReader.close();
			// instantiate the handler
			UserInputHandler<String> processInput = new UserInputHandler<String>();
			// Option selector
			while (completeFlag == 0) {
				name = processInput.getAlphaNum("Enter then name of a person whose friends you want to find (enter 0 for exit): ");
				
				if (Integer.parseInt(name) == 0) {
					completeFlag = 0;
					System.out.println("Thank you and goodbye");
					exit(0);
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
