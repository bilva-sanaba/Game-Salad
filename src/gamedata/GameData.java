package gamedata;

import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;

public class GameData {
    private DoubleProperty points;
    private DoubleProperty lives;
    private IRestrictedEntityManager restrictedEntityManager;
    private DoubleProperty level;
    
    public GameData(double p, double l, IRestrictedEntityManager rem, double lvl){
        points.set(p);
        lives.set(l);
        restrictedEntityManager = rem;
        level.set(lvl);
        
    }
    public DoubleProperty getPoints(){
        return points;
    }
    public DoubleProperty getLives(){
        return lives;
    }
    public IRestrictedEntityManager getRestrictedEntityManager(){
        return restrictedEntityManager;
    }
    public DoubleProperty getLevel(){
        return level;
    }
}