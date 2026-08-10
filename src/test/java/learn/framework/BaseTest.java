package learn.framework;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

	protected static WebDriver driver;

	@BeforeTest
	public void LaunchBrowser() {
		driver = createDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}

	/**
	 * Launches a brand-new browser instance. Only ever call this to CREATE a
	 * driver (e.g. from LaunchBrowser). Do NOT call this when you want the
	 * driver that's currently running a test - use getDriver() for that.
	 */
	public static WebDriver createDriver() {

//		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		// 1. Create a map for profile preferences
		Map<String, Object> prefs = new HashMap<>();

		// Disables the modern data breach/leak detection feature
		prefs.put("profile.password_manager_leak_detection", false);

		// Disables the general credential saving service
		prefs.put("credentials_enable_service", false);

		// Disables the browser's built-in password manager entirely
		prefs.put("profile.password_manager_enabled", false);

		// Attach preferences to options
		options.setExperimentalOption("prefs", prefs);

		// 2. Add extra arguments to block modern onboarding/autofill triggers
		options.addArguments("--disable-features=AutofillServerCommunication");
		options.addArguments("--disable-features=PasswordManagerOnboarding");

		return new ChromeDriver(options);
	}

	/**
	 * Returns the WebDriver instance currently in use by the running test
	 * (e.g. so a listener can take a screenshot of the actual failing page).
	 * This does NOT launch a new browser.
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	@AfterTest
	public void ShutDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}