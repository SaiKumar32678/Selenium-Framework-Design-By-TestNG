package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends  AbstractComponent {
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement proceedButton;
    

	public boolean verifyProductDisplay(String productName ) {
		boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return match;
	}
	public PaymentPage porceedToCheckOut() {
		proceedButton.click();
		PaymentPage paymentpage= new PaymentPage(driver);
		return paymentpage;
		
	}
	
	

}
