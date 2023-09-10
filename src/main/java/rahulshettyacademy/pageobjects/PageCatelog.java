package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class PageCatelog extends  AbstractComponent {
	WebDriver driver;
	
	public PageCatelog(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail= driver.findElement(By.id("userEmail")).sendKeys("sai@gemail.com");
	@FindBy(css=".card-body")
	List<WebElement> products;
    //driver.findElement(By.cssSelector(".ng-animating"))
	@FindBy(css=".ng-animating")
	WebElement spinner;

	By productsBy= By.cssSelector(".card-body");
	By item=By.cssSelector(".card-body b");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage= By.cssSelector("#toast-container");
	public List<WebElement> getProducts()
	{
		waitElementToBeAppear(productsBy); 
		return products;
	}
	public WebElement getProductsByName(String productName)
	{
		WebElement prod =  getProducts().stream().filter(product -> product.findElement(item)
				.getText().contains(productName)).findFirst().orElse(null);
		return prod;	
	}
	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement prod=getProductsByName(productName);
		prod.findElement(addToCart).click();
		waitElementToBeAppear(toastMessage);
		waitElementToBeDisappear(spinner);
	}

	
	

}
