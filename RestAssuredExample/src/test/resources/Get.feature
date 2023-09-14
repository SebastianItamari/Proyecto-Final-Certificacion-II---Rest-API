Feature: Get employee endpoints
  Background: Get employees endpoints should allow to get one or all employees

    @GetAll
    Scenario: /employees should return all the employees
      Given I perform a GET the employees endpoint
      Then I verify status code 200 is returned
      And I verify that the body does not have size 0
      And I verify that the message returned is "Successfully! All records has been fetched."

    @GetByIdWithNonNumericID
    Scenario: /employees/{id} should not return an employee according the id
      Given I perform a GET with id "sebas" to obtain an employee
      Then I verify that the body does not have size 0
      And I verify that the value of data is null
      And I verify status code 404 is returned
      And I verify that the message returned is different that "Successfully! Record has been fetched."

  @GetByIdWithCorrectID
  Scenario: /employees/{id} should return an employee according the id
    Given I perform a GET with id "1" to obtain an employee
    Then I verify that the body does not have size 0
    And I verify status code 200 is returned
    And I verify that the message returned is "Successfully! Record has been fetched."
