package client.common;

import client.common.reportportal.ReportPortalConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

@Slf4j
public class DriverClass {
    public static WebDriver driver;
    public static Integer TEST_RUN_ID;

    @SneakyThrows
    @BeforeSuite
    public void beforeSuite(){
        ReportPortalConfig.startLaunch();
        TEST_RUN_ID = TestCaseLabRun.createTestRun("Regression Run - " + System.currentTimeMillis());
        log.info("Test Run Created with ID: " + TEST_RUN_ID);
    }

    @BeforeMethod
    @Parameters(value={"url", "browser", "isHeadless"})
    public void driverSetUp(@Optional(ClientUrl.LOGIN_URL) String url, @Optional("chrome") String browser, @Optional("false") boolean isHeadless){
        driver = setBrowser(browser, isHeadless);
        driver.manage().window().maximize();
//        driver.manage().window().minimize();
        if (url == null || url.isEmpty()) {
            url = LoginCreds.LOGIN_URL;
        }
        driver.get(url);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @AfterSuite
    public void afterSuite(){
        ReportPortalConfig.stopLaunch();
    }

    private static WebDriver setBrowser(String browserName, boolean isHeadless){
        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            if(isHeadless)
                options.addArguments("--headless=old");
            driver = new ChromeDriver(options);
        }
        else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-gpu");
            if(isHeadless)
                options.addArguments("--headless");
            driver = new EdgeDriver(options);
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if(isHeadless)
                options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }
        return driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
