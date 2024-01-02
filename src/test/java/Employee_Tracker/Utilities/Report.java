package Employee_Tracker.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;

public class Report {

    public static void main(String[] args) throws IOException {
        ExtentReports extentReports=new ExtentReports();
        File file=new File("report.html");
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(file);
        extentReports.attachReporter(sparkReporter);
        extentReports
                .createTest("screen shot test 1","this in iformation ").info("this is info mesg");
        extentReports.flush();
        Desktop.getDesktop().browse(new File("report.html").toURI());
    }
    public static void captureScreenshot(String filename)
    {

    }
}
