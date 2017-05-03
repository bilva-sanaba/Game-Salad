package view;

public class AuthoringException extends RuntimeException {
	
	public static final String NO_IMAGE = "Choose an image you wild animal";

	private static final long serialVersionUID = 1L;

	public AuthoringException(String message) {
        super(message);
    }

	public AuthoringException(String message, Object ... values) {
        super(String.format(message, values));
    }

	public AuthoringException(Throwable cause) {
        super(cause);
    }
}
