@tag
Feature: Purchase the order from Ecommerce website
I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce Page
  

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the Order
    Then I verify if "THANKYOU FOR THE ORDER." is displayed

    Examples:
      | username             | password  | productName |
      | bogdan.pop@gmail.com | Parola123 | ZARA COAT 3 |
