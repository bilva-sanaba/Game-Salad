package view;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AuthoringException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources" + File.separator;
	public static final String AUTHORING_RESOURCES = "AuthoringExceptions";
	public static final String AUTHORING_ERROR = "Authoring Exception";
	public static final String AUTHORING_HEADER = "Error in Authoring Environment";
	
	private ResourceBundle myErrors = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + AUTHORING_RESOURCES);
	private Alert error = new Alert(AlertType.INFORMATION);

	public AuthoringException(String message) {
		error.setTitle(AUTHORING_ERROR);
		error.setHeaderText(AUTHORING_HEADER);
		error.setContentText(myErrors.getString(message));
		error.showAndWait();
    }

	public AuthoringException(String message, Object ... values) {
		error.setTitle(AUTHORING_ERROR);
		error.setHeaderText(AUTHORING_HEADER);
		String errorMessage = String.format(myErrors.getString(message), values);
		error.setContentText(errorMessage);
		error.showAndWait();
	}
}
