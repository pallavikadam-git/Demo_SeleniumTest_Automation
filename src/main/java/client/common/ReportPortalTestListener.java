package client.common;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
//import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.sql.DriverManager;

public class ReportPortalTestListener extends ReportPortalTestNGListener {

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        // Add additional logic here if needed
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) throws Throwable {
        super.onTestFailure(result);
        ReportPortalHelper.saveAttachments(result, DriverClass.getDriver());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);
    }

    @Override
    public void onStart(ITestContext context) {
        super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        super.onFinish(context);
    }
}
