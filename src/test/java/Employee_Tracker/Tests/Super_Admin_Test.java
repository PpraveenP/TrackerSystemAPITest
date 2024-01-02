package Employee_Tracker.Tests;


import Employee_Tracker.Payloads.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

import java.util.List;

public class Super_Admin_Test{
    private static final String BASE_URL = "http://localhost:8086/api/auth/SuperAdmin";

    Faker fk;
    User up;
    List<Object> S_A_ID;
    List<Object> S_A_Username;
    List<Object>S_A_Password;
    List<Object>S_A_Email;

    public User setData()
    {
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
        return up;
    }

//    @Test(priority = 1)
//    public void validating_Super_Admin_Registration()
//    {
//        up=setData();
//        Response response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(up)
//                .when()
//                .post(BASE_URL + "/SuperAdminRegister")
//                .then().log().all()
//                .extract().response();
//
//        if (response.statusCode() == 200 )
//        {
//            Assert.assertTrue(true);
//        } else
//        {
//            Assert.assertTrue(false);
//        }
//    }


    @Test(priority = 3,dependsOnMethods = "validating_SuperAdmin_showSuperAdmin_Get")
    public void validating_SuperAdminLogin_with_validaData() {
        for (int i = 0; i < S_A_ID.size(); i++)
        {
            System.out.println(S_A_Username);
            System.out.println(S_A_Password);
            fk = new Faker();
            up = new User();
            up.setUsername(S_A_Username.get(i).toString());
            up.setPassword(S_A_Password.get(i).toString());

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .post(BASE_URL + "/SuperAdminLogin")
                    .then().log().all()
                    .statusCode(200)
                    .extract().response();

            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
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

    @Test(priority = 5,dependsOnMethods = "validating_SuperAdmin_showSuperAdmin_Get")
    public void validating_SuperAdmin_Get_By_ID() {

        for (int i=0; i<S_A_ID.size();i++) {
            Response response = RestAssured.given()
                    .get(BASE_URL + "/SuperAdmin/" +S_A_ID.get(i))
                    .then().log().all()
                    .statusCode(200)
                    .extract().response();

            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }


    @Test(priority = 5)
    public void validating_SuperAdmin_GetAll_Method() {
        Response response = RestAssured.given()
                .get(BASE_URL + "/GetSuperAdmin") // Add a slash before GetSuperAdmin
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

    @Test(priority = 7,dependsOnMethods = "validating_SuperAdmin_showSuperAdmin_Get")
    public void validating_Super_Admin_Update_By_Id() {
        for (int i=0; i<S_A_ID.size();i++) {

            up = setData();
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .patch(BASE_URL + "/SuperAdmin/" + S_A_ID.get(i))
                    .then().log().all()
                    .extract().response();

            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }

    @Test(priority = 8,dependsOnMethods = "validating_SuperAdmin_showSuperAdmin_Get")
    public void validating_Super_Admin_Change_Password() {
        for (int i=0; i<S_A_ID.size();i++) {
            fk = new Faker();
            up = new User();

            up.setUsername(S_A_Username.get(i).toString());
            up.setCurrentpassword(S_A_Password.get(i).toString());
            up.setPassword("Praveen1997");
            up.setConfirmpassword("Praveen1997");

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .post(BASE_URL + "/ChangePassword")
                    .then().log().all()
                    .extract().response();

            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }

    @Test(priority = 9,dependsOnMethods = "validating_SuperAdmin_showSuperAdmin_Get")
    public void validating_Super_Admin_ForgotPassword() {
        for (int i=0; i<S_A_ID.size();i++) {

            fk = new Faker();
            up = new User();
            up.setEmail(S_A_Email.get(i).toString());

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .post(BASE_URL + "/forgotPassword")
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








