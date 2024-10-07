package client.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static ExtentReports extent;
    public static ExtentSparkReporter sparkReporter;

    public static ExtentReports getInstance() {
        if (extent == null) {
            sparkReporter = new ExtentSparkReporter("TestReport/ExtentReport.html");
            sparkReporter.config().setDocumentTitle("Client Automation Report");
            sparkReporter.config().setReportName("Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Project Name", "Client Automation");
            extent.setSystemInfo("Environment", "QA Environment");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("QA", "Kalyan Pawar");
        }
        return extent;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}

