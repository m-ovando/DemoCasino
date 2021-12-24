@UserRegistration
Feature: UserRegistration
  Background:
    Given User launch Chrome browser
    And User opens URL "selenium.url"

  @HappyPath
  Scenario: Successful User registration
    When User clicks Registration button
    Then Page redirects to "selenium.url.registration"
    When User activates checkbox for terms and conditions
    And User adds valid password "selenium.password.valid"
    And User repeats password "selenium.password.valid"
    And User adds valid phone number
    And User click on the Registration button at the end of the form
    Then The user should be logged and redirected to the main page
    
  @ErrorTest
  Scenario: Unsuccessful User registration with invalid password
    When User clicks Registration button
    Then Page redirects to "selenium.url.registration"
    When User activates checkbox for terms and conditions
    And User adds invalid password "selenium.password.invalid"
    And User repeats password "selenium.password.invalid"
    And User adds valid phone number
    And User click on the Registration button at the end of the form
    Then The user should not be register and an error for invalid pass should appear

  @ErrorTest
  Scenario: Unsuccessful User registration with invalid phone
    When User clicks Registration button
    Then Page redirects to "selenium.url.registration"
    When User activates checkbox for terms and conditions
    And User adds valid password "selenium.password.valid"
    And User repeats password "selenium.password.valid"
    And User adds invalid phone number "selenium.phone.invalid"
    And User click on the Registration button at the end of the form
    Then The user should not be register and an error for invalid phone should appear

  @ErrorTest
  Scenario: Unsuccessful User registration with inactive checkbox for term and conditions
    When User clicks Registration button
    Then Page redirects to "selenium.url.registration"
    When User adds valid password "selenium.password.valid"
    And User repeats password "selenium.password.valid"
    And User adds valid phone number
    And User click on the Registration button at the end of the form
    Then The user should not be register and an error for not accepting term and conditions