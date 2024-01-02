package Employee_Tracker.Tests;

import Employee_Tracker.Payloads.Leave;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Leave_Apply_Test {


    private static final String BASE_URL_Emp = "http://localhost:8086/api/auth/Employee/"   ;
    public String BASE_URL= "http://localhost:8086/api/auth/Leave/";
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
    @Test(priority = 1)
    public void validating_Apply_Leave_With_invalidData()
    {


        Leave up=new Leave();
        up.setName("kjgkjdl");
        up.setEmail("gjkldfjglkj@gmsil.com");
        up.setStatus("pending");
        up.setReason("fever");
        String timeStamp=new SimpleDateFormat("yyyy-mm-dd").format(new Date());
        up.setStartDate("2023-12-20");
        up.setEndDate("2023-12-25");
        up.setType("paid");

        Response response= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"SentLeave/"+Employee_Id.get(1)+123)
                .then().log().all()
                .extract().response();
        String message="false";
        if (response.jsonPath().getString("success")==message)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }
    @Test(priority = 2)
    public void validating_Get_Leave_Application_By_invalid_EMPID()
    {

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"GetByEmpId/"+Employee_Id.get(0)+123)
                .then().log().all()
                .extract().response();
        if (response.statusCode()==404)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 5)
    public  void validating_Reject_Leave_Application_By_EMP_ID()
    {

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .patch(BASE_URL+"LeaveReject/"+Employee_Id.get(0)+123)
                .then().log().all()
                .extract().response();
        if (response.statusCode()==404)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 4)
    public void validating_Accept_Leave_Application_By_invalidID()
    {

        Response response =RestAssured.given()
                .when()
                .patch(BASE_URL+""+Employee_Id.get(0)+123)
                .then().log().all()
                .extract().response();
        if(response.statusCode()==404)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }

}
