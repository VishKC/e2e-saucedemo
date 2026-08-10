package learn.framework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import src.test.resources.CartAndCheckoutPO;
import src.test.resources.DashBoardPO;
import src.test.resources.LoginPO;
import src.test.resources.MyUtilities;

public class ErrorScenarios extends BaseTest{
	
	LoginPO loginPage;
	DashBoardPO dashBoardPage;
	CartAndCheckoutPO cartAndCheckoutPage;
	MyUtilities myutils;

	@BeforeClass
	public void initPageObjects() {
		loginPage = new LoginPO(driver);
		dashBoardPage = new DashBoardPO(driver);
		cartAndCheckoutPage = new CartAndCheckoutPO(driver);
		myutils = new MyUtilities(driver);
	}

    @DataProvider(name = "loginCredentials")
    public Object[][] provideData() throws IOException {

		String username = " ";
		String password = " ";
		int rowNum = 0;
    	Object[][] logins = new Object[3][2];

		Map<String, String> loginCredentials = myutils.getUsernameAndPassword("ErrorCase");
		
		for(Map.Entry entry: loginCredentials.entrySet()) {
			System.out.println("rowNum: "+rowNum);
			username = entry.getKey().toString();
			password = entry.getValue().toString();
			System.out.println("username: "+username+" password: "+password);
			logins[rowNum][0] = username;
			logins[rowNum][1] = password;
			rowNum++;
		}    	
    	return logins;
    }

	
	@Test(priority=1, dataProvider = "loginCredentials")
	public void errorMessagesOnLoginPage(String userName, String password) throws InterruptedException {

		System.out.println("UN: "+userName+" PWD: "+password);
		
		loginPage.loginToApplication(userName, password);

		Thread.sleep(2000);
		
		if(userName == "") {
			Assert.assertEquals(loginPage.getLoginErrorMessage(), "Epic sadface: Username is required");
		} else if(password == "") {
			Assert.assertEquals(loginPage.getLoginErrorMessage(), "Epic sadface: Password is required");
		} else {
			Assert.assertEquals(loginPage.getLoginErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
		}
	}
	
	@Test(priority=2)
	public void errorMessagesOnCheckOutPage() throws InterruptedException, IOException {

		String username = null;
		String password = null;
		
		Map<String, String> loginCredentials = myutils.getUsernameAndPassword("LoginData");
		
		for(Entry e: loginCredentials.entrySet()) {
			username = e.getKey().toString();
			password = e.getValue().toString();
		}
		loginPage.loginToApplication(username, password);

		List productsToCart = myutils.readProductsdata();
		System.out.println("SelectAndCheckOut - productsToCart: "+productsToCart);
		dashBoardPage.addListOfProducts(productsToCart);

		dashBoardPage.naviagateToShoppinCart();
		cartAndCheckoutPage.clickCheckoutBtn();
		cartAndCheckoutPage.getsecHeader().equalsIgnoreCase("Checkout: Your Information");
		cartAndCheckoutPage.clickContinueBtn();
		Thread.sleep(2000);
		Assert.assertEquals(cartAndCheckoutPage.getErrorMessage(), "Error: First Name is required");
		cartAndCheckoutPage.enterFirstName("Mangesha");
		cartAndCheckoutPage.clickContinueBtn();
		Thread.sleep(2000);
		Assert.assertEquals(cartAndCheckoutPage.getErrorMessage(), "Error: Last Name is required");
		cartAndCheckoutPage.enterLastName("Kapla");
		cartAndCheckoutPage.clickContinueBtn();
		Thread.sleep(2000);
		Assert.assertEquals(cartAndCheckoutPage.getErrorMessage(), "Error: Postal Code is required");
		cartAndCheckoutPage.enterZipCode("560085");
		cartAndCheckoutPage.clickContinueBtn();
		Thread.sleep(2000);
		cartAndCheckoutPage.getsecHeader().equalsIgnoreCase("Checkout: Overview");
	}
	
}
