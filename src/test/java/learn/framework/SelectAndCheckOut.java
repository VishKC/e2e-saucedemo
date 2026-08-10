package learn.framework;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.test.resources.CartAndCheckoutPO;
import src.test.resources.DashBoardPO;
import src.test.resources.MyUtilities;

public class SelectAndCheckOut extends BaseTest {

	DashBoardPO dashBoardPage;
	CartAndCheckoutPO cartAndCheckoutPage;
	MyUtilities myutils;

	@BeforeClass
	public void initPageObjects() {
		dashBoardPage = new DashBoardPO(driver);
		cartAndCheckoutPage = new CartAndCheckoutPO(driver);
		myutils = new MyUtilities(driver);
	}

    @Test(priority = 3)
	public void selectProductAddToCart() throws InterruptedException, IOException {
		List productsToCart = myutils.readProductsdata();
		System.out.println("SelectAndCheckOut - productsToCart: "+productsToCart);
		dashBoardPage.addListOfProducts(productsToCart);
		dashBoardPage.naviagateToShoppinCart();
		Thread.sleep(3000);
	}

	@Test(priority = 4)
	public void productCheckout() throws InterruptedException {
		cartAndCheckoutPage.clickCheckoutBtn();
		cartAndCheckoutPage.enterFirstName("Rohit");
		cartAndCheckoutPage.enterZipCode("577004");
		cartAndCheckoutPage.enterLastName("Sharma");
		cartAndCheckoutPage.clickContinueBtn();

		System.out.println("Item Total: "+ cartAndCheckoutPage.getItemTotal());
		System.out.println("Tax: "+ cartAndCheckoutPage.getTax());
		System.out.println("Total: "+ cartAndCheckoutPage.getTotal());

		cartAndCheckoutPage.clickFinishBtn();
		cartAndCheckoutPage.getThankYouMessge().compareToIgnoreCase("Thank you for your order!");
		dashBoardPage.logOut();
	}

}
