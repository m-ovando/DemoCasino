@Login
Feature: Login
  Background:
    Given User launch Chrome browser for login
    And User opens URL "selenium.url" in login

  @HappyPath
  Scenario: Successful login
    When User clicks the Sign in button
    And User chooses the Gmail option
    And User enters email as "selenium.existinguser.gmail.email" and Password as "selenium.existinguser.gmail.pass"
    Then user should appear logged
    