Feature: Put employee endpoint
  Background: Put employee endpoint should allow to update employees

    @PutWithLessFieldsRequired
    Scenario: /update should not update an employee
      Given I perform a wrong PUT to update the employee with the following data
        | 1 | TestName |
      Then I verify that the body does not have size 0
      And I verify that the message returned is different that "Successfully! Record has been updated."
      And I verify status code 400 is returned

    @PutWithCorrectValues
    Scenario: /update should update an employee
      Given I perform PUT to update the employee with the following data
        | 1 | TestName | 20 | 10000 |
      Then I verify that the body does not have size 0
      And I verify the following data in the body response
        | TestName | 20 | 10000 |
      And I verify that the message returned is "Successfully! Record has been updated."
      And I verify status code 200 is returned