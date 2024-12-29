package client.common.extentreport;

import client.common.DriverClass;
import client.common.ScreenshotUtility;
import client.common.TestCaseLabRun;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static client.common.TestCaseLabRun.*;

@Log4j2
public class TestListener extends DriverClass implements ITestListener {
    public static Integer TEST_CASE_ID;
    public static Integer TEST_RESULT_ID;
    public static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        log.info("Test Suite started!");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test Suite finished!");
        ExtentManager.flush();
    }

    @SneakyThrows
    @Override
    public void onTestStart(ITestResult result) {
        test = new ExtentTestManager().createTest(result.getMethod().getMethodName());
        test.assignCategory("Test ID: "+ result.getMethod().getDescription());
        log.info(result.getMethod().getMethodName() + " started!");
        TEST_CASE_ID = getTestCaseIdByTitle(result.getMethod().getDescription());
        addTestCasesToTestRun(TEST_RUN_ID, new int[] {TEST_CASE_ID});
        TEST_RESULT_ID = getTestResultIdByTestCaseId(TEST_RUN_ID, TEST_CASE_ID);
    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
        ExtentManager.flush();
        updateTestCaseStatus(TEST_RESULT_ID, TEST_RUN_ID, TEST_CASE_ID, "passed");

    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");
        test.addScreenCaptureFromBase64String(ScreenshotUtility.captureScreenshot(driver));
        test.log(Status.FAIL, result.getThrowable());
        ExtentManager.flush();
        updateTestCaseStatus(TEST_RESULT_ID, TEST_RUN_ID, TEST_CASE_ID, "failed");
    }

    @SneakyThrows
    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test skipped");
        ExtentManager.flush();
        updateTestCaseStatus(TEST_RESULT_ID, TEST_RUN_ID, TEST_CASE_ID, "blocked");
    }
}