package Employee_Tracker.Tests;

import Employee_Tracker.Payloads.Employee_User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Employee {
    Faker fk;
    Employee_User up;
    List<Object>  Employee_Id;
    List<Object>  Employee_Email;
    List<Object>  Employee_Username;
    List<Object>  Employee_Password;
    private static final String BASE_URL = "http://localhost:8086/api/auth/Employee/"   ;
    private Employee_User setData()
    {
        fk=new Faker();
        up=new Employee_User();
        up.setName(fk.name().firstName());
         up.setUsername(fk.name().username());
        up.setEmail(fk.internet().safeEmailAddress());
        up.setDesignation(fk.name().firstName());
        up.setContact(fk.number().digits(10));
        up.setAltcontact(fk.number().digits(10));
        up.setCurrentaddress(fk.address().fullAddress());
        up.setPermanentaddress(fk.address().fullAddress());
        up.setCity(fk.name().name());
        up.setState(fk.name().lastName());
        up.setCountry(fk.name().name());
        up.setCountryCode("97");
        String password=fk.internet().password();
        up.setPassword(password);
        up.setConfirmpassword(password);
        up.setCreatedby(fk.name().firstName());
        up.setAdminId(27);
        up.setProbationPeriodInMonths("2");

        LocalDate currentDate = LocalDate.now();
        // Add a specific number of months to the current date (e.g., 1 month in this case)
        LocalDate futureDate = currentDate.plusMonths(1);
        // Format the date using the desired pattern
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        up.setJoiningDate(formattedDate);
//        up.setJoiningDate("2023-11-01");
        up.setUpdatedby(fk.name().lastName());
        up.setMonthlyallowedleaves(fk.number().digits(1));
        up.setTotalsickleave(fk.number().digits(1));
        up.setTotalemeregencyleave(fk.number().digits(1));
        up.setTotalmaternityleave(fk.number().digits(1));
        return up;
    }


@Test(priority = 1)
public void validating_Employee_Registration_With_validData() {

        up=setData();
//      up=new Employee_User();
    Response response = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(up)  // Use the defined employeeDetails
            .when()
            .post(BASE_URL + "EmployeeRegister")
            .then().log().all()
            .extract().response();

    if (response.statusCode() == 200) {
        Assert.assertTrue(true);
    } else {
        Assert.assertTrue(false);
    }
}



        @Test(priority =5,dependsOnMethods = "validating_GetAllEmployees_Method")
    public void validating_Employee_Login_with_validData()
    {
        for (int i=0;i<Employee_Id.size();i++) {

            fk = new Faker();
            up = new Employee_User();
            up.setUsername(Employee_Username.get(i).toString());
            up.setPassword(Employee_Password.get(i).toString());

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .post(BASE_URL + "EmployeeLogin")
                    .then().log().all()
                    .extract().response();
            if (response.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }
    @Test(priority = 3,dependsOnMethods = "validating_GetAllEmployees_Method")
    public  void validating_Employee_Get_By_validID()
    {
        for (int i=0; i<Employee_Id.size() ; i++) {
            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .get(BASE_URL + "Employee/"+Employee_Id.get(i))
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
    public void  validating_GetAllEmployees_Method()
    {
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"GetEmployee")
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
        Employee_Id = response.jsonPath().getList("id");
        Employee_Email=response.jsonPath().getList("email");
        Employee_Username=response.jsonPath().getList("username");
        Employee_Password=response.jsonPath().getList("confirmpassword");

    }
    @Test(priority = 6)
    public void validating_generate_pdf_Method()
    {

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .get(BASE_URL+"generate-pdf")
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

    @Test(priority = 10)
    public void validating_Employee_Delete_By_validID()
    {
       for (int i=0;i<Employee_Id.size()-1;i++) {
           Response response = RestAssured.given()
                   .contentType(ContentType.JSON)
                   .when()
                   .delete(BASE_URL + "Employee/" +Employee_Id.get(i))
                   .then().log().all()
                   .extract().response();
           if (response.statusCode() == 200) {
               Assert.assertTrue(true);
           } else {
               Assert.assertTrue(false);
           }
       }
    }

    @Test(priority = 7,dependsOnMethods = "validating_GetAllEmployees_Method")
    public void validating_Employee_Update_By_validID()
    {
        for (int i=0;i<Employee_Id.size();i++) {
            up = setData();
            Response responce = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(up)
                    .when()
                    .patch(BASE_URL + "Employee/" +Employee_Id.get(i))
                    .then().log().all()
                    .extract().response();
            if (responce.statusCode() == 200) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        }
    }
    @Test(priority = 8,dependsOnMethods = "validating_GetAllEmployees_Method")
    public void validating_Employee_Forget_Password_By_ValidEmailID()
    {
       for (int i=0;i<Employee_Id.size();i++) {
           fk = new Faker();
           up = new Employee_User();
           up.setEmail(Employee_Email.get(i).toString());
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

//    @Test()
//    public void validating_Employee_Reset_Password_By_validID()
//    {
//
//        Faker fk;
//        fk=new Faker();
//        up=new Employee_User();
//        up.setNewPassword("praveen1997");
//        up.setConfirmpassword("praveen1997");
//        up.setOTP("87747");
//        Response response=RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(up)
//                .when()
//                .post(BASE_URL+"ResetPassword")
//                .then().log().all()
//                .extract().response();
//        if(response.statusCode()==200)
//        {
//            Assert.assertTrue(true);
//        }
//        else
//        {
//            Assert.assertTrue(false);
//        }
//    }
//
    @Test(priority = 9,dependsOnMethods = "validating_GetAllEmployees_Method")
    public void validating_Employee_Change_Password_with_validData()
    {
        for (int i=0;i<Employee_Id.size();i++) {
            fk = new Faker();
            up = new Employee_User();
            up.setUsername(Employee_Username.get(i).toString());
            up.setCurrentpassword(Employee_Password.get(i).toString());
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

}
