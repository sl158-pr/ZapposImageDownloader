package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Class Name: ImageHelperClass.java
 * 
 * Helper class to create the folder "image" in the current directory if doesn't
 * exist download the impages into that folder
 * 
 * @author Sumanth Lakshminarayana, UTA ID: 1000830230
 */

public class ImageHelperClass {

	private static final String IMAGE_FOLDER_NAME = "images";
	private static final String PATH_DELIMITER = "\\";
	private static final String IMAGE_FILE_TYPE = ".jpg";

	public static void storeImageLocally(String imageURL, String productId)
			throws IOException {

		URL url = new URL(imageURL);
		InputStream imageIStream = url.openStream();
		// Saving the images with SKUS as file name
		OutputStream imageOStream = new FileOutputStream(
				getImageCommpletePath(productId));

		byte[] buffer = new byte[2048];
		int readLength;

		while ((readLength = imageIStream.read(buffer)) != -1) {
			imageOStream.write(buffer, 0, readLength);
		}

		imageIStream.close();
		imageOStream.close();

	}

	/*
	 * Create the folder "image" in the current directory if doesn't exist
	 */
	private static String getImageFolderPath() {
		String workingDir = System.getProperty("user.dir");

		String imageFolderPath = workingDir + PATH_DELIMITER
				+ IMAGE_FOLDER_NAME;

		File imageFolder = new File(imageFolderPath);

		if (!imageFolder.exists()) {
			imageFolder.mkdir();
		}

		return imageFolderPath;
	}

	/*
	 * Returns complete image path including image file name
	 */
	private static String getImageCommpletePath(String productName) {
		// System.out.println(getImageFolderPath() + PATH_DELIMITER +
		// productName+ IMAGE_FILE_TYPE);
		return getImageFolderPath() + PATH_DELIMITER + productName
				+ IMAGE_FILE_TYPE;
	}
}
