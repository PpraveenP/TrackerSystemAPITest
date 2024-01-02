package Employee_Tracker.Endpoits;

public class Routes {

	private static final String BASE_URL = "http://localhost:8086/api/auth/SuperAdmin";

	//User Module
	public static String SuperAdmin_registerUser_Post="BASE_URL"+"/SuperAdminRegister";
	public static String SuperAdmin_authenticateUser_post="BASE_URL"+"/SuperAdminLogin";
	
	public static String SuperAdmin_showSuperAdmin_Get="BASE_URL"+"/GetSuperAdmin";

	public static String SuperAdmin_getSuperAdminById_Get="BASE_URL"+"SuperAdmin/{id}";
	
}
