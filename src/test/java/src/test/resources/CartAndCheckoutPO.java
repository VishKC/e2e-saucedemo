package src.test.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CartAndCheckoutPO {

	private WebDriver driver;
	String prod_id;

	private By secHeader = By.className("header_secondary_container");
	private By checkOutBtn = By.id("checkout");
	private By first_Name = By.id("first-name");
	private By last_Name = By.id("last-name");
	private By zip_Code = By.id("postal-code");
	private By errorMgs = By.className("error-message-container");
	private By continueBtn = By.id("continue");
	private By continueShoppingBtn = By.id("continue-shopping");
	private By finishBtn = By.id("finish");
	private By cancelBtn = By.id("cancel");

	private By itemTotal = By.className("summary_subtotal_label");
	private By tax = By.className("summary_tax_label");
	private By total = By.className("summary_total_label");
	private By ThankYouMessage = By.className("complete-header");
	private By backHome = By.id("back-to-products");


	public CartAndCheckoutPO(WebDriver driver) {
		this.driver = driver;
	}

	public String getsecHeader() {
		return driver.findElement(secHeader).getText();
	}
	
	public void removeProduct(String Product) {
		String prod_id= getProductId(Product);
		WebElement remove_Btn = driver.findElement(By.id(prod_id));
		Actions act = new Actions(driver);
		act.moveToElement(remove_Btn);
		remove_Btn.click();
	}
	
	public String getProductId(String Product) {
		switch(Product) {
		case "backpack":
			prod_id = "remove-sauce-labs-backpack";
			break;
		case "bike-ligh":
			prod_id = "remove-sauce-labs-bike-light";
			break;
		case "bolt-t-shirt":
			prod_id = "remove-sauce-labs-bolt-t-shirt";
			break;
		case "fleece-jacket":
			prod_id = "remove-sauce-labs-fleece-jacket";
			break;
		case "onesie":
			prod_id = "remove-sauce-labs-onesie";
			break;
		default:
			prod_id = "remove-test.allthethings()-t-shirt-(red)";
			break;
		}
	return prod_id;
	}

	public void clickCheckoutBtn() {
		driver.findElement(checkOutBtn).click();
	}

	public void enterFirstName(String firstName) {
		driver.findElement(first_Name).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		driver.findElement(last_Name).sendKeys(lastName);
	}

	public void enterZipCode(String zipCode) {
		driver.findElement(zip_Code).sendKeys(zipCode);
	}

	public void clickContinueBtn() {
		driver.findElement(continueBtn).click();
	}
	
	public String getErrorMessage() {
		return driver.findElement(errorMgs).getText();
	}

	public void clickFinishBtn() {
		driver.findElement(finishBtn).click();
	}

	public void clickCancelBtn() {
		driver.findElement(cancelBtn).click();
	}
	
	public void clickBackHomeBtn() {
		driver.findElement(backHome).click();
	}

	public String getItemTotal() {
		return driver.findElement(itemTotal).getText();
	}

	public String getTax() {
		return driver.findElement(tax).getText();
	}

	public String getTotal() {
		return driver.findElement(total).getText();
	}
	
	public String getThankYouMessge() {
		return driver.findElement(ThankYouMessage).getText();
	}
	
	public void productCheckout(){
		clickCheckoutBtn();
	}
}
