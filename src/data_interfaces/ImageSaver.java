package data_interfaces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

public class ImageSaver implements FileSaver{

	
	public void createFile(String fileName, Object data) {
		
		try {
			BufferedImage bi = (BufferedImage)data;
			File f = new File(fileName);
			ImageIO.write(bi, "jpg", f);
		}
		catch (IOException e) {
			//TODO use their error alert
		}
	}

	public void saveImage(String fileName, Image i) {
		createFile(fileName, i);
	}
}
