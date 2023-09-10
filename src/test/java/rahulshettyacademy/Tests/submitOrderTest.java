package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.PageCatelog;
import rahulshettyacademy.pageobjects.PaymentPage;

public class submitOrderTest extends BaseTest{
	//String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitORD(HashMap<String,String> input) throws IOException ,InterruptedException {
		
		// Login
		PageCatelog pagecatelog=landingPage.loginApplication(input.get("email1"),input.get("password1")); 
		// Adding Cart
		driver.manage().window().fullscreen();
		List<WebElement> products=pagecatelog.getProducts();
		pagecatelog.addProductToCart(input.get("product")); 
		// cartPage
		CartPage cartpage=pagecatelog.goToCart();
		Boolean match=cartpage.verifyProductDisplay(input.get("product"));
		// paymentPage
		PaymentPage paymentpage=cartpage.porceedToCheckOut();
		Assert.assertTrue(match);
		paymentpage.selectCountry("india");
		// Successful order page
		ConfirmationPage confirmfPage=paymentpage.submitOrder();
		String confirmMsg= confirmfPage.getconfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitORD"},dataProvider="getData1")
	public void OrderHistoryTest(HashMap<String,String> input) {
		PageCatelog pagecatelog=landingPage.loginApplication("sai@gemail.com", "Rgukt@123"); 
		OrdersPage ordersPage=pagecatelog.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(input.get("product")));
		
	}
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String,String> map1= new HashMap<String,String> ();
//		map1.put("email1","shetty@gmail.com" );
//		map1.put("password1","Iamking@000" );
//		map1.put("product","IPHONE 13 PRO" );
//		
//		HashMap<String,String> map= new HashMap<String,String> ();
//		map.put("email1","sai@gemail.com" );
//		map.put("password1","Rgukt@123" );
//		map.put("product","ZARA COAT 3" );
		
		List<HashMap<String,String>>data=getJsonDataToMap("C:\\Users\\Admin\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	@DataProvider
	public Object[] getData1(){
		HashMap<Object,Object> map2= new HashMap<Object,Object> ();
		map2.put("product","ZARA COAT 3" );
		return new Object[] {map2};
	}
	

//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"sai@gemail.com","Rgukt@123","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","IPHONE 13 PRO"}};
//	}
//	@DataProvider
//	public Object[] getData2() {
//		return new Object[] {"ZARA COAT 3"};
//	}

}
