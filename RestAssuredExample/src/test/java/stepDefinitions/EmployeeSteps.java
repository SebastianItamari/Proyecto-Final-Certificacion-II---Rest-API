package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.EmployeeEndpoints;
import entities.Employee;
import entities.WrongEmployee;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import utils.Request;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;

public class EmployeeSteps {
    Response response;
    @Given("I perform a GET the employees endpoint")
    public void getEmployees(){
        response = Request.get(EmployeeEndpoints.GET_EMPLOYEES);
    }

    @And("I perform a GET with id {string} to obtain an employee")
    public void getEmployeeByID(String id){
        response = Request.getWithId(EmployeeEndpoints.GET_EMPLOYEE, id);
    }

    @And("I perform a DELETE to delete the employee with id {string}")
    public void deleteEmployee(String id){
        response = Request.delete(EmployeeEndpoints.DELETE_EMPLOYEE, id);
    }

    @And("I verify status code {int} is returned")
    public void verifyStatusCode(int statusCode){
        response.then().assertThat().statusCode(statusCode);
    }

    @And("I verify that the body does not have size {int}")
    public void verifyResponseSize(int size){
        response.then().assertThat().body("size()", not(0));
    }

    @And("I perform a POST to create the endpoint with the following data")
    public void postEmployee(DataTable employeeInfo) throws JsonProcessingException {
        List<String> data = employeeInfo.transpose().asList(String.class);

        Employee employee = new Employee();
        employee.setName(data.get(0));
        employee.setAge(data.get(1));
        employee.setSalary(data.get(2));

        ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(employee);

        response = Request.post(EmployeeEndpoints.POST_EMPLOYEE, payload);
    }

    @And("I perform a wrong PUT to update the employee with the following data")
    public void wrongPutEmployee(DataTable employeeInfo) throws JsonProcessingException {
        List<String> data = employeeInfo.transpose().asList(String.class);

        WrongEmployee wrongEmployee = new WrongEmployee();
        wrongEmployee.setName(data.get(1));

        ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(wrongEmployee);

        response = Request.put(EmployeeEndpoints.PUT_EMPLOYEE,data.get(0),payload);
    }

    @And("I perform PUT to update the employee with the following data")
    public void putEmployee(DataTable employeeInfo) throws JsonProcessingException {
        List<String> data = employeeInfo.transpose().asList(String.class);

        Employee employee = new Employee();
        employee.setName(data.get(1));
        employee.setSalary(data.get(3));
        employee.setAge(data.get(2));

        ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(employee);

        response = Request.put(EmployeeEndpoints.PUT_EMPLOYEE,data.get(0),payload);
    }

    @And("I verify the following data in the body response")
    public void verifyEmployeeResponseData(DataTable employeeInfo){
        List<String> data = employeeInfo.transpose().asList(String.class);

        response.then().assertThat().body("data.name", Matchers.equalTo(data.get(0)));
        response.then().assertThat().body("data.age",Matchers.equalTo(data.get(1)));
        response.then().assertThat().body("data.salary",Matchers.equalTo(data.get(2)));
    }

    @And("I verify that the value of data is null")
    public void verifyEmployeeResponseDataIsNull(){
        response.then().assertThat().body("data", Matchers.equalTo(null));
    }

    @And("I verify that the message returned is different that {string}")
    public void verifyResponseMessageIsDifferent(String message){
        response.then().assertThat().body("message", Matchers.equalTo(not(message)));
    }

    @And("I verify that the message returned is {string}")
    public void verifyResponseMessage(String message){
        response.then().assertThat().body("message", Matchers.equalTo(message));
    }

    @And("I verify that the value of data is {string}")
    public void verifyEmployeeResponseDataID(String id){
        response.then().assertThat().body("data", Matchers.equalTo(id));
    }

    @And("I perform a wrong POST to create an employee with the following data")
    public void wrongPostEmployee(DataTable employeeInfo) throws JsonProcessingException {
        List<String> data = employeeInfo.transpose().asList(String.class);

        WrongEmployee wrongEmployee = new WrongEmployee();
        wrongEmployee.setName(data.get(0));

        ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(wrongEmployee);

        response = Request.post(EmployeeEndpoints.POST_EMPLOYEE,payload);
    }
}
