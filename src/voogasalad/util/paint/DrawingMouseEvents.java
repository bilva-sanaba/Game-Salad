//package voogasalad.util.paint;
//
//import components.entityComponents.IKeyExpression;
//import javafx.event.EventHandler;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.shape.Shape;
//
//public enum DrawingMouseEvents {
//Rectangle(new EventHandler<MouseEvent>() {
//	@Override
//	public void handle(MouseEvent mouseEvent) {
//		if(mouseEvent.getEventType() == MouseEvent.DRAG_DETECTED){
//			dragBox.setFill(currentColor);
//			dragBox.setVisible(true);
//			dragBox.setTranslateX(mouseEvent.getX());
//			dragBox.setTranslateY(mouseEvent.getY());
//		}
//
//		if(mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED && dragBox.isVisible()){
//			dragBox.setWidth(mouseEvent.getX() - dragBox.getTranslateX());
//			dragBox.setHeight(mouseEvent.getY() - dragBox.getTranslateY());
//			
//		}
//		if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
//			gc.setFill(currentColor);
//
//			gc.fillOval(dragBox.getTranslateX(),dragBox.getTranslateY()-MOUSE_CORRECTION_FACTOR,dragBox.getWidth(),dragBox.getHeight());
//			dragBox.setVisible(false);
//		}
//		
//	}
//	
//}),
//Circle(new EventHandler<MouseEvent>() {
//	@Override
//	public void handle(MouseEvent mouseEvent) {
//		if(mouseEvent.getEventType() == MouseEvent.DRAG_DETECTED){
//			dragBox.setFill(currentColor);
//			dragBox.setVisible(true);
//			dragBox.setTranslateX(mouseEvent.getX());
//			dragBox.setTranslateY(mouseEvent.getY());
//		}
//
//		if(mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED && dragBox.isVisible()){
//			dragBox.setRadius(mouseEvent.getX() - dragBox.getTranslateX());
////			dragBox.setHeight(mouseEvent.getY() - dragBox.getTranslateY());
//			
//		}
//		if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
//			gc.setFill(currentColor);
//
//			gc.fillOval(dragBox.getTranslateX()-dragBox.getRadius(),dragBox.getTranslateY()-MOUSE_CORRECTION_FACTOR-dragBox.getRadius(),dragBox.getRadius()*2,dragBox.getRadius()*2);
//			dragBox.setVisible(false);
//		}
//		
//	}});
//	
//
//
//	private EventHandler<MouseEvent> mouseEvent;
//    DrawingMouseEvents(EventHandler<MouseEvent> me) {
//        this.mouseEvent = me;
//    }
//    
//    public EventHandler<MouseEvent> getEvent(){
//    	return mouseEvent;
//    }
//}
