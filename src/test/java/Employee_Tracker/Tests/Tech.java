package Employee_Tracker.Tests;

import Employee_Tracker.Payloads.Tech_User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Tech {

    private static final String BASE_URL = "http://localhost:8086/api/auth/Tech/"   ;
    Faker fk ;
    Tech_User up;
    List<Object> Tech_ID;
    List<Object> Tech_UserName;
    List<Object> Tech_Email;
    List<Object> Tech_Password;

    public Tech_User setData()
    {
        Faker fk = new Faker();
        Tech_User up = new Tech_User();
        up.setName(fk.name().firstName());
        up.setUsername(fk.name().username());
        up.setEmail(fk.internet().safeEmailAddress());
        up.setContact(fk.number().digits(10));
        up.setCity(fk.name().lastName());
        up.setAddress(fk.address().fullAddress());
        up.setCountry(fk.name().firstName());
        up.setState(fk.name().name());
        up.setConfirmpassword("praveen1997");
        up.setPassword("praveen1997");
        up.setCreatedby(fk.name().firstName());
        up.setUpdatedby(fk.name().lastName());
        up.setCountryCode("90");
        return up;
    }

    @Test()
    public void validatin_Tech_Registration_With_valid_Data() {
       up=setData();
        Response response= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"TechRegister")
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
    @Test(priority = 3 ,dependsOnMethods = "validating_Tech_GetAllDetails")
    public void validating_Tech_Login_with_validDAta()
    {
        System.out.println(Tech_UserName);
        System.out.println(Tech_Password);
        for (int i=0;i<Tech_ID.size();i++) {
            fk = new Faker();
            up = new Tech_User();
            up.setPassword(Tech_Password.get(i).toString());
            up.setUsername(Tech_UserName.get(i).toString());
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .post(BASE_URL + "TechLogin")
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }
    @Test(priority = 4,dependsOnMethods = "validating_Tech_GetAllDetails")
    public void validating_Tech_Update_with_ValidData()
    {
        for (int i=0;i<Tech_ID.size();i++) {
            up = setData();
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .patch(BASE_URL + "Tech/" +Tech_ID.get(i))
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }

    @Test(priority = 5,dependsOnMethods = "validating_Tech_GetAllDetails")
    public void validating_Tech_Get_by_validID()
    {
       for (int i=0;i<Tech_ID.size();i++) {
           Response response = RestAssured.given()
                   .contentType(ContentType.JSON)
                   .get(BASE_URL + "Tech/"+Tech_ID.get(i))
                   .then().log().all()
                   .extract().response();
           if (response.statusCode() == 200) {
               Assert.assertTrue(true);
           } else {
               Assert.assertTrue(false);
           }
       }
    }

    @Test(priority = 2)
    public void validating_Tech_GetAllDetails()
    {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"AllTech")
                .then()
                .extract().response();
        if(response.statusCode()==200)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
        Tech_ID = response.jsonPath().getList("id");
        Tech_UserName=response.jsonPath().getList("username");
        Tech_Email=response.jsonPath().getList("email");
        Tech_Password=response.jsonPath().getList("confirmpassword");
    }

    @Test(priority = 10,dependsOnMethods = "validating_Tech_GetAllDetails")
    public void validating_Tech_Delete_By_validID()
    {
       for(int i=0; i<Tech_ID.size()-1; i++) {
           Response response = RestAssured.given()
                   .contentType(ContentType.JSON)
                   .delete(BASE_URL + "Tech/" + Tech_ID.get(i))
                   .then().log().all()
                   .extract().response();
           if (response.statusCode() == 200) {
               Assert.assertTrue(true);
           } else {
               Assert.assertTrue(false);
           }
       }
    }

    @Test(priority = 6,dependsOnMethods = "validating_Tech_GetAllDetails")
    public void validating_Tech_ChangePassword_with_validData()
    {
        for (int i=0;i<Tech_ID.size();i++) {

            fk = new Faker();
            up = new Tech_User();
            up.setUsername(Tech_UserName.get(i).toString());
            up.setCurrentpassword(Tech_Password.get(i).toString());
            up.setPassword("praveen1997");
            up.setConfirmpassword("praveen1997");
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .post(BASE_URL + "ChangePassword")
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }
    @Test(priority = 8,dependsOnMethods = "validating_Tech_GetAllDetails")
    public void validating_Tech_forgotPassword_with_validData()
    {
//        System.out.println(Tech_Email);
        for (int i=0;i<Tech_ID.size();i++) {
            fk = new Faker();
            up = new Tech_User();
            up.setEmail(Tech_Email.get(i).toString());
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .post(BASE_URL + "forgotPassword")
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
