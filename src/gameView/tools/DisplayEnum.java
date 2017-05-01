package gameView.tools;


public enum DisplayEnum {
	TOP_LEFT	(0, 0),				
	TOP_CENTER	(50, 0),			
	TOP_RIGHT	(100, 0),				
	CENTER_RIGHT	(100, 50),			
	BOTTOM_RIGHT	(100, 100),				
	BOTTOM_CENTER	(50, 100),				
	BOTTOM_LEFT		(0, 100),				
	CENTER_LEFT 	(0, 50);				
	
	
	
	private final double myX;
	private final double myY;

	DisplayEnum(double x, double y) {
		this.myX = x;
		this.myY = y;
	}
	
	/**
	 * Returns the percentage of the X that the object should be located at. I.E. TOP_CENTER returns 50 because an item at the
	 * top in the center should be 50% of the width of the screen
	 * @return
	 */
	public double x() {
		return myX;
	}
	/**
	 * Returns the percentage of the Y that the object should be located at. I.E. CENTER_LEFT returns 50 because an item at the
	 * center on the left should be 50% of the height of the screen
	 */
	
	public double y() {
		return myY;
	}
}
