package client.common.reportportal;

import com.epam.reportportal.listeners.ItemStatus;
import java.util.concurrent.atomic.AtomicInteger;

import com.epam.reportportal.testng.ITestNGService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IExecutionListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener2;

public class BaseTestNGListener implements IExecutionListener, ISuiteListener, IResultListener2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTestNGListener.class);
    private static final AtomicInteger INSTANCES = new AtomicInteger(0);
    private final ITestNGService testNGService;

    public BaseTestNGListener(ITestNGService testNgService) {
        this.testNGService = testNgService;
        if (INSTANCES.incrementAndGet() > 1) {
            String warning = "WARNING! More than one ReportPortal listener is added";
            LOGGER.warn("WARNING! More than one ReportPortal listener is added");
            System.out.println("WARNING! More than one ReportPortal listener is added");
        }

    }

    public void onExecutionStart() {
        this.testNGService.startLaunch();
    }

    public void onExecutionFinish() {
        this.testNGService.finishLaunch();
    }

    public void onStart(ISuite suite) {
        this.testNGService.startTestSuite(suite);
    }

    public void onFinish(ISuite suite) {
        this.testNGService.finishTestSuite(suite);
    }

    public void onStart(ITestContext testContext) {
        this.testNGService.startTest(testContext);
    }

    public void onFinish(ITestContext testContext) {
        this.testNGService.finishTest(testContext);
    }

    public void onTestStart(ITestResult testResult) {
        this.testNGService.startTestMethod(testResult);
    }

    public void onTestSuccess(ITestResult testResult) {
        this.testNGService.finishTestMethod(ItemStatus.PASSED, testResult);
    }

    public void onTestFailure(ITestResult testResult) {
        this.testNGService.sendReportPortalMsg(testResult);
        this.testNGService.finishTestMethod(ItemStatus.FAILED, testResult);
    }

    public void onTestSkipped(ITestResult testResult) {
        this.testNGService.finishTestMethod(ItemStatus.SKIPPED, testResult);
    }

    public void beforeConfiguration(ITestResult testResult) {
        this.testNGService.startConfiguration(testResult);
    }

    public void onConfigurationFailure(ITestResult testResult) {
        this.testNGService.sendReportPortalMsg(testResult);
        this.testNGService.finishTestMethod(ItemStatus.FAILED, testResult);

    }

    public void onConfigurationSuccess(ITestResult testResult) {
        this.testNGService.finishTestMethod(ItemStatus.PASSED, testResult);
    }

    public void onConfigurationSkip(ITestResult testResult) {
        this.testNGService.startConfiguration(testResult);
        this.testNGService.finishTestMethod(ItemStatus.SKIPPED, testResult);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        this.testNGService.finishTestMethod(ItemStatus.FAILED, result);
    }
}
