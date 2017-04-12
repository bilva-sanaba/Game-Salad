package gameView.tools;

import java.util.ArrayList;
import java.util.Collection;

public enum DisplayEnum {
	TOP_LEFT	(0, 0),				//(0, 100, 100, 0),
	TOP_CENTER	(50, 0),					//(0, 50, 100, 50),
	TOP_RIGHT	(100, 0),					//(0, 0, 100, 100),
	CENTER_RIGHT	(100, 50),				//(50, 0, 50, 100),
	BOTTOM_RIGHT	(100, 100),				//(100, 0, 0, 100),
	BOTTOM_CENTER	(50, 100),				//(100, 50, 0, 50),
	BOTTOM_LEFT		(0, 100),				//(100, 0, 0, 100),
	CENTER_LEFT 	(0, 50);				//(50, 100, 50, 0);
	
//	private final double myTop;
//	private final double myRight;
//	private final double myBottom;
//	private final double myLeft;
	private final double myX;
	private final double myY;

	DisplayEnum(double x, double y) {
//		this.myTop = top;
//		this.myRight = right;
//		this.myBottom = bottom;
//		this.myLeft = left;
		this.myX = x;
		this.myY = y;
	}
	
//	public double top() {
//		return myTop;
//	}
//	
//	public double right() {
//		return myRight;
//	}
//	public double bottom() {
//		return myBottom;
//	}
//	
//	public double left() {
//		return myLeft;
//	}
	public double x() {
		return myX;
	}
	
	public double y() {
		return myY;
	}
}
