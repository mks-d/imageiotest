package net.mks.misc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

public class App 
{
	public static void main( String[] args )
	{
		try {
			(new App()).run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void run() throws IOException {
		InputStream stream = getClass().getClassLoader().getResourceAsStream("portrait.jpg");
//		InputStream stream = getClass().getClassLoader().getResourceAsStream("landscape.jpg");
		
		// Use Thumbnails (instead of ImageIO) to reproduce the bug on the portrait image.
		BufferedImage img = Thumbnails.of(stream).scale(1).asBufferedImage();
//		BufferedImage img = ImageIO.read(stream);

		File targetDir = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath())).getParentFile();
		File outFile = new File(targetDir, "sample_out.jpg");

		ImageIO.write(img, "jpeg", outFile);
	}
}