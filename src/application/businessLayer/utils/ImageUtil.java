package application.businessLayer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * An util class on the image manipulation
 * @author Thomas
 *
 */
public class ImageUtil {

	/**
	 * Copy a file 
	 * @param source the image's source
	 * @param dest the image's destination
	 * @throws IOException
	 */
	public static void copyFile(File source, File dest) throws IOException {
		InputStream input = null;
		OutputStream output = null;

		try {

			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;

			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}

		} finally {

			input.close();
			output.close();
		}
	}
}
