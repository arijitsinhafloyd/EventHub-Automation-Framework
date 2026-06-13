package eventHub.pageComponents;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LastPageOfOrder extends AbstractComponents{

	public WebDriver driver;
	
	public LastPageOfOrder(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="customerName")
	private WebElement customerName;
	
	@FindBy(id="customer-email")
	private WebElement customerEmail;
	
	@FindBy(id="phone")
	private WebElement customerPhone;
	
	@FindBy(id="confirm-booking")
	private WebElement confirmButton ;
	
	@FindBy(xpath="//div[contains(@class,'py-6')]/h3")
	private WebElement statusMessage;
	
	@FindBy(css="p[class*='text-red-600']")
	private List<WebElement> errorMessages;
	
	private By ListOfErrors=By.cssSelector("p[class*='text-red-600']");
	
	
	public void preBookFlow(String name, String email, String phone) {
		customerName.sendKeys(name);
		customerEmail.sendKeys(email);
		customerPhone.sendKeys(phone);
		elementClick(confirmButton);
	}
	
	public boolean statusOfOrder(String expectedMessage) {
		boolean status=false;
		waitForElementToAppear(statusMessage);
		String message=statusMessage.getText().trim();
		if(message.contains(expectedMessage))
			status=true;
		return status;
	}
	
	public void bookingButton() {
		elementClick(confirmButton);
	}
	
	public List<WebElement> listOfErrorsPresence() {
		waitForPresenceOfAllElement(ListOfErrors);
		return errorMessages;
	}
	
	public String[] getErrorMessages() {
		listOfErrorsPresence();
		String[] actual=new String[errorMessages.size()];
		for(int i=0;i<errorMessages.size();i++) {
			actual[i]=errorMessages.get(i).getText().trim();
		}
		return actual;
	}
}
