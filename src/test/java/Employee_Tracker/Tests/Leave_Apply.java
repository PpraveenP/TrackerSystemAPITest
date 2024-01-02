package Employee_Tracker.Tests;

import Employee_Tracker.Payloads.Leave;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Leave_Apply {


    private static final String BASE_URL_Emp = "http://localhost:8086/api/auth/Employee/"   ;
    public String BASE_URL= "http://localhost:8086/api/auth/Leave/";
     public int empID;
    List<Object> Employee_Id;
    List<Object>  Employee_Email;
    List<Object>  Employee_Username;
    List<Object>  Employee_Password;
    @Test()
    public void getAllEmployee()
    {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL_Emp+"GetEmployee")
                .then()
                .extract().response();

        Employee_Id = response.jsonPath().getList("id");
        Employee_Email=response.jsonPath().getList("email");
        Employee_Username=response.jsonPath().getList("name");
        Employee_Password=response.jsonPath().getList("confirmpassword");
        System.out.println(Employee_Id);
    }
    @Test(priority = 1,dependsOnMethods = "getAllEmployee")
    public void validating_Apply_Leave_With_validData()
    {
        for (int i=0;i<Employee_Id.size();i++) {
            Leave up = new Leave();
            up.setName(Employee_Username.get(i).toString());
            up.setEmail(Employee_Email.get(i).toString());
            up.setStatus("pending");
            up.setReason("fever");

            LocalDate currentDate = LocalDate.now();
            // Add a specific number of months to the current date (e.g., 1 month in this case)
            LocalDate futureDate = currentDate.plusMonths(1);
            // Format the date using the desired pattern
            String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            up.setStartDate(formattedDate);
            up.setEndDate(formattedDate);
            up.setType("totalsickleave");

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .post(BASE_URL + "SentLeave/" +Employee_Id.get(i))
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }

    }
    @Test(priority = 2,dependsOnMethods = "getAllEmployee")
    public void validating_Get_Leave_Application_By_valid_EMPID()
    {
        for (int i=0;i<Employee_Id.size();i++) {
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .get(BASE_URL + "GetByEmpId/" + Employee_Id.get(i))
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }



    @Test(priority = 3)
    public void validating_Get_All_Leave_Applications()
    {

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"GetAllLeave")
                .then().log().all()
                .extract().response();
        if (response.statusCode()==200)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }


    @Test(priority = 5,dependsOnMethods = "getAllEmployee")
    public  void validating_Reject_Leave_Application_By_EMP_ID()
    {
        for (int i=0;i<Employee_Id.size();i++) {
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .patch(BASE_URL + "LeaveReject/" + Employee_Id.get(i))
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }

    @Test(priority = 4,dependsOnMethods = "getAllEmployee")
    public void validating_Accept_Leave_Application_By_validID()
    {
        for (int i=0;i<Employee_Id.size();i++) {
            Response response = RestAssured.given()
                    .when()
                    .patch(BASE_URL+"LeaveAccept/"+Employee_Id.get(i))
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }

}
