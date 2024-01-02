package Employee_Tracker.Tests;

import Employee_Tracker.Payloads.Client_User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Client {

    public String Base_URL="http://localhost:8086/api/auth/Client/";
    @Test()
    public void validating_Client_Registration_with_valid_Data()
    {
        Faker fk=new Faker();
        Client_User up=new Client_User();
        up.setName(fk.name().firstName());
        up.setEmail(fk.internet().emailAddress());
        up.setContact(fk.number().digits(10));
        up.setAddress(fk.address().fullAddress());
        up.setAssigned(fk.name().firstName());
        up.setAltcontact(fk.number().digits(10));
        up.setCreatedby(fk.name().lastName());
        up.setLandmark(fk.name().nameWithMiddle());
        up.setUpdatedby(fk.name().lastName());
        up.setOrganizationName(fk.name().firstName());
        up.setOrganizationType("IT");

        Response response= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .post(Base_URL+"ClientRegister")
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
    @Test()
    public void validating_Client_Update_By_validID()
    {
        int ClientID=1;
        Faker fk=new Faker();
        Client_User up=new Client_User();
        up.setName(fk.name().firstName());
        up.setEmail(fk.internet().emailAddress());
        up.setContact(fk.number().digits(10));
        up.setAddress(fk.address().fullAddress());
        up.setAssigned(fk.name().firstName());
        up.setAltcontact(fk.number().digits(10));
        up.setCreatedby(fk.name().lastName());
        up.setLandmark(fk.name().nameWithMiddle());
        up.setUpdatedby(fk.name().lastName());
        up.setOrganizationName(fk.name().firstName());
        up.setOrganizationType("IT");

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(up)
                .when()
                .patch(Base_URL+"Client")
                .then().log().all()
                .extract().response();
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
