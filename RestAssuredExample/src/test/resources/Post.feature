Feature: Post employee endpoint
  Background: Post employee endpoint should allow to create employees

    @PostWithCorrectValues
    Scenario: /create should create an employee
      Given I perform a POST to create the endpoint with the following data
        | Diego | 26 | 3500 |
      Then  I verify status code 200 is returned
      And I verify that the body does not have size 0
      And I verify the following data in the body response
        | Diego | 26 | 3500 |
      And I verify that the message returned is "Successfully! Record has been added."

  @PostWithLessFieldsRequired
  Scenario: /create should not create an employee
    Given I perform a wrong POST to create an employee with the following data
      | TestName |
    Then I verify that the body does not have size 0
    And I verify that the message returned is different that "Successfully! Record has been updated."
    And I verify status code 400 is returned