package Employee_Tracker.Tests;

import Employee_Tracker.Payloads.Tech_User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Tech_Test {
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
//        up.setUsername(fk.name().username());
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
    public void validating_Tech_Registration_Without_UserName() {
        up=setData();
        Response response= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"TechRegister")
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

    @Test()
public void validating_Tech_Registration_Without_EmailID() {
    Faker fk = new Faker();
    Tech_User up = new Tech_User();
    up.setName(fk.name().firstName());
        up.setUsername(fk.name().username());
//    up.setEmail(fk.internet().safeEmailAddress());
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

    Response response= RestAssured.given()
            .contentType(ContentType.JSON)
            .body(up)
            .when()
            .post(BASE_URL+"TechRegister")
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
    public void validating_Tech_Registration_Without_ContactNumber() {
        Faker fk = new Faker();
        Tech_User up = new Tech_User();
        up.setName(fk.name().firstName());
        up.setUsername(fk.name().username());
    up.setEmail(fk.internet().safeEmailAddress());
//        up.setContact(fk.number().digits(10));
        up.setCity(fk.name().lastName());
        up.setAddress(fk.address().fullAddress());
        up.setCountry(fk.name().firstName());
        up.setState(fk.name().name());
        up.setConfirmpassword("praveen1997");
        up.setPassword("praveen1997");
        up.setCreatedby(fk.name().firstName());
        up.setUpdatedby(fk.name().lastName());
        up.setCountryCode("90");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "TechRegister")
                .then().log().all()
                .extract().response();
        if (response.statusCode() == 400) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test()
    public void validating_Tech_Login_with_invalid_UserName()
    {
        fk=new Faker();
        up=new Tech_User();
        up.setPassword("praveen1997");
        up.setUsername("sadfsd");
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"TechLogin")
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
    public void validating_Tech_Login_with_invalid_Password()
    {
        fk=new Faker();
        up=new Tech_User();
        up.setPassword("safsdaf");
        up.setUsername("praveen");
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL+"TechLogin")
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
    public void validating_Tech_Update_with_inValidID()
    {
        int TechID=222;
        up=setData();
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .patch(BASE_URL+"Tech/"+TechID)
                .then().log().all()
                .extract().response();
        if(response.statusCode()==404

        )
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }

    @Test()
    public void validating_Tech_Get_by_invalidID()
    {
        int TechID=123;
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"Tech/"+TechID)
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
    public void validating_Tech_Delete_By_invalidID()
    {
        int TechID=123;
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .delete(BASE_URL+"Tech/"+TechID)
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
    @Test(priority = 10,dependsOnMethods = "validating_Tech_GetAllDetails")
    public void validating_Tech_Delete_By_validID()
    {
      for (int i=0; i<Tech_ID.size();i++) {
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


    @Test()
    public void validating_Tech_ChangePassword_with_invalid_UserName()
    {
        fk=new Faker();
        up=new Tech_User();
        up.setUsername("klgjsfkldg");
        up.setCurrentpassword("praveen1997");
        up.setPassword("praveen1997");
        up.setConfirmpassword("praveen1997");
        Response response= RestAssured.given()
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
    public void validating_Tech_ChangePassword_with_invalid_Password()
    {
        fk=new Faker();
        up=new Tech_User();
        up.setUsername("praveen");
        up.setCurrentpassword("praven1997");
        up.setPassword("praveen1997");
        up.setConfirmpassword("praveen1997");
        Response response= RestAssured.given()
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
    public void validating_Tech_forgotPassword_with_invalid_EmailID()
    {
        fk=new Faker();
        up=new Tech_User();
        up.setEmail("praveen97@gmail.com");
        Response response= RestAssured.given()
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

}
