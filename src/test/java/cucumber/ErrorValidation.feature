@ErrorValidation

	Feature: Error Validation 
	
	Background: 
	Given I have landed on the Event Page
	
	
	@LogInErrorValidation
	Scenario Outline: Log In Error Validation
	Given Logged in with <email> and <password>
	Then the error message "Invalid email or password" is thrown
	
	Examples:
	|email                   |password        |
	|arijit567dwq@gmail.com	 |3253253op       |
	
	@InputFieldErrorValidation
	Scenario Outline: Error Validation for Input Field of Event 
	Given Logged in with <email> and <password>
	When I click on any <event> from event page
	And click the booking button without giving inputs 
	Then I will get error messages "<error1>" "<error2>" "<error3>"
	
	Examples:
	|event               |error1                       |error2              |error3                        |email                   |password        |
	|Dilli Diwali Mela   |Name must be at least 2 chars|Enter a valid email |Enter a valid 10-digit phone  |arijit.sinha@gmail.com	|Asdf@1234       |
