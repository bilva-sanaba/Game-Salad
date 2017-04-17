package voogasalad.util.paint;

public class CheckTransparency implements IPixelComparison {

	@Override
	public boolean checkCondition(int pixel) {
		return pixel!=0;
	}

}
