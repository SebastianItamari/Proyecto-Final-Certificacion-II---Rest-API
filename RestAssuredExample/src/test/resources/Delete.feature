Feature: Delete employee endpoint
  Background: Delete employee endpoint should allow to delete employees

    @DeleteEmployeeWithIncorrectNumericID
    Scenario: /delete should not delete an employee
      Given I perform a DELETE to delete the employee with id "1000000000000"
      Then I verify that the body does not have size 0
      And I verify that the value of data is null
      And  I verify status code 404 is returned
      And I verify that the message returned is different that "Successfully! Record has been deleted"

  @DeleteEmployeeWithCorrectID
  Scenario: /delete should delete an employee
    Given I perform a DELETE to delete the employee with id "1"
    Then I verify that the body does not have size 0
    And  I verify status code 200 is returned
    And I verify that the value of data is "1"
    And I verify that the message returned is "Successfully! Record has been deleted"