package eventHub.pageComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyBookingsPage extends AbstractComponents{

	WebDriver driver;
	public MyBookingsPage(WebDriver driver) {
		// TODO Auto-generated constructor stubs
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'flex-1')]/h3")
	List<WebElement> BookedList;
	
	By BookedListBy=By.xpath("//div[contains(@class,'flex-1')]/h3");
	
	
	public List<WebElement> getBookedlList() {
		waitForPresenceOfAllElement(BookedListBy);
		return BookedList;
	}
	
	public boolean bookingVerification(String event) {
		boolean status=false;
		getBookedlList();
		for(int i=0;i<BookedList.size();i++) {
			String list=BookedList.get(i).getText().trim();
			if(list.equalsIgnoreCase(event)) {
				status=true;
				break;
			}
		}
		return status;
		
	}

}
