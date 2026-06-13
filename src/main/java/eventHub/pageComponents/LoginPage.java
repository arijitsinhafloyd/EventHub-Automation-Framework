package eventHub.pageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By emailBy= By.id("email");
	
	@FindBy(id="email")
	private WebElement emailElement;
	
	@FindBy(id="password")
	private WebElement passwordElement;
	
	@FindBy(id="login-btn")
	private WebElement loginButton;
	
	@FindBy(xpath="//p[@class='text-sm font-medium flex-1 leading-snug']")
	private WebElement errorMessage;
	
	public WebElement email() {
		waitForPresenceOfElement(emailBy);
		return emailElement;
	}
	
	public void goTo(WebDriver driver) {
		driver.get("https://eventhub.rahulshettyacademy.com");
	}
	
	public AddingEvents loginFlow(String email, String password) {
		email();
		emailElement.sendKeys(email);
		passwordElement.sendKeys(password);
		elementClick(loginButton);
		return new AddingEvents(driver);
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText().trim();
	}
}
//WebElement email=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
//WebElement password=driver.findElement(By.id("password"));
//WebElement loginButton=driver.findElement(By.id("login-btn"));



