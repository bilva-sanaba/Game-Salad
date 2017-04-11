package voogasalad.util.reflection;

/**
 * A general exception that represents all possible Java Reflection exceptions.
 *
 * @author Robert C. Duvall
 */
public final class ReflectionException extends RuntimeException {
    // for serialization
    private static final long serialVersionUID = 1L;
    
    public static final String COMPONENT_REFLECTION_ERROR = "Error in component reflection, check package and class names";
    public static final String EVENT_REFLECTION_ERROR = "Error in event reflection, check package and class names";

	/**
	 * Create an exception based on an issue in our code.
	 */
	public ReflectionException(String message, Object... args) {
		super(format(message, args));
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public ReflectionException(Throwable cause, String message, Object... args) {
		super(format(message, args), cause);
	}

	// remove duplicate code, also placeholder for future improvements (like
	// logging)
	private static String format(String message, Object... args) {
		return String.format(message, args);
	}
}
