package dhl;

/**
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/**
 * @author Matthew Tse
 *
 */
public class regexInput {
	
	int inputValue = -1;
	private String pattern = "";
	
	/**
	 * @param args
	 * @return 
	 */
	public int getInputValue(String input, String data) {		
		
		// Declare Pattern
		pattern = input;
		
		// pattern object
		Pattern checking = Pattern.compile(pattern);
		
		// value pair match object
		Matcher dataCheck = checking.matcher(data);
		//while there are still values to find, keep iterating
		while (dataCheck.find()) {
			if (dataCheck.group() != null) {
//				inputValue = dataCheck.start();	
				inputValue = dataCheck.end();//last index of matched value
			}
						
		} 
		
		
		return inputValue;
		
	}
	

	
}