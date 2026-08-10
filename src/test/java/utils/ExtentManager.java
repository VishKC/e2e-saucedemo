package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    public static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {
    	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
    	sparkReporter.config().setTheme(Theme.STANDARD);
    	sparkReporter.config().setDocumentTitle("Automation Test Report");
    	sparkReporter.config().setReportName("Vishwanath KC");

    	extent = new ExtentReports();
    	extent.attachReporter(sparkReporter);
    	extent.setSystemInfo("Operating System", System.getProperty("os.name"));
    	extent.setSystemInfo("Java Version", System.getProperty("java.version"));

    	return extent;
    }

}
