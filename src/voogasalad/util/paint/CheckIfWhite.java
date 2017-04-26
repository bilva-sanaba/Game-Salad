package voogasalad.util.paint;

public class CheckIfWhite implements IPixelComparison{
	private int tolerance_threshold; 
	public CheckIfWhite(int threshold){
		tolerance_threshold = threshold;
	}
	@Override
	public boolean checkCondition(int pixel) {
		int r = (pixel >> 16) & 0xFF;
		int g = (pixel >> 8) & 0xFF;
		int b = pixel & 0xFF;

		return (r >= tolerance_threshold && g >= tolerance_threshold && b >= tolerance_threshold);
	}

}
