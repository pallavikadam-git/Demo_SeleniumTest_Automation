package client.common;

import client.common.reportportal.ReportPortalConfig;
import lombok.Getter;
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

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DriverClass {
    @Getter
    public static WebDriver driver;
//    public static Integer TEST_RUN_ID;

    @SneakyThrows
    @BeforeSuite
    public void beforeSuite(){
        ReportPortalConfig.startLaunch();
    }

    @BeforeMethod
    @Parameters(value={"url", "browser", "isHeadless"})
    public void driverSetUp(@Optional(LoginCreds.LOGIN_URL) String url, @Optional("chrome") String browser, @Optional("false") boolean isHeadless){
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
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);
            if(isHeadless)
                options.addArguments("--headless=old");
            driver = new ChromeDriver(options);
        }
        else if(browserName.equalsIgnoreCase("edge")){
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-gpu");
            if(isHeadless)
                options.addArguments("--headless");
            driver = new EdgeDriver(options);
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            if(isHeadless)
                options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }
        return driver;
    }
}
