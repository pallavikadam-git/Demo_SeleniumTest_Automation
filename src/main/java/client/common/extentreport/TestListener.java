package client.common.extentreport;

import client.common.DriverClass;
import client.common.ScreenshotUtility;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends DriverClass implements ITestListener {
    public static ExtentTest test;
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite finished!");
        ExtentManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Initialize ExtentTest in ExtentTestManager
        test = new ExtentTestManager().createTest(result.getMethod().getMethodName());
        test.assignCategory("Test ID: "+ result.getMethod().getDescription());
        System.out.println(result.getMethod().getMethodName() + " started!");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
        ExtentManager.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure and take a screenshot
        test.log(Status.FAIL, "Test failed");
        test.addScreenCaptureFromBase64String(ScreenshotUtility.captureScreenshot(driver));
        test.log(Status.FAIL, result.getThrowable());
        ExtentManager.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test skipped");
        ExtentManager.flush();
    }
}
