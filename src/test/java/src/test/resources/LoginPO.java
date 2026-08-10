package src.test.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPO {

	private WebDriver driver;

	private By username = By.id("user-name");
	private By password = By.id("password");
	private By loginBtn = By.id("login-button");
	
	private By errorMgs = By.className("error-message-container");

	String URL = "https://www.saucedemo.com/";

	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String Username) {
		WebElement fieldUsername = driver.findElement(username);
		String fieldValue = fieldUsername.getAttribute("value");
		if(fieldValue == null || fieldValue.trim().isEmpty()) {
			fieldUsername.sendKeys(Username);
		} else {
			fieldUsername.clear();
			fieldUsername.sendKeys(Username);
		}
	}

	public void enterPassword(String Password) {
		WebElement fieldPassword = driver.findElement(password);
		String fieldValue = fieldPassword.getAttribute("value");
		if(fieldValue == null || fieldValue.trim().isEmpty()) {
			fieldPassword.sendKeys(Password);
		} else {
			fieldPassword.clear();
			fieldPassword.sendKeys(Password);
		}
	}

	public void clickLoginBtn() {
		driver.findElement(loginBtn).click();
	}
	
	public void loginToApplication(String username, String password) {
		System.out.println("username: "+username+" Password: "+password);
		enterUsername(username);
		enterPassword(password);
		clickLoginBtn();
	}
	
	public String getLoginErrorMessage() {
		return driver.findElement(errorMgs).getText();
	}

}
