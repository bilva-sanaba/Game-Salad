package gameView.tools;

public enum UserDataEnum {

	USERNAME ("name"),
	PASSWORD ("password"),
	PICTURE ("picture");
	
	
	private final String myKey;
	
	
	UserDataEnum(String s) {
		this.myKey = s;
	}
	
	
	public String getKey() {
		return myKey;
	}
}
