package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail= driver.findElement(By.id("userEmail")).sendKeys("sai@gemail.com");
	@FindBy(id="userEmail")
	WebElement userEmail;
	//WebElement pswd= driver.findElement(By.id("userPassword")).sendKeys("Rgukt@123");
	@FindBy(id="userPassword")
	WebElement pswd;
	//WebElement submit= driver.findElement(By.id("login")).click();
	@FindBy(id="login")
	WebElement submit;
	///ng-tns-c4-10 toast-message ng-star-inserted
	@FindBy(css="[class*='toast-message']")
	WebElement errorMessage;

	public void goTo()
	{
		driver.get("https://www.rahulshettyacademy.com/client");
	}
	public PageCatelog loginApplication(String email, String Password)
	{
		userEmail.sendKeys(email);
		pswd.sendKeys(Password);
		submit.click();
		PageCatelog pagecatelog= new PageCatelog(driver);
		return pagecatelog;
		
	}
	public String getErrorMessage() {
		waitWebElementToBeAppear(errorMessage);
		return errorMessage.getText();
	}
	
	

}
