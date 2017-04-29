import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import voogasalad.util.paint.ImageRefiner;

public class ImageRefinerTest {
	private Stage myStage;
	private ImageRefiner myImageEditor;
	public ImageRefinerTest(Stage arg){
		Group root = new Group();
		myStage= arg;
		myImageEditor = new ImageRefiner();
		Scene myScene = new Scene(root,500,500);
		myStage.setScene(myScene);
		Image myImage= new Image(getClass().getClassLoader().getResourceAsStream("platform_tile_053.png"),300,300,false,false); 
		Shape imageBounds = myImageEditor.getBoundedShape(myImage);
		
		root.getChildren().add(imageBounds);
//		root.getChildren().add(new ImageView(myImage));
//		root.getChildren().add(imageBounds);
		myStage.show();
	}

}
