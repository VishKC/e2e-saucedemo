package src.test.resources;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DashBoardPO{

	private WebDriver driver;
	String prod_id;

	private By pageHeader = By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div");
	private By secHeader = By.className("header_secondary_container");
	private By ddfield = By.className("product_sort_container");
	private By leftMenu = By.id("react-burger-menu-btn");
	private By leftMenuClose = By.id("react-burger-cross-btn");
	private By logOutLink = By.id("logout_sidebar_link");

	private By shoppingCart = By.id("shopping_cart_container");

	public DashBoardPO(WebDriver driver) {
		this.driver = driver;
	}

	public String getpageHeader() {
		return driver.findElement(pageHeader).getText();
	}

	public String getsecHeader() {
		return driver.findElement(secHeader).getText();
	}

	public void naviagateToShoppinCart() {
		WebElement Cart = driver.findElement(shoppingCart);
		System.out.println("Number of Items in Cart: " +Cart.getText());
		Actions act = new Actions(driver);
		act.moveToElement(Cart);
		Cart.click();
	}
	
	public void selectDropdownValue(String value) {
		WebElement dropDown = driver.findElement(ddfield);
		Select sec = new Select(dropDown);
		sec.selectByValue(value);
	}

	public void addProduct(String Product) {
		String prod_id= getProductId(Product);
		WebElement addToCart_Btn = driver.findElement(By.id(prod_id));
		Actions act = new Actions(driver);
		act.moveToElement(addToCart_Btn);
		addToCart_Btn.click();
	}
	
	public String getProductId(String Product) {
		switch(Product) {
		case "backpack":
			prod_id = "add-to-cart-sauce-labs-backpack";
			break;
		case "bike-light":
			prod_id = "add-to-cart-sauce-labs-bike-light";
			break;
		case "bolt-t-shirt":
			prod_id = "add-to-cart-sauce-labs-bolt-t-shirt";
			break;
		case "fleece-jacket":
			prod_id = "add-to-cart-sauce-labs-fleece-jacket";
			break;
		case "onesie":
			prod_id = "add-to-cart-sauce-labs-onesie";
			break;
		default:
			prod_id = "add-to-cart-test.allthethings()-t-shirt-(red)";
			break;
		}
	return prod_id;
	}
	
	public void addListOfProducts(List Products) throws InterruptedException{
		int length = Products.size();
		for(int i = 0; i < length; i++) {
			Thread.sleep(1000);
			addProduct(Products.get(i).toString());
			
			Thread.sleep(1000);
		}
		Thread.sleep(2000);
	}

	public void logOut() throws InterruptedException {
		driver.findElement(leftMenu).click();
		Thread.sleep(3000);
		driver.findElement(logOutLink).click();

	}
}
