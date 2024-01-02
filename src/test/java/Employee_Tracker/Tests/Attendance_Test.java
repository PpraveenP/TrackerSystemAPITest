package Employee_Tracker.Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Attendance_Test {

    List<Object>  Employee_Id;
    private static final String BASE_URL_Emp = "http://localhost:8086/api/auth/Employee/"   ;
    String BASE_URL="http://localhost:8086/api/auth/Attendance/";

    @Test()
    public void getAllEmployee()
    {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL_Emp+"GetEmployee")
                .then()
                .extract().response();

        Employee_Id = response.jsonPath().getList("id");
    }
    @Test(priority = 1,dependsOnMethods = "getAllEmployee")
    public void validating_Attendance_With_Invalid_EmployeeID_Login()
    {

            Response response= RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .post(BASE_URL+"EmployeeLogin/"+Employee_Id.size()+5)
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
    @Test(priority = 2,dependsOnMethods = "getAllEmployee")
    public void validating_Attendance_With_Employee_Logout_with_invalid_Data()
    {


            Response response=RestAssured.given()
                    .contentType(ContentType.JSON)
                    .post(BASE_URL+"EmployeeLogout/"+Employee_Id.size()+123)
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
    @Test(priority = 3,dependsOnMethods = "getAllEmployee")
    public void validating_Get_Attendance_By_invalid_EMPID() {


            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .get(BASE_URL + "Attendance/"+Employee_Id.size()+123)
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 404) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }

    }

    @Test(priority = 4,dependsOnMethods ="getAllEmployee" )
    public void validating_Delete_Attendance_By_invalid_EMP_ID()
    {

            Response response=RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(BASE_URL+"DeleteAttendanceByEmployee/"+Employee_Id.size()+123)
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

