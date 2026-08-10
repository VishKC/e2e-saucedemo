package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import learn.framework.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
	
	private static ExtentReports extent = ExtentManager.createInstance("./test-output/ExtentReport.html");
	public static ThreadLocal<ExtentTest> test = new ThreadLocal();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, " ~ Test Passed ~ ");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {

		test.get().log(Status.FAIL, result.getThrowable());
		System.out.println("Test Failed: " + result.getName() + " -> Taking screenshot...");

		// IMPORTANT: use the driver instance that is actually running this
		// test, not a newly launched one. BaseTest.getDriver() now returns
		// the live instance instead of opening a fresh blank browser.

		WebDriver driver = BaseTest.getDriver();
		
		System.out.println("Test Failed: " + result.getName() + " -> Taking screenshot...");
		driver = BaseTest.getDriver();
		
		if (driver != null) {
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
			if (screenshotPath != null) {
				try {
					test.get().addScreenCaptureFromPath(screenshotPath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Screenshot capture failed for: " + result.getName());
			}
		} else {
			System.out.println("Driver was null - could not capture screenshot for: " + result.getName());
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "Test Skipped");
	}
	
	// This MUST take an ITestContext to actually override ITestListener's
	// onFinish(). The previous version took an ITestResult, which meant it
	// was never invoked by TestNG - extent.flush() never ran, so the HTML
	// report file was never written out to disk.
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
	
	/* * // You can optionally override other methods like onTestStart, onTestSuccess, etc.
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
	 * */

}
