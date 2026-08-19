package learn.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.test.resources.CartAndCheckoutPO;
import src.test.resources.DashBoardPO;
import src.test.resources.MyUtilities;

public class RemoveProductAndCheckOut extends BaseTest{
	
	DashBoardPO dashBoardPage;
	CartAndCheckoutPO cartAndCheckoutPage;
	MyUtilities myutils;
	List productsToCart;

	@BeforeClass
	public void initPageObjects() {
		dashBoardPage = new DashBoardPO(driver);
		cartAndCheckoutPage = new CartAndCheckoutPO(driver);
		myutils = new MyUtilities(driver);
	}

    @Test(priority = 3)
	public void AddProductsToCart() throws InterruptedException, IOException {
		productsToCart = myutils.readProductsdata();
		
		System.out.println("RemoveProductAndCheckOut - productsToCart: "+productsToCart);
		System.out.println("Adding Output statement");
		
		dashBoardPage.addListOfProducts(productsToCart);
		dashBoardPage.naviagateToShoppinCart();
		Thread.sleep(5000);
	}

    @Test(priority = 4)
	public void RemoveTheProduct() throws InterruptedException {
    	String productToRemove = productsToCart.get(1).toString();
    	System.out.println("productToRemove: "+productToRemove);
		cartAndCheckoutPage.removeProduct(productToRemove);
		Thread.sleep(1000);
	}

	@Test(priority = 5)
	public void productCheckout() throws InterruptedException {
		cartAndCheckoutPage.clickCheckoutBtn();
		cartAndCheckoutPage.enterFirstName("Jitesh");
		cartAndCheckoutPage.enterZipCode("577004");
		cartAndCheckoutPage.enterLastName("Sharma");
		cartAndCheckoutPage.clickContinueBtn();

		System.out.println("Item Total: "+ cartAndCheckoutPage.getItemTotal());
		System.out.println("Tax: "+ cartAndCheckoutPage.getTax());
		System.out.println("Total: "+ cartAndCheckoutPage.getTotal());

		cartAndCheckoutPage.clickFinishBtn();
		cartAndCheckoutPage.getThankYouMessge().compareToIgnoreCase("Thank you for your order!");
		cartAndCheckoutPage.clickBackHomeBtn();
		Thread.sleep(1000);
		dashBoardPage.logOut();
	}

}
