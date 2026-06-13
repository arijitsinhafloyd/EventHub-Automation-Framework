package cucumber;

import java.io.IOException;

import org.testng.Assert;

import eventHub.pageComponents.AddingEvents;
import eventHub.pageComponents.LastPageOfOrder;
import eventHub.pageComponents.MyBookingsPage;
import eventHub.testComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest{
	AddingEvents ad;
	LastPageOfOrder lop;
	MyBookingsPage mp;
	
	@Given("I have landed on the Event Page")
	public void I_have_landed_on_the_Event_Page() throws IOException {
		launchingApplication();
	}
	
	@Given("^Logged in with (.+) and (.+)$")
	public void Logged_in_with_email_and_password(String email, String password) {
		ad=getLoginPage().loginFlow(email,password);
	}
	
	@When("^I click on any (.+) from event page$")
	public void I_click_on_any_event_from_event_page(String event) {
		lop=ad.addingEvent(event);
	}
	
	@And("^I fill the info (.+) (.+) and (.+) and click on book$")
	public void I_fill_the_info_name_email_and_mobile_and_click_on_book(String name, String email, String mobile) {
		lop.preBookFlow(name,email,mobile);
	}

	@Then("the event should booked with status message {string}")
	public void then_the_event_should_be_booked_with_status_message (String string) {
		Assert.assertTrue(lop.statusOfOrder(string), "Event is not booked, something is wrong..");
		af();
	}
	
	@When("I go to booking section")
	public void I_go_to_booking_section() {
		mp=ad.goToBooking();
	}
	
	@Then("^the (.+) should be on the booking page$")
	public void the_event_should_be_on_the_booking_page(String event) {
		boolean status=mp.bookingVerification(event);
		Assert.assertTrue(status,"Event booking not found");
		af();
	}
	
	
	//ErrorValidation
	@Then("the error message {string} is thrown")
	public void the_error_message_is_thrown(String string) {
		String errorMessage=getLoginPage().getErrorMessage();
		Assert.assertEquals(errorMessage, string);
		af();
	}
	
	@And("click the booking button without giving inputs")
	public void click_the_booking_button_without_giving_inputs() {
		lop.bookingButton();
	}
	
	@Then("I will get error messages {string} {string} {string}")
	public void get_error_messages(String error1, String error2, String error3) {
		String[] errorMessages= {error1,error2,error3};
		String[] actualMessages=lop.getErrorMessages();
		Assert.assertEquals(errorMessages, actualMessages);
		af();
	}
}