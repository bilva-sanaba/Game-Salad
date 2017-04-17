package voogasalad.util.paint;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChangePenWidthEvent implements IDrawingToolbar {
	
	private DrawingTool drawer;
	
	private static final double MINVAL = 1;
	private static final double MAXVAL = 300;
	private static final String TITLESTRING = "Change Pen Width";
	private static final String ERRORTITLE = "Pen Not Equipped";
	private static final String ERRORCONTENT = "You must have a pen equipped to change its title.";

	public ChangePenWidthEvent(DrawingTool d) {
		drawer = d;
	}
	
	public void event() {
		
		if (drawer.getDrawingToolType().equals(DrawingToolType.Pen)) {
			Pen p = (Pen) drawer;
			double w;
			Stage s = new Stage();
			Scene scene;
			BorderPane bp = new BorderPane();
			
			Slider slide = new Slider();
			slide.setValue(p.getWidth());
			slide.setMin(MINVAL);
			slide.setMax(MAXVAL);
			
			
			bp.getChildren().add(slide);
			scene = new Scene(bp);
			s.setScene(scene);
			s.setTitle(TITLESTRING);
			s.showAndWait();
			
			w = slide.getValue();
			p.setWidth(w);
		} else {
			PaintUtilityAlert pua = new PaintUtilityAlert(ERRORTITLE, ERRORCONTENT);
			pua.showAlert();
		}
	}

}
