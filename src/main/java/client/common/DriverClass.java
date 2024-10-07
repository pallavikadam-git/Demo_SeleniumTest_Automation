package client.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class DriverClass {
    public static WebDriver driver;

    @BeforeMethod
    public void driverSetUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(LoginCreds.LOGIN_URL);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
