package Employee_Tracker.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkRepoter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp=new SimpleDateFormat("dd-mm-yyyy---hh-mm-ss").format(new Date()); //time Stamp
		repName="Test_Report-"+timeStamp+".html";
		
		sparkRepoter=new ExtentSparkReporter(".//Reports//"+repName); //specify the location of the file
		
		sparkRepoter.config().setDocumentTitle("EmployeeTrackerSystem_API_Automation"); //title of the report
		sparkRepoter.config().setReportName("Employee_Tracker_System_API_Test_Report"); //name of report
		sparkRepoter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkRepoter);
		extent.setSystemInfo("Application","Employee Tracker System");
		extent.setSystemInfo("Operating System",System.getProperty("os.name"));
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment Name","QA");
		extent.setSystemInfo("User","Praveen");
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS,"Test Passed");
		
		
	}
	
	public void onTestFailuer(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,"Test Failed");
		test.log(Status.FAIL,result.getThrowable().getMessage());
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,"Test Skipped");
		test.log(Status.SKIP,result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	
	
}
