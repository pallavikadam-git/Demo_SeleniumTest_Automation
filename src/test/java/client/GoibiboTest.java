package client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class GoibiboTest {

    public static void main(String[] args) {
        //1. Go to https://www.goibibo.com/
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.goibibo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //2. In From - type - Ben
        driver.findElement(By.xpath("//span[@class='sc-koXPp bDtzaf']")).click();
        driver.findElement(By.xpath("//p[@class='sc-jlwm9r-1 ewETUe']")).click();
        WebElement iframe = driver.findElement(By.xpath("//iframe[@name='webpush-onsite']"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.xpath("//button[@id='deny']")).click();
        driver.switchTo().defaultContent();

        driver.findElement(By.xpath("//*[text()='From']/following-sibling::p")).click();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Ben");

        //3. Select Bengaluru from the list.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> searchList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='autoCompleteTitle ']")));
        for(WebElement element : searchList){
            if(element.getText().contains("Bengaluru")) {
                element.click();
                break;
            }
        }

        //4. Verify that the Bangluru is indeed selected
        WebElement from = driver.findElement(By.xpath("//span[text()='From']/following-sibling::p[@class='sc-12foipm-4 czGBLf fswWidgetTitle']"));
        Assert.assertEquals(from.getText(), "Bengaluru");
        driver.quit();
    }
}
