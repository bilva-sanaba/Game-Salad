package exceptions;

public abstract class InputException extends Exception {

	public static final String GENERIC_STRING = "Your input was not of the correct type.";

	
	public InputException(String message) {
		super(message);
		
	}
	
	public String format(String toFormat, String stringToInsert) {
		return String.format(toFormat, stringToInsert);
	}
	
	
	
	

}
