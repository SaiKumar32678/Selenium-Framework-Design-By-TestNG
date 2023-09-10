package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class PaymentPage extends  AbstractComponent {
	WebDriver driver;
	
	public PaymentPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder*='Select Country']")
	WebElement country;
	By countryName= By.cssSelector(".ta-results");
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectcountry;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	
	public void selectCountry(String countryname) {
		country.sendKeys(countryname);
		waitElementToBeAppear(countryName);
		selectcountry.click();
		
	}
	public ConfirmationPage submitOrder() {
		submitButton.click();
		return new ConfirmationPage(driver);
		
	}
    
	

}
