package client.common;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

    public static String captureScreenshot(WebDriver driver) {
        String src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return src;
    }
}

