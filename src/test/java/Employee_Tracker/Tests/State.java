package Employee_Tracker.Tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class State {

    public  String BASE_URL="http://localhost:8086/api/auth/states/";
    List<Object> state_id;
    @Test
    public void validating_Get_All_State()
    {
        Response response = RestAssured.given()
                .get(BASE_URL+"getallstates")
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
    }
    @Test
    public void validating_Get_State_By_ID()
    {
        for (Object id:state_id)
            {
                Response response=RestAssured.given()
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
}
