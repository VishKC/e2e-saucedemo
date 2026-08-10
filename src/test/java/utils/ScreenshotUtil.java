package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	public static String captureScreenshot(WebDriver driver, String screenshotName) {

		String dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// Keep screenshots alongside the report, under test-output/Images
		String destinationDir = System.getProperty("user.dir")
				+ File.separator + "test-output"
				+ File.separator + "Images";

		File destDirFile = new File(destinationDir);
		if (!destDirFile.exists()) {
			destDirFile.mkdirs();
		}
		System.out.println("destinationDir: "+destinationDir);
		// Build a unique, descriptive file name - this was missing before,
		// which is why nothing usable was ever produced
		String fileName = screenshotName + "_" + dateTime + ".png";
		File destinationFile = new File(destDirFile, fileName);

		try {
			// Destination must be a FILE, not a directory - copying to a
			// directory here was the reason screenshots never got created
			FileUtils.copyFile(source, destinationFile);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return destinationFile.getAbsolutePath();
	}

}
