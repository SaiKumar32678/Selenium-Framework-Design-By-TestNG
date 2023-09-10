package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.PageCatelog;
import rahulshettyacademy.pageobjects.PaymentPage;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException ,InterruptedException {
		
		landingPage.loginApplication("sai@gemail.com", "Rgukt@1123");
		String errormessage=landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", errormessage);
		//driver.close();
		
	}
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		PageCatelog pagecatelog=landingPage.loginApplication("sai@gemail.com", "Rgukt@123"); 
		List<WebElement> products=pagecatelog.getProducts();
		//driver.manage().window().fullscreen();
		pagecatelog.addProductToCart(productName); 
		CartPage cartpage=pagecatelog.goToCart();
		Boolean match=cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}

}
