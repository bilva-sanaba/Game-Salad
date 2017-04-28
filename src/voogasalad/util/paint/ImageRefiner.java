package voogasalad.util.paint;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
/**
 * The first method was adapted from stack.overflow.com
 * 
 */
public class ImageRefiner implements IImageRefiner {

	public ImageRefiner(){};
	/**
	 * Change all white pixels in an image to be transparent. 
	 * @param whiteImage Image to be converted
	 * @param threshold Minimum value for cells to be considered white and be turned transparent
	 * @return
	 */
	public Image turnAllWhiteTransparent(Image whiteImage, int threshold){
		int TOLERANCE_THRESHOLD = threshold;
		int width = (int) whiteImage.getWidth();
		int height = (int) whiteImage.getHeight();
		WritableImage transparentImage = new WritableImage(width, height);
		PixelReader myPixelReader = whiteImage.getPixelReader();
		PixelWriter myPixelWriter = transparentImage.getPixelWriter();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int argb = myPixelReader.getArgb(x, y);
				if (new CheckIfWhite(TOLERANCE_THRESHOLD).checkCondition(argb)){
					argb &= 0x00FFFFFF;
				}
				myPixelWriter.setArgb(x, y, argb);
			}
		}
		return transparentImage;
	}
	public Image turnAllWhiteTransparent(Image whiteImage){
		return turnAllWhiteTransparent(whiteImage,0xFF);
	}
	/**
	 * Given a list of bounding pixels, creates a shape of the image.
	 * Useful for determining realistic collisions. This method decides what part of the image to be included in the 
	 * shape based on which shapes are transparent or not.
	 * @param inputImage
	 * @return Shape of the image without transparent cells. 
	 */
	public Shape getBoundedShape(Image inputImage){
		List<Dimension2D> coloredPixels = getBoundPixelList(inputImage, new CheckTransparency());
		Shape toReturn = Shape.union(new Rectangle(coloredPixels.get(0).getWidth(), coloredPixels.get(0).getHeight(),
													1,coloredPixels.get(1).getHeight()-coloredPixels.get(0).getHeight()), 
									new Rectangle(coloredPixels.get(2).getWidth(), coloredPixels.get(2).getHeight(),
													1,coloredPixels.get(3).getHeight()-coloredPixels.get(2).getHeight()));
		for(int ir=4;ir<coloredPixels.size();ir=ir+2){
			Rectangle curr = new Rectangle(coloredPixels.get(ir).getWidth(), coloredPixels.get(ir).getHeight(),
												1,coloredPixels.get(ir+1).getHeight()-coloredPixels.get(ir).getHeight());
			toReturn = Shape.union(toReturn, curr);
		}
		return toReturn;
	}
	/**
	 * Loops through all pixels in an image and returns a list of pixels. 
	 * Consecutive items in the list, ie 0,1; 2,3; etc. denote the bounds of a region of pixels which are
	 * meet the condition outlined in the IPixelComparison implementation
	 * @param inputImage Image to be represented by a list of bounding pixels
	 * @param ipc object which contains a method for comparing a pixel
	 * @return
	 */
	private List<Dimension2D> getBoundPixelList(Image inputImage, IPixelComparison ipc){
		List<Dimension2D> coloredPixels = new ArrayList<Dimension2D>();
		for(int i=0;i<inputImage.getWidth();i++){
			addPixelRow(i, inputImage, ipc, coloredPixels);
		}
		if(coloredPixels.size() == 0){
			return null;
		}
		return coloredPixels;
	}
	/**
	 * Given a currentRow of pixels, finds pixels which meet some criteria and adds to a list their inclusive bounding pixels
	 * @param currentRow row of pixels current being looped through
	 * @param inputImage image being analyzed
	 * @param ipc IPixelComparison which has a method for comparison a pixel
	 * @param coloredPixels List of Dimension2D representation of pixels
	 */
	private void addPixelRow(int currentRow, Image inputImage, IPixelComparison ipc, List<Dimension2D> coloredPixels){
		PixelReader myPixelReader = inputImage.getPixelReader();
		int width = (int) inputImage.getWidth();
		int height = (int) inputImage.getHeight();
		boolean firstFound =false;
		for(int j=0;j<height;j++){
			int currentPixel = myPixelReader.getArgb(currentRow, j);
			if(ipc.checkCondition(currentPixel)){
				if(!firstFound){
					firstFound=true;
					coloredPixels.add(new Dimension2D(currentRow,j));
				}
			}else{
				if(firstFound){
					coloredPixels.add(new Dimension2D(currentRow,j));
					firstFound=false;
				}
			}
		}
		if(firstFound){
			coloredPixels.add(new Dimension2D(currentRow,width));
			firstFound=false;
		}
	}
}