package application.businessLayer.utils;

/**
 * An util class about integer manipulation
 * @author Thomas
 *
 */
public class IntegerUtil {

	/**
	 * Check if a String is an intger
	 * @param s the string to be tested
	 * @return true if the string is an integer
	 */
	public static boolean isInteger(String s) {
		try { 
			
			Integer.parseInt(s); 
			
		} catch(NumberFormatException e) { 
			
			return false; 
			
		}
		
		return true;
	}
}
