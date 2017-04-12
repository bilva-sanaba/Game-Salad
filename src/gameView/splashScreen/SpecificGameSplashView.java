package gameView.splashScreen;


import entity.SplashEntity;
import gameView.UIView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class SpecificGameSplashView extends SplashView{
	
	private String background;
	
	public SpecificGameSplashView(UIView view, SplashEntity se){
		super(view, se);
	}
	
	public void addBackground(){
		background = getSE().getBackgroundFilePath();
		getRoot().setBackground(makeBackground(background));
	}
	
	private Background makeBackground(String bg){
		System.out.println(bg);
		Image background = new Image(getClass().getClassLoader().getResourceAsStream(bg));
		BackgroundSize size = new BackgroundSize(100, 100, true, true, true, false);
		BackgroundImage image = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		return new Background(image);
	}
}