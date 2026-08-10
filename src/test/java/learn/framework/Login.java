package learn.framework;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import src.test.resources.CartAndCheckoutPO;
import src.test.resources.DashBoardPO;
import src.test.resources.LoginPO;
import src.test.resources.MyUtilities;

public class Login extends BaseTest {

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

	@Test(priority = 1)
	public void verifyLogin() throws IOException {

		String username = null;
		String password = null;
		
		Map<String, String> loginCredentials = myutils.getUsernameAndPassword("LoginData");
		
		for(Entry e: loginCredentials.entrySet()) {
			username = e.getKey().toString();
			password = e.getValue().toString();
		}
		loginPage.loginToApplication(username, password);
	}

    @Test(priority = 2)
	public void verifyDashBoardPage() throws InterruptedException {
    	dashBoardPage.getpageHeader().equalsIgnoreCase("Swag Labs");
		dashBoardPage.getsecHeader().equalsIgnoreCase("Products");
		dashBoardPage.selectDropdownValue("lohi");
		//Thread.sleep(5000);
//		dashBoardPage.logOut();
	}

}
