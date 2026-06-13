@Regression

	Feature: Booking an event 
	
	Background: 
	Given I have landed on the Event Page
	
	@EventBooking 
	Scenario Outline: Positive test of event booking
	Given Logged in with <email> and <password>
	When I click on any <event> from event page
	And I fill the info <name> <email> and <mobile> and click on book
	Then the event should booked with status message "Booking Confirmed!"  
	
	
	Examples:
	|email                  |password    |event                                |name          |mobile      |
	|arijit.sinha@gmail.com |Asdf@1234   |Dilli Diwali Mela                    |Arijit Sinha  |9456456456  |    
	|kriti.sanon@gmail.com  |Asdf@1234   |Hollywood Monsoon Night — Los Angeles|Kriti Sanon   |8765787567  |
	
	@VerifyingEvents
	Scenario Outline:Verification of eventss
	Given Logged in with <email> and <password>
	When I go to booking section
	Then the <event> should be on the booking page
	
	Examples:
	|email                  |password    |event                                |
	|arijit.sinha@gmail.com |Asdf@1234   |Dilli Diwali Mela                    |   
	|kriti.sanon@gmail.com  |Asdf@1234   |Hollywood Monsoon Night — Los Angeles|