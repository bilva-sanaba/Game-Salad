package exceptions;

public class NumericInputException extends InputException {

	public static final String NUMERIC_MESSAGE = "Your input '%s' was not numeric.";
	
	public NumericInputException() {
		super(String.format(NUMERIC_MESSAGE, ""));
	}
	
	public NumericInputException(String offendingInput) {
		super(String.format(NUMERIC_MESSAGE, offendingInput));
	}

	
	
	
	
	
	
}
