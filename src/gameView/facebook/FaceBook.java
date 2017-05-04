package gameView.facebook;

import gameView.tools.FrontEndException;
import gameView.userManagement.UserData;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.restfb.*;
import com.restfb.json.JsonObject;
import com.restfb.types.User;

public class FaceBook {
	
	private final String CHROME_DRIVER = "data/chromedriver";
	private final String APP_ID = "1995068140770452";
	//private final String APP_SECRET = "de3819d4811660b10cb879c1f9a891f6";
	private final String RESPONSE_URL = "https://users.cs.duke.edu/~rcd/";//"https://www.google.com";
	private final String LOGIN_URL = "https://www.facebook.com/dialog/oauth?"
			+ "client_id=" + APP_ID
			+ "&redirect_uri=" + RESPONSE_URL
			+ "&response_type=token"
			+ "&scope=public_profile";//,publish_pages,manage_pages";
	
	private String ACCESS_TOKEN; 
	private FacebookClient myClient;

	
	public FaceBook() {
		
	}
	
	/**
	 * Returns a new UserData object from the facebook client and the user's log-in. Because Facebook runs through a specific method in 
	 * userManager, uses a generic 'password' to sign in
	 * @return
	 * @throws FrontEndException
	 */
	public UserData login() throws FrontEndException {
		UserData userData;
		WebDriver driver = initializeDriver();
		driver.get(LOGIN_URL);
		while (true) {
			if (!driver.getCurrentUrl().contains("facebook")) {
				ACCESS_TOKEN = regexMatch("access[_]token=(\\w)+", driver.getCurrentUrl()).split("=")[1];
				myClient = new DefaultFacebookClient(ACCESS_TOKEN, Version.LATEST);
				User user = myClient.fetchObject("me", User.class);
				JsonObject profilePicture = myClient.fetchObject("me/picture", JsonObject.class, Parameter.with("redirect", "false"));
				userData = new UserData(user.getName(), "password", 
						(String) profilePicture.get("data").asObject().get("url").asString());
				driver.close();
				break;
			}
		}
		return userData;
	}
	
	private String regexMatch(String regex, String original) {
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(original);
		match.find();
		return match.group();
	}
	
	private WebDriver initializeDriver() {
		File driverFile = new File(CHROME_DRIVER);
		driverFile.setExecutable(true);
		System.setProperty("webdriver.chrome.driver", driverFile.toString());
		WebDriver driver = new ChromeDriver();
		return driver;
	}
}
