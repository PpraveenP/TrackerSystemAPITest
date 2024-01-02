package Employee_Tracker.Tests;

import Employee_Tracker.Payloads.Employee_User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Employee_Test {

    private static final String BASE_URL = "http://localhost:8086/api/auth/Employee/";
    Faker fk;
    Employee_User up;

    public Employee_User setData()
    {
        fk=new Faker();
        up=new Employee_User();
        up=new Employee_User();
        up.setName(fk.name().firstName());
//        up.setEmail(fk.internet().safeEmailAddress());
//        up.setContact(fk.number().digits(10));
        up.setCreatedby(fk.name().firstName());
        up.setCurrentaddress(fk.address().fullAddress());
        up.setPassword("praveen1997");
        up.setConfirmpassword("praveen1997");
//        up.setUsername(fk.name().firstName());
        up.setUpdatedby(fk.name().firstName());
        up.setUpdatedby(fk.name().firstName());
//        up.setYearlyleaves("90");
        return up;
    }
    @Test()
    public void validating_Employee_Registration_Without_UserName()
    {
        up=setData();
        Response responce= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"EmployeeRegister")
                .then().log().all()
                .extract().response();
        if(responce.statusCode()==400)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }

    }
    @Test()
    public void validating_Employee_Registration_Without_EmailID()
    {
        up=setData();
        Response responce= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"EmployeeRegister")
                .then().log().all()
                .extract().response();
        if(responce.statusCode()==400)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }

    }
    @Test()
    public void validating_Employee_Registration_Without_ContactNumber()
    {
        up=setData();
        Response responce= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"EmployeeRegister")
                .then().log().all()
                .extract().response();
        if(responce.statusCode()==400)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }

    }

    @Test()
    public void validating_Employee_Login_with_invalidUsername()
    {
        fk=new Faker();
        up=new Employee_User();
        up.setUsername("sdafsd");
        up.setPassword("praveen1997");

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"EmployeeLogin")
                .then().log().all()
                .extract().response();
        if(response.statusCode()==401)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }
    @Test()
    public void validating_Employee_Login_with_invalidPassword()
    {
        fk=new Faker();
        up=new Employee_User();
        up.setUsername("praveen");
        up.setPassword("sdafdg");

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"EmployeeLogin")
                .then().log().all()
                .extract().response();
        if(response.statusCode()==401)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }

    @Test()
    public void validating_Employee_Get_By_invalidID()
    {
        int EmployeeID=123;
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"Employee/"+EmployeeID)
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


    @Test
    public void validating_Employee_Delete_By_invalidID()
    {
        int EmployeeID=2;
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(BASE_URL+"Employee/"+EmployeeID)
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

    @Test()
    public void validating_Employee_Update_By_validID()
    {
        int EmployeeID=123;
        up=setData();
        Response responce= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .patch(BASE_URL+"Employee/"+EmployeeID)
                .then().log().all()
                .extract().response();
        if(responce.statusCode()==404)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }

    @Test()
    public void validating_Employee_Forget_Password_By_inValidEmailID()
    {
        String EmployeeEmailID="jon4fdhc825d5bc@gmail.com";
        Faker fk;
        fk=new Faker();
        up=new Employee_User();
        up.setEmail(EmployeeEmailID);
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"forgotPassword")
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

//    @Test()
//    public void validating_Employee_Reset_Password_By_invalid_OTP()
//    {
//        Faker fk;
//        fk=new Faker();
//        up=new Employee_User();
//        up.setNewPassword("praveen1997");
//        up.setConfirmpassword("praveen19976");
//        up.setOTP("87747");
//        Response response=RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(up)
//                .when()
//                .post(BASE_URL+"ResetPassword")
//                .then().log().all()
//                .extract().response();
//        if(response.statusCode()==400)
//        {
//            Assert.assertTrue(true);
//        }
//        else
//        {
//            Assert.assertTrue(false);
//        }
//    }

    @Test()
    public void validating_Employee_Change_Password_with_invalid_Username()
    {
        Faker fk=new Faker();
        up=new Employee_User();
        up.setPassword("praveen1997");
        up.setUsername("sgh44hfgh");
        up.setConfirmpassword("praveen1997");
        up.setCurrentpassword("praveen1997");
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"ChangePassword")
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

    @Test()
    public void validating_Employee_Change_Password_with_invalid_Password()
    {
        Faker fk=new Faker();
        up=new Employee_User();
        up.setPassword("457");
        up.setUsername("praveen");
        up.setConfirmpassword("praveen1997");
        up.setCurrentpassword("praveen1997");
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"ChangePassword")
                .then().log().all()
                .extract().response();
        if(response.statusCode()==400)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }

    }

    @Test()
    public void validating_Employee_Change_Password_with_invalid_CurrentPassword()
    {
        Faker fk=new Faker();
        up=new Employee_User();
        up.setPassword("praveen1997");
        up.setUsername("praveen");
        up.setConfirmpassword("praveen1997");
        up.setCurrentpassword("sdfsdfd");
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"ChangePassword")
                .then().log().all()
                .extract().response();
        if(response.statusCode()==400)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }

    }



}
