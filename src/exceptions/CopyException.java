// This entire file is part of my masterpiece.
// Bilva Sanaba
// This is a simple custom exception class which provides more clear information on errors related to the deep copy of 
// componets (deep copy is used for my rewind feature as well as saving) 
// Additionally it uses a resource bundle in order to avoid hard coded unmodifiable strings.
// Note: At some point in error throwing, outside of the masterpiece a try-catch was used to stop a chain of throws in an
// effort to save time and avoid editing others code for the sake of my own masterpiece.
package exceptions;

import java.util.ResourceBundle;

public class CopyException extends Exception{
	private static final ResourceBundle exceptionBundle = ResourceBundle.getBundle("/resources/CopyExceptions");
	public static final String DEAFULT_WARNING = exceptionBundle.getString("CopyException");
	public CopyException(String message){
		super(message);
	}
}
