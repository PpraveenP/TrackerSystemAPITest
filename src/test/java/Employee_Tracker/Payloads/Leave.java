package Employee_Tracker.Payloads;

import lombok.Data;

@Data
public class Leave {

    String name ="";
    String email ="";
    String reason ="";
    String type ="";
    String startDate ="";
    String endDate ="";
    String status ="";

}