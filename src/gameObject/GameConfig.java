package gameObject;
/**
 * 
 * @author Jacob
 *
 */
public class GameConfig extends AbstractGameConfig{
	
	public GameConfig(int myID, int lives, int score){
		super(myID, lives, score);
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
