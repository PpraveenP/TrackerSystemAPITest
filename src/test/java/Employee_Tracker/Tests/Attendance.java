package Employee_Tracker.Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Attendance {



    List<Object>  Employee_Id;
    String BASE_URL="http://localhost:8086/api/auth/Attendance/";
    private static final String BASE_URL_Emp = "http://localhost:8086/api/auth/Employee/"   ;

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
//    @Test(priority = 1)
//    public void validating_Attendance_With_Employee_Login()
//    {
//
//        for (Object employeeId : Employee_Id) {
//
//            Response response= RestAssured.given()
//                    .contentType(ContentType.JSON)
//                    .when()
//                    .post(BASE_URL+"EmployeeLogin/"+employeeId)
//                    .then().log().all()
//                    .extract().response();
//            if (response.statusCode()==200)
//            {
//                Assert.assertTrue(true);
//            }
//            else if (response.statusCode()==400)
//            {
//                Assert.assertTrue(true);
//            }
//        }
//    }
//    @Test(priority = 2)
//    public void validating_Attendance_With_Employee_Logout()
//    {
//
//        for (Object id:Employee_Id)
//        {
//            Response response=RestAssured.given()
//                    .contentType(ContentType.JSON)
//                    .post(BASE_URL+"EmployeeLogout/"+id)
//                    .then().log().all()
//                    .extract().response();
//            if (response.statusCode()==200)
//            {
//                Assert.assertTrue(true);
//            }
//            else if (response.statusCode()==400)
//            {
//                Assert.assertTrue(true);
//            }
//        }
//    }
//    @Test(priority = 3)
//    public void validating_Get_Attendance_By_valid_EMPID() {
//
//        for (Object id : Employee_Id) {
//            Response response = RestAssured.given()
//                    .contentType(ContentType.JSON)
//                    .get(BASE_URL + "Attendance/"+id)
//                    .then().log().all()
//                    .extract().response();
//            if (response.statusCode() == 200) {
//                Assert.assertTrue(true);
//            } else {
//                Assert.assertTrue(false);
//            }
//        }
//    }
    @Test(priority = 4)
    public void validating_Get_Attendance_Show_List() {

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .get(BASE_URL + "GetAllAttendace")
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 200)
            {
                Assert.assertTrue(true);
            } else
            {
                Assert.assertTrue(false);
            }
        }

        @Test(priority = 5 )
    public void validating_Delete_Attendance_By_valid_EMP_ID()
        {
            for (int i=0;i<Employee_Id.size()-1;i++)
            {
                Response response=RestAssured.given()
                        .contentType(ContentType.JSON)
                        .when()
                        .delete(BASE_URL+"DeleteAttendanceByEmployee/"+Employee_Id.get(i))
                        .then().log().all()
                        .extract().response();
                if(response.statusCode()==200)
                {
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }
        }
}

