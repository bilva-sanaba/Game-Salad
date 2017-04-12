package gameObject;
/**
 * 
 * @author Jacob
 *
 */
public class GameConfig extends AbstractGameConfig{
	
	public GameConfig(int myID, int lives){
		super(myID, lives, 0);
	}
	
	public int getID(){
		return myID;
	}
	
	public int getLives(){
		return lives;
	}
	
	public int getScore(){
		return score;
	}
	
	public void increaseScore(){
		score++;
	}
	
	public void loseLife(){
		lives--;
	}
}
