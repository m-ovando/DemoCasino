@Deposit
Feature: Deposit
  Background:
    Given User launch Chrome browser for deposit
    And User logged on url "selenium.url" with user "selenium.existinguser.gmail.email" and password "selenium.existinguser.gmail.pass"
    
    @HappyPath
    Scenario: Successful deposit
      When User opens the user profile
      And User opens the Deposit option on the profile menu
      And User clicks on the Manual Deposit button
      And User adds the amount "500" on the Amount field
      And User clicks on the Deposit button
      Then A successful message should appear indicating that the amount was correct

  @ErrorTest
  Scenario: Unsuccessful deposit
    When User opens the user profile
    And User opens the Deposit option on the profile menu
    And User clicks on the Manual Deposit button
    And User clicks on the Deposit button
    Then An error should appear indicating that the amount can't be blank