package eventHub.pageComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddingEvents extends AbstractComponents{
	
	public WebDriver driver;

	public AddingEvents(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="a[data-testid*='nav-events']")
	private WebElement eventNav;
	
	@FindBy(css="a[data-testid*='nav-bookings']")
	private WebElement myBooking;
	
	private By myBookingBy=By.cssSelector("a[data-testid*='nav-bookings']");
	
	private By eventBy=By.cssSelector("a[data-testid*='nav-events']");
	
	@FindBy(xpath="//article[@data-testid='event-card']")
	private List<WebElement> listOfEvents;
	
	
	private By bookNowButton=By.xpath("//a[@data-testid='book-now-btn']");
	private By listOfEventsBy=By.xpath("//article[@data-testid='event-card']");
	
	
	public WebElement eventButton() {
		waitForPresenceOfElement(eventBy);
		return eventNav;
	}
	
	public List<WebElement> eventListLoad(){
		waitForPresenceOfAllElement(listOfEventsBy);
		return listOfEvents;
	}
	
	public LastPageOfOrder addingEvent(String event) {
		eventButton();
		eventNav.click();
		eventListLoad();
		for(int i=0;i<listOfEvents.size();i++) {
			String eventExp=listOfEvents.get(i).findElement(By.xpath(".//a/h3")).getText().trim();
			if(eventExp.equalsIgnoreCase(event)) {
				
				WebElement button=waitForPresenceOfAllElements(bookNowButton).get(i);
				elementClick(button);
				break;
			}
		}
		return new LastPageOfOrder(driver);
	}
	
	public WebElement myBookingSection() {
		waitForPresenceOfElement(myBookingBy);
		return myBooking;
	}
	
	public MyBookingsPage goToBooking() {
		myBookingSection();
		myBooking.click();
		return new MyBookingsPage(driver);
	}
	

}
