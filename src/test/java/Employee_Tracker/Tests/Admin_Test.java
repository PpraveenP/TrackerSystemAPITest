package Employee_Tracker.Tests;


import Employee_Tracker.Payloads.Admin_User;
import Employee_Tracker.Payloads.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class Admin_Test  {
    Faker fk;
    Admin_User up;
    List<Object> Admin_id;
    List<String > Admin_username;
    List<String> Admin_Password;
    private static final String BASE_URL = "http://localhost:8086/api/auth/Admin/";
    @Test(priority = 2)
    public void validating_Admin_showAllAdmin_Get() {
        Response response = RestAssured.given()
                .get(BASE_URL + "GetAllAdmin")
                .then()
                .extract().response();

        if (response.statusCode() == 200)
        {
            Assert.assertTrue(true);
        } else
        {
            Assert.assertTrue(false);
        }
        JsonPath js=response.jsonPath();
        Admin_id = js.getList("id");
        Admin_username = js.getList("username");
        Admin_Password=js.getList("confirmpassword");

    }
    @Test
    public void validating_SuperAdminRegister_without_UserName() {
        Faker fk;
        Admin_User up;
        fk = new Faker();
        up = new Admin_User();
        up.setName(fk.name().firstName());
        up.setAddress(fk.name().lastName());
        up.setEmail(fk.internet().safeEmailAddress());
        up.setContact(fk.number().digits(10));
//        up.setUsername(null);
        up.setPassword("Praveen1997");
        up.setConfirmpassword("Praveen1997");
        up.setCity("Karnataka");
        up.setCountryCode("91");
        up.setCountry("India");
        up.setCreatedby("Praveen");
        up.setUpdatedby("praveen");
        up.setState("Pune");
        up.setOrganizationName("Syntiaro");
        up.setOrganizationType("IT");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "AdminRegister")
                .then().log().all()
                .extract().response();

        // Extract the message from the response
        String actualMessage = response.jsonPath().getString("message");

        // Check if the registration was successful
        if (response.statusCode() == 400) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        // You can add more test methods for other API endpoints as needed

    }
    @Test
    public void validating_SuperAdminRegister_without_EmailID() {
        Faker fk;
        Admin_User up;
        fk = new Faker();
        up = new Admin_User();
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
        up.setOrganizationName("Syntiaro");
        up.setOrganizationType("IT");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "AdminRegister")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 400) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }
@Test
public void validating_SuperAdminRegister_without_contactNumber() {
    Faker fk;
    Admin_User up;
    fk = new Faker();
    up = new Admin_User();
    up.setName(fk.name().firstName());
    up.setAddress(fk.name().lastName());
        up.setEmail(fk.internet().safeEmailAddress());
//    up.setContact(fk.number().digits(10));
    up.setUsername(fk.name().firstName());
    up.setPassword("Praveen1997");
    up.setConfirmpassword("Praveen1997");
    up.setCity("Karnataka");
    up.setCountryCode("91");
    up.setCountry("India");
    up.setCreatedby("Praveen");
    up.setUpdatedby("praveen");
    up.setState("Pune");
    up.setOrganizationName("Syntiaro");
    up.setOrganizationType("IT");

    Response response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(up)
            .when()
            .post(BASE_URL + "AdminRegister")
            .then().log().all()
            .extract().response();

    if (response.statusCode() == 400) {
        Assert.assertTrue(true);
    } else {
        Assert.assertTrue(false);
    }
}

    @Test(priority = 2)
    public void validating_Admin_Login_with_invalid_Username() {
        Faker fk;
        Admin_User up;
        fk = new Faker();
        up = new Admin_User();
        up.setUsername("fgsfg");
        up.setPassword("Praveen1997");


        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "AdminLogin")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 400) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 2)
    public void validating_Admin_Login_with_invalid_Password() {
        Faker fk;
        Admin_User up;
        fk = new Faker();
        up = new Admin_User();
        up.setUsername("Antionette");
        up.setPassword("Praveen997");


        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "AdminLogin")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 400) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }
    @Test(priority = 4)
public void validating_Admin_Get_By_invalid_ID() {
    int AdminId=123;
    Response response = RestAssured.given()
            .get(BASE_URL +"Admin/"+AdminId)
            .then().log().all()
            .extract().response();

        if (response.statusCode() == 404) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
}
    @Test(priority = 4)
    public void validating_Admin_Delete_By_invalidID() {
        int AdminId=123;
        Response response = RestAssured.given()
                .delete(BASE_URL +"Admin/"+AdminId)
                .then().log().all()
//                .statusCode(200)
                .extract().response();

        if (response.statusCode() == 404) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }
//    @Test(priority = 10,dependsOnMethods = "validating_Admin_showAllAdmin_Get")
//    public void validating_Admin_Delete_By_ID() {
//        for (int i = 0; i < Admin_id.size()-1; i++) {
//
//            Response response = RestAssured.given()
//                    .delete(BASE_URL + "Admin/" + Admin_id.get(i))
//                    .then().log().all()
////                .statusCode(200)
//                    .extract().response();
//
//            if (response.statusCode() == 404) {
//                Assert.assertTrue(true);
//            } else {
//                Assert.assertTrue(false);
//            }
//        }
//    }
//

    @Test(priority = 7)
    public void validating_Super_Admin_Update_By_invalidId() {
        // Define the SuperAdmin ID you want to update
        int AdminIdToUpdate = 123; // Change this ID based on your scenario

        // Assuming you have an updatedUser object with the new data
        Admin_User up;
        fk = new Faker();
        up = new Admin_User();
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
                .patch(BASE_URL + "Admin/" + AdminIdToUpdate)
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 404) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 8)
    public void validating_Admin_Change_Password_without_Username(){
        Admin_User up;
        fk = new Faker();
        up = new Admin_User();

//        up.setUsername("praveen");
        up.setCurrentpassword("Praveen1997");
        up.setPassword("Praveen1997");
        up.setConfirmpassword("Praveen1997");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "ChangePassword")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 400) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 9)
    public void validating_Admin_ForgotPassword_with_Invalid_EmailID() {
        Admin_User up;
        fk = new Faker();
        up = new Admin_User();

        up.setEmail("dorrilanda@example.com");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "forgotPassword")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 404) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }
    @Test(priority = 10)
    public void validating_Admin_Delete_By_validID() {

        for (int i=0;i<Admin_id.size()-1;i++)
        {
            Response response = RestAssured.given()
                    .delete(BASE_URL + "Admin/" + Admin_id.get(i))
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
