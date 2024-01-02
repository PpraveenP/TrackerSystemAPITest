package Employee_Tracker.Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Country {

    List<Object>  state_id;
    List<Object>  state_name;
    List<Object>  country_id;
    List<Object> countryName;

    public String BASE_URL="http://localhost:8086/api/auth/countries/";
    @Test(priority = 1)
    public void validating_Get_All_Countries()
    {
        Response response= RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"countries")
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
        JsonPath js=response.jsonPath();
        state_id = js.getList("state_id");
        state_name= js.getList("state_name");
        country_id=js.getList("country_id");
       countryName=js.getList("country");
    }
    @Test(priority = 4,dependsOnMethods = "validating_Get_All_Countries")
    public void validating_Get_Country_By_valid_ID()
    {
        for (Object id:country_id)
        {
            Response response=RestAssured.given()
                    .contentType(ContentType.JSON)
                    .get(BASE_URL+id)
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
    @Test(priority = 3)
    public void validating_Get_CountyInformation_By_CountryName()
    {
        for (Object name:countryName)
        {
            Response response=RestAssured.given()
                    .contentType(ContentType.JSON)
                    .get(BASE_URL+"info/"+name)
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
    @Test(priority = 2)
    public void validating_Get_All_Country_Names()
    {
        Response response=RestAssured.given()
                .get(BASE_URL+"country-names")
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
