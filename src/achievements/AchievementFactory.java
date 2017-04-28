package achievements;

public class AchievementFactory {
	
	public AchievementFactory(){
	}
	
	public Achievement genAchievement(String type) throws ClassNotFoundException{
		Class<?> clazz = Class.forName("achievements." + type);
		try{
			return (Achievement) clazz.getDeclaredConstructor().newInstance();
		}
		catch(Exception e){
			return null;
		}
	}
}
