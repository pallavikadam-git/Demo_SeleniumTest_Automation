package client.common.reportportal;

import client.common.DriverClass;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class ReportPortalTestListener extends ReportPortalTestNGListener {

    @Override
    public void onTestStart(ITestResult result) {
//        ReportPortalConfig.startLaunch();
        super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ReportPortalHelper.saveAttachments(result, DriverClass.getDriver());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        super.onTestFailure(result);
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
//        ReportPortalConfig.stopLaunch();
        super.onFinish(context);
    }
}
