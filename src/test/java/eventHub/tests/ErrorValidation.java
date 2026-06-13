package eventHub.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eventHub.pageComponents.AddingEvents;
import eventHub.pageComponents.LastPageOfOrder;
import eventHub.testComponents.BaseTest;
import eventHub.testComponents.Retry;

public class ErrorValidation extends BaseTest{
	
	@Test(dataProvider="getData", groups= {"Errors"})
	public void loginError(HashMap<String,String> hm) throws IOException {
		getLoginPage().loginFlow(hm.get("wrong_email"),hm.get("wrong_password"));
		String errorMessage=getLoginPage().getErrorMessage();
		Assert.assertEquals(errorMessage, "Invalid email or password");
	}
	
	@Test(dataProvider="getData1", groups= {"Errors"},retryAnalyzer=Retry.class)
	public void errorMessages(HashMap<String,String> hm) {
		String[] errorMessages= {hm.get("errors1"),hm.get("errors2"),hm.get("errors3")};
		AddingEvents ad=getLoginPage().loginFlow(hm.get("email"),hm.get("password"));
		LastPageOfOrder lop=ad.addingEvent(hm.get("event"));
		lop.bookingButton();
		String[] actualMessages=lop.getErrorMessages();
		Assert.assertEquals(errorMessages, actualMessages);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\eventHub\\data\\eventHub.json");
		return new Object[][] {{data.get(2)}};
	}
	
	@DataProvider
	public Object[][] getData1() throws IOException {
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\eventHub\\data\\eventHub.json");
		return new Object[][] {{data.get(3)}};
	}
	
	
}
