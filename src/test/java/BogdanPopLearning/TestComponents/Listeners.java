package BogdanPopLearning.TestComponents;


import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import BogdanPopLearning.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // Assign unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");

		//test.addScreenCaptureFromPath(null);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().log(Status.FAIL, "Test Failed");
	    extentTest.get().fail(result.getThrowable());

	    try {
	    	Field field = result.getTestClass()
	    	        .getRealClass()
	    	        .getSuperclass()
	    	        .getDeclaredField("driver");

	        field.setAccessible(true);
	        WebDriver driver = (WebDriver) field.get(result.getInstance());

	        String filePath = getScreenShot(result.getMethod().getMethodName(), driver);

	        if (filePath != null && !filePath.trim().isEmpty()) {
	        	extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	        } else {
	        	extentTest.get().warning("Screenshot path was null or empty");
	        }

	    } catch (Exception e) {
	    	extentTest.get().warning("Screenshot could not be attached: " + e.getMessage());
	    }
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
