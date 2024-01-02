package Employee_Tracker.Tests;

import Employee_Tracker.Payloads.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class Super_Admin {

    private static final String BASE_URL = "http://localhost:8086/api/auth/SuperAdmin";
    Faker fk;
    User up;
    List<Object> S_A_ID;
    List<Object> S_A_Username;
    List<Object>S_A_Password;
    List<Object>S_A_Email;

    @Test
    public void validating_SuperAdminRegister_without_UserName() {
        Faker fk;
        User up;
        fk = new Faker();
        up = new User();
        up.setName(fk.name().firstName());
        up.setAddress(fk.name().lastName());
        up.setEmail(fk.internet().safeEmailAddress());
        up.setContact(fk.number().digits(10));
//        up.setUsername(fk.name().firstName());
        up.setPassword("Praveen1997");
        up.setConfirmpassword("Praveen1997");
        up.setCity("Karnataka");
        up.setCountryCode("91");
        up.setCountry("India");
        up.setCreatedby("Praveen");
        up.setUpdatedby("praveen");
        up.setState("Pune");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "/SuperAdminRegister")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 400) {
            Assert.assertTrue(true);

        } else {
            Assert.assertTrue(false);
        }


    }
    @Test(priority = 2)
    public void validating_SuperAdmin_showSuperAdmin_Get() {
        Response response = RestAssured.given()
                .get(BASE_URL + "/GetSuperAdmin")
                .then().log().all()
                .statusCode(200)
                .extract().response();

        if (response.statusCode() == 200 )
        {
            Assert.assertTrue(true);
        } else
        {
            Assert.assertTrue(false);
        }
        S_A_ID=response.jsonPath().getList("id");
        S_A_Username=response.jsonPath().getList("username");
        S_A_Password=response.jsonPath().getList("confirmpassword");
        S_A_Email=response.jsonPath().getList("email");
    }

    @Test()
    public void validating_SuperAdminRegistration_Without_Email_Id() {
        Faker fk;
        User up;
        fk = new Faker();
        up = new User();
        up.setName(fk.name().firstName());
        up.setAddress(fk.name().lastName());
//        up.setEmail(fk.internet().safeEmailAddress());
        up.setContact(fk.number().digits(10));
        up.setUsername(fk.name().firstName());
        up.setPassword("Praveen1997");
        up.setConfirmpassword("Praveen1997");
        up.setCity("Karnataka");
        up.setCountryCode("91");
        up.setCountry("India");
        up.setCreatedby("Praveen");
        up.setUpdatedby("praveen");
        up.setState("Pune");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "/SuperAdminRegister")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 400) {
            Assert.assertTrue(true);

        } else {
            Assert.assertTrue(false);
        }
    }

    @Test()
    public void validating_SuperAdminRegistration_Without_Contact_numer()
    {
        Faker fk;
        User up;
        fk = new Faker();
        up = new User();
        up.setName(fk.name().firstName());
        up.setAddress(fk.name().lastName());
        up.setEmail(fk.internet().safeEmailAddress());
//        up.setContact(fk.number().digits(10));
        up.setUsername(fk.name().firstName());
        up.setPassword("Praveen1997");
        up.setConfirmpassword("Praveen1997");
        up.setCity("Karnataka");
        up.setCountryCode("91");
        up.setCountry("India");
        up.setCreatedby("Praveen");
        up.setUpdatedby("praveen");
        up.setState("Pune");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "/SuperAdminRegister")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 400)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }

    @Test()
    public void validating_SuperAdminRegistration_Without_PasswordAndConformPassword()
    {
        Faker fk;
        User up;
        fk = new Faker();
        up = new User();
        up.setName(fk.name().firstName());
        up.setAddress(fk.name().lastName());
        up.setEmail(fk.internet().safeEmailAddress());
        up.setContact(fk.number().digits(10));
        up.setUsername(fk.name().firstName());
//        up.setPassword("Praveen1997");
//        up.setConfirmpassword("Praveen1997");
        up.setCity("Karnataka");
        up.setCountryCode("91");
        up.setCountry("India");
        up.setCreatedby("Praveen");
        up.setUpdatedby("praveen");
        up.setState("Pune");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "/SuperAdminRegister")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 400)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 2)
    public void testSuperAdminLogin_With_Invalid_USerName() {
        fk = new Faker();
        up = new User();
        up.setUsername("dasgsdfh");
        up.setPassword("Praveen1997");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "/SuperAdminLogin")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 401)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 2)
    public void testSuperAdminLogin_With_Invalid_Password() {
        fk = new Faker();
        up = new User();
        up.setUsername("praveen");
        up.setPassword("zgfdhdjhndfgj");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "/SuperAdminLogin")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 401)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 2)
    public void testSuperAdminLogin_Without_UserName() {
        fk = new Faker();
        up = new User();
//        up.setUsername("dasgsdfh");
        up.setPassword("Praveen1997");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "/SuperAdminLogin")
                .then().log().all()
                .extract().response();


        if (response.statusCode() == 400)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 2)
    public void testSuperAdminLogin_Without_Password() {
        fk = new Faker();
        up = new User();
        up.setUsername("dasgsdfh");
//        up.setPassword("Praveen1997");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "/SuperAdminLogin")
                .then().log().all()
                .statusCode(400)
                .extract().response();


        if (response.statusCode() == 400)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 4)
    public void validating_SuperAdmin_Get_By_invalid_ID() {

        int superAdminId = 200;

        Response response = RestAssured.given()
                .get(BASE_URL + "/SuperAdmin/" + superAdminId)
                .then().log().all()
                .extract().response();


        if (response.statusCode() == 404)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }
    @Test(priority = 4)
    public void validating_SuperAdmin_Get_By_Without_ID() {

        Response response = RestAssured.given()
                .get(BASE_URL + "/SuperAdmin/")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 404)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }
    @Test(priority = 6)
    public void validating_SuperAdmin_Delete_By_invalidId() {

        int superAdminIdToDelete = 200;

        Response response = RestAssured.given()
                .delete(BASE_URL + "/SuperAdmin/" + superAdminIdToDelete)
                .then().log().all()
                .extract().response();


        if (response.statusCode() == 404)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }
@Test(priority = 6)
public void validating_SuperAdmin_Delete_By_Without_ID() {

    Response response = RestAssured.given()
            .delete(BASE_URL + "/SuperAdmin/" )
            .then().log().all()
            .extract().response();

    if (response.statusCode() == 404)
    {
        Assert.assertTrue(true);

    } else
    {
        Assert.assertTrue(false);
    }
}

    @Test(priority = 7)
    public void validating_Super_Admin_Update_By_Invalid_ID(){

        int superAdminIdToUpdate = 290;
        User up;
        fk = new Faker();
        up = new User();
        up.setName(fk.name().firstName());
        up.setAddress(fk.name().lastName());
        up.setEmail(fk.internet().safeEmailAddress());
        up.setContact(fk.number().digits(10));
        up.setUsername(fk.name().firstName());
        up.setPassword("Praveen1997");
        up.setConfirmpassword("Praveen1997");
        up.setCity("Karnataka");
        up.setCountryCode("91");
        up.setCountry("India");
        up.setCreatedby("Praveen");
        up.setUpdatedby("praveen");
        up.setState("Pune");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .patch(BASE_URL + "/SuperAdmin/" + superAdminIdToUpdate)
                .then().log().all()
                .extract().response();


        if (response.statusCode() == 404)
        {
            Assert.assertTrue(true);

        } else
        {
            Assert.assertTrue(false);
        }
    }
    @Test(priority = 10,dependsOnMethods = "validating_SuperAdmin_showSuperAdmin_Get")
    public void validating_SuperAdmin_Delete_By_Id() {
        for (int i=0; i<S_A_ID.size()-1;i++) {

            Response response = RestAssured.given()
                    .delete(BASE_URL + "/SuperAdmin/" + S_A_ID.get(i))
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
