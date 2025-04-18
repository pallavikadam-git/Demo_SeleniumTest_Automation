package client;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.util.Set;

public class TestClass {

    public static void main(String[] args) {

        //1. https://nichethyself.com/tourism/home.html
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://nichethyself.com/tourism/home.html");

        //2. Go to "Customized tours"
        WebElement customizedTours = driver.findElement(By.xpath("//*[text()='Customized tours']"));
        customizedTours.click();

        //3. Select "Home Stay" in preferred stay dropwdown/combo towards the bottom of the page.
        String curremtWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for(String window : windows){
            if(!window.equals(curremtWindow)){
                driver.switchTo().window(window);
            }
        }
        WebElement preferredStay = driver.findElement(By.xpath("//select[@id='days']"));
        Select select = new Select(preferredStay);
        select.selectByVisibleText("Home Stay");

        //4. Click on "England" checkbox
        WebElement england = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
        england.click();

        //5. Now click on "Contact Us" menu on top right corner
        WebElement contactUs = driver.findElement(By.xpath("//button[text()='Contact us!']"));
        contactUs.click();

        //6. On "Contact Us" window, click on Search icon.
        String secondWindow = driver.getWindowHandle();
        windows = driver.getWindowHandles();
        for(String window : windows){
            driver.switchTo().window(window);
            if(driver.getTitle().equals("Contact us")){
                break;
            }
        }
        WebElement searchIcon = driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search']"));
        searchIcon.click();

        //7. Enter "London" on the pop up window and click on OK.
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("London");
        alert.accept();

        //8. Close "Contact Us" window and click on "Submit" button on customized tour STC Tourism
        driver.close();
        driver.switchTo().window(secondWindow);
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        driver.quit();
    }
}
