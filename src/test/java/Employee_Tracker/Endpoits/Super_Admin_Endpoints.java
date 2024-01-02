package Employee_Tracker.Endpoits;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import Employee_Tracker.Payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

public class Super_Admin_Endpoints {

    public static Response RegisterSuperAdmin(User up)
    {
        Response ro =
        given()
            .contentType(ContentType.JSON)
            .body(up)
            .when()
            .post(Routes.SuperAdmin_registerUser_Post);
        return ro;
    }

    public static Response SuperAdmin_authenticateUser_post(User up)
    {
        String post_url = Routes.SuperAdmin_authenticateUser_post;
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(up)
                .when()
                .post(post_url);
        return response;
    }
//    public static Response SuperAdmin_showSuperAdmin_Get(User up)
//    {
//        String post_url = getUrl().getString("post_url");
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .body(up)
//                .when()
//                .post(post_url);
//        return response;
//
//    }
//
    public static Response SuperAdmin_showSuperAdmin_Get()
    {
        String get_url = Routes.SuperAdmin_showSuperAdmin_Get;
        Response response = given()
                .when()
                .get(get_url);
        return response;
    }
//
//    public static Response updateUser(String userName, User up)
//    {
//        String put_url = getUrl().getString("put_url");
//        Response response=given()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .pathParam("username", userName)
//                .body(up)
//
//                .when()
//                .put(put_url);
//
//        return response;
//    }
//
//    public static Response deleteUser(String userName)
//    {
//        String delet_url = getUrl().getString("delet_url");
//
//        Response response=given()
//                .pathParam("username", userName)
//                .when()
//                .delete(delet_url);
//        return response;
//    }

}
