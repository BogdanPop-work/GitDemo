Feature: Error Validation

  @ErrorValidation
  Scenario Outline:
    Given I landed on Ecommerce Page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | username             | password   |
      | bogdan.pop@gmail.com | Parola1234 |
