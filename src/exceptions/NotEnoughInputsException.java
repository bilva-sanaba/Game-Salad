package exceptions;

public class NotEnoughInputsException extends InputException {

	public static final String NOT_ENOUGH = "You did not enter enough inputs. You entered %d inputs but you needed %d inputs.";
	
	public NotEnoughInputsException(int neededNumInputs, int givenNumInputs) {
		super(String.format(NOT_ENOUGH, givenNumInputs, neededNumInputs));
	}



}
