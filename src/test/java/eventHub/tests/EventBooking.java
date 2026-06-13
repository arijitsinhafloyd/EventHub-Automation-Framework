package eventHub.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import eventHub.testComponents.Retry;

import eventHub.pageComponents.AddingEvents;
import eventHub.pageComponents.LastPageOfOrder;
import eventHub.pageComponents.MyBookingsPage;
import eventHub.testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EventBooking extends BaseTest {
	
	@Test(dataProvider="getData",groups= {"positive"},retryAnalyzer=Retry.class)
	public void SubmitEvent(HashMap<String,String> hm) throws IOException {
		// TODO Auto-generated method stub
		
		//Login
		AddingEvents ad=getLoginPage().loginFlow(hm.get("email"),hm.get("password"));
		
		//Adding events
		LastPageOfOrder lop=ad.addingEvent(hm.get("event"));
		
		//Order placing and status
		lop.preBookFlow(hm.get("name"),hm.get("email"),hm.get("mobile"));
		Assert.assertTrue(lop.statusOfOrder("Booking Confirmed!"), "Event is not booked, something is wrong..");
	}
	
	@Test(dependsOnMethods="SubmitEvent", dataProvider="getData", groups= {"positive"},retryAnalyzer=Retry.class)
	public void eventConfirmation(HashMap<String,String> hm) {
		AddingEvents ad=getLoginPage().loginFlow(hm.get("email"),hm.get("password"));
		MyBookingsPage mp=ad.goToBooking();
		boolean status=mp.bookingVerification(hm.get("event"));
		Assert.assertTrue(status,"Event booking not found");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\eventHub\\data\\eventHub.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	

}
