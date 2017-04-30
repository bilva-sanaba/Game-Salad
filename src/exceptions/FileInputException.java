package exceptions;

public class FileInputException extends InputException {
	
	public static final String FILE_EXCEPTION = "Your input '%s' could not be turned into a file";

	public FileInputException() {
		super(String.format(FILE_EXCEPTION, ""));
	}
	
	public FileInputException(String offendingFilePath) {
		super(String.format(FILE_EXCEPTION, offendingFilePath));
	}

}
