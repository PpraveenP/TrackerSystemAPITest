package Employee_Tracker.Tests;

import Employee_Tracker.Payloads.Admin_User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class Admin {

    private static final String BASE_URL = "http://localhost:8086/api/auth/Admin/";

    Faker fk;
    Admin_User up;
    List<Object> Admin_id;
    List<String > Admin_username;
    List<String> Admin_Password;
    public Response getAdminData( Object uid) {
        Object AdminId = uid;
        Response response = RestAssured.given()
                .get(BASE_URL +"Admin/"+AdminId);
        return response;
    }
    public Admin_User setdata()
    {
        Faker fk;
        Admin_User up;
        fk = new Faker();
        up = new Admin_User();
        up.setName(fk.name().firstName());
        up.setAddress(fk.name().lastName());
        up.setEmail(fk.internet().safeEmailAddress());
        up.setContact(fk.number().digits(10));
        up.setUsername(fk.name().firstName());
        String password=fk.internet().password();
        up.setPassword(password);
        up.setConfirmpassword(password);
        up.setCity(fk.name().name());
        up.setCountryCode("91");
        up.setCountry("India");
        up.setCreatedby(fk.name().firstName());
        up.setUpdatedby(fk.name().lastName());
        up.setState(fk.name().name());
        up.setOrganizationName(fk.name().lastName());
        up.setOrganizationType("IT");
        up.setSubscriptionType("paid");
        return up;
    }
    @Test(priority = 1)
    public void validating_Admin_Registration() {
        up = setdata();
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(BASE_URL + "AdminRegister")
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 200) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }



    @Test(priority = 3,dependsOnMethods = "validating_Admin_showAllAdmin_Get")
    public void validating_Admin_Login_with_valid_Data() {
        for (int i = 0; i < Admin_id.size(); i++) {

            Response rs = getAdminData(Admin_id.get(i));
            fk = new Faker();
            up = new Admin_User();

            up.setUsername(rs.jsonPath().getString("username"));
            up.setPassword(rs.jsonPath().getString("confirmpassword"));
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .post(BASE_URL + "AdminLogin")
                    .then().log().all()
                    .extract().response();
//            System.out.println("Request Body: " + up.toString());
//            System.out.println("Response Body: " + response.getBody().asString());
//
           if (response.statusCode()==200)
           {
               Assert.assertTrue(true);
           }
           else
           {
               Assert.assertTrue(false);
           }


        }
    }

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

    @Test(priority = 4)
    public void validating_Admin_generate_pdf_Get() {
        Response response = RestAssured.given()
                .get(BASE_URL + "generate-pdf")
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
    @Test(priority = 5 ,dependsOnMethods = "validating_Admin_showAllAdmin_Get")
    public void validating_Admin_Get_By_validID() {
    for(int i=0;i<Admin_id.size();i++) {
        Response response = RestAssured.given()
                .get(BASE_URL + "Admin/"+Admin_id.get(i))
                .then().log().all()
                .extract().response();

        if (response.statusCode() == 200) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
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

    @Test(priority = 6)
    public void validating_Super_Admin_Update_By_Id() {
        // Define the SuperAdmin ID you want to update
        int AdminIdToUpdate = 1; // Change this ID based on your scenario

        // Assuming you have an updatedUser object with the new data
     for (int i=0; i<Admin_id.size();i++) {
         up = setdata();
         Response response = RestAssured.given()
                 .contentType(ContentType.JSON)
                 .body(up)
                 .when()
                 .patch(BASE_URL + "Admin/" + Admin_id.get(i))
                 .then().log().all()
                 .extract().response();

         if (response.statusCode() == 200) {
             Assert.assertTrue(true);
         } else {
             Assert.assertTrue(false);
         }
     }
    }

    @Test(priority = 7)
    public void validating_Admin_Change_Password(){

        for (int i=0;i<Admin_id.size();i++)
        {
            Admin_User up;
            fk = new Faker();
            up = new Admin_User();
            Response rs = getAdminData(Admin_id.get(i));
            up.setUsername(rs.jsonPath().getString("username"));
            up.setCurrentpassword(rs.jsonPath().getString("confirmpassword"));
            up.setPassword("Praveen1997");
            up.setConfirmpassword("Praveen1997");

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


    @Test(priority = 8)
    public void validating_Admin_ForgotPassword() {

        for (int i=0;i<Admin_id.size();i++) {
            Admin_User up;
            fk = new Faker();
            up = new Admin_User();
            Response rs = getAdminData(Admin_id.get(i));

            up.setEmail(rs.jsonPath().getString("email"));

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

