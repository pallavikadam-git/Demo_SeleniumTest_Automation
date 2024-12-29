package client.common.reportportal;

import com.epam.reportportal.service.ReportPortal;
//import com.ontada.qa.shared.legacy.TestCaseIdValue;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;

@Log4j2
public class ReportPortalHelper {
    private static String TestCaseIdValue = "221"; //TestCaseIdValue.getID()
//    private static final Logger log = LogManager.getLogger(ReportPortalHelper.class);
//    private static final ThreadLocal<File> qTestFileStorage = new ThreadLocal();

    private ReportPortalHelper() {
        throw new UnsupportedOperationException("Illegal access to private constructor");
    }

    public static synchronized void saveAttachments(ITestResult testResult, WebDriver driver) throws Throwable {
        Method testMethod = testResult.getMethod().getConstructorOrMethod().getMethod();
        WebDriver webDriver = (new Augmenter()).augment(driver);
        String fileName = String.format("[%s]%s-%d-%d", TestCaseIdValue, testMethod.getName(), Thread.currentThread().getId(), Calendar.getInstance().getTime().getTime());
        attacheScreenshot(webDriver, fileName);
//        attacheHtml(webDriver.getPageSource(), fileName);
    }

    public static void attacheScreenshot(WebDriver driver, String screenshotName) throws Throwable {
        try {
            screenshotName = screenshotName + ".png";
            log.info("Taking screenshot: [{}].", screenshotName);
            Path destFolder = Paths.get("target", "report-portal", TestCaseIdValue).toAbsolutePath();
            Files.createDirectories(destFolder);
            Path destFile = Paths.get(destFolder.toString(), screenshotName);
//            File screenshot = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//            qTestFileStorage.set(destFile.toFile());
            Files.copy(new DataInputStream(new FileInputStream(screenshot)), destFile, new CopyOption[0]);
            attacheFile(destFile.toFile(), "ERROR");
        } catch (Throwable var5) {
            Throwable $ex = var5;
            throw $ex;
        }
    }

    public static void attacheHtml(String source, String fileName) throws Throwable {
        try {
            fileName = fileName + ".html";
            log.info("Capturing page source: [{}].", fileName);
            Path destFolder = Paths.get("target", "report-portal", TestCaseIdValue).toAbsolutePath();
            Files.createDirectories(destFolder);
            Path destFile = Paths.get(destFolder.toString(), fileName);
            Files.copy(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), destFile, new CopyOption[0]);
            attacheFile(destFile.toFile(), "ERROR");
        } catch (Throwable var4) {
            Throwable $ex = var4;
            throw $ex;
        }
    }

    public static void attacheTxt(String source) throws Throwable {
        try {
            String fileName = String.format("[%s]-%d-%d.txt", TestCaseIdValue, Thread.currentThread().getId(), Calendar.getInstance().getTime().getTime());
            Path destFolder = Paths.get("target", "report-portal", TestCaseIdValue).toAbsolutePath();
            Files.createDirectories(destFolder);
            Path destFile = Paths.get(destFolder.toString(), fileName);
            Files.copy(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)), destFile, new CopyOption[0]);
            attacheFile(destFile.toFile(), "INFO");
        } catch (Throwable var4) {
            Throwable $ex = var4;
            throw $ex;
        }
    }

    public static void attacheFile(File file, String level) {
        if (Objects.nonNull(file) && file.exists() && file.isFile()) {
            log.info("Adding attachment [{}] to report portal.", file.getName());
            ReportPortal.emitLog(file.getName(), Objects.nonNull(level) ? level.toUpperCase() : "INFO", Calendar.getInstance().getTime(), file);
        } else {
            log.error("The file can not be attached to report portal. File [{}].", file);
        }
    }

//    public static void cleanUp() {
//        qTestFileStorage.remove();
//    }
//
//    public static ThreadLocal<File> getQTestFileStorage() {
//        return qTestFileStorage;
//    }
}
