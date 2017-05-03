package data_interfaces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

/*THIS CLASS IS UNUSED BUT COULD BE USED
 * IF ONE WANTED TO ADD MORE IN THE FUTURE
 */

public class ImageSaver {

	/**
	 * Saves an image for the user
	 * 
	 * @param fileName
	 *            name of the file
	 * @param i
	 *            the image itself
	 */
	public void saveImage(String fileName, Object i) {
		try {
			BufferedImage bi = (BufferedImage) i;
			File f = new File(fileName);
			ImageIO.write(bi, "jpg", f);
		} catch (IOException e) {
			// TODO use their error alert
		}
	}
}
