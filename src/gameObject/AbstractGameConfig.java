package gameObject;

import java.util.Observable;
/**
 * 
 * @author Jacob
 *
 */
public class AbstractGameConfig extends Observable {
	
	protected int myID;
	protected int lives;
	protected int score;
	
	public AbstractGameConfig(int myID, int lives, int score){
		this.myID = myID;
		this.lives = lives;
		this.score = score;
	}
}
