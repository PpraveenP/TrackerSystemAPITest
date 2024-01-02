package Employee_Tracker.Payloads;

import lombok.Data;

@Data
public class Admin_User {

    String name = "";
    String username = "";
    String email = "";
    String contact = "";
    String createdby = "";
    String updatedby = "";
    String address = "";
    String city = "";
    String state = "";
    String country = "";
    String countryCode = "";
    String password = "";
    String confirmpassword = "";
    String organizationName="";
    String organizationType="";
    String Currentpassword="";

   String subscriptionType="";
}