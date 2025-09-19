package client;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AutomationExercise {

    public static void main(String[] args) {
//        Steps to Automate
//        1. Launch browser and open https://automationexercise.com.
//        2. Verify home page is visible successfully (check title of page).
//        3. Click on ‘Signup / Login’ button from the top menu.
//        4. Enter Name and Email Address under “New User Signup!” section.
//        5. Click on ‘Signup’ button.
//        6. Fill all required details on Account Information page (Title, Password, DOB, Address, Country, etc.).
//        7. Click on ‘Create Account’ button.
//        8. Verify that the account is created (look for “ACCOUNT CREATED!” message).
//        9. Click ‘Continue’, verify user is logged in (look for “Logged in as username”).
//        10. Click on ‘Products’ page.
//        11. Add a few products to the cart by clicking ‘Add to Cart’.
//        12. Click on ‘Cart’ button from top-right and verify the added products.
//        13. Click ‘Proceed To Checkout’.
//        14. Enter comment in the description box and click ‘Place Order’.
//        15. Enter payment details (dummy data), click ‘Pay and Confirm Order’, and verify the success message.

        String random = String.valueOf(System.currentTimeMillis());

        //1. Launch browser and open https://automationexercise.com.
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
        driver.manage().window().maximize();

        //2. Verify home page is visible successfully (check title of page).
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");

        //3. Click on ‘Signup / Login’ button from the top menu.
        driver.findElement(By.xpath("//a[@href='/login']")).click();

        //4. Enter Name and Email Address under “New User Signup!” section.
        driver.findElement(By.xpath("//form[@action='/signup']/child::input[@type='text']")).sendKeys("Kalyan");
        driver.findElement(By.xpath("//form[@action='/signup']/child::input[@type='email']")).sendKeys(random + "Kalyan@test.com");

        //5. Click on ‘Signup’ button.
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        //6. Fill all required details on Account Information page (Title, Password, DOB, Address, Country, etc.).
        driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Pass@123");

        WebElement day = driver.findElement(By.xpath("//select[@id='days']"));
        WebElement month = driver.findElement(By.xpath("//select[@id='months']"));
        WebElement year = driver.findElement(By.xpath("//select[@id='years']"));

        Select daySelect = new Select(day);
        daySelect.selectByIndex(8);

        Select monthSelect = new Select(month);
        monthSelect.selectByValue("11");

        Select yearSelect = new Select(year);
        yearSelect.selectByVisibleText("1996");

        driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("Kalyan");
        driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys("Pawar");
        driver.findElement(By.xpath("//input[@id='company']")).sendKeys("Sapient");
        driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("157");
        driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Maharashtra");
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Pune");
        driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("424242");
        driver.findElement(By.xpath("//input[@id='mobile_number']")).sendKeys("9234542343");

        //7. Click on ‘Create Account’ button.
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();

        //8. Verify that the account is created (look for “ACCOUNT CREATED!” message).
        String accountMessage = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
        Assert.assertEquals(accountMessage, "ACCOUNT CREATED!");

        //9. Click ‘Continue’, verify user is logged in (look for “Logged in as username”).
        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
        String userMessage = driver.findElement(By.xpath("//i[@class='fa fa-user']/parent::a")).getText();
        Assert.assertEquals(userMessage, "Logged in as Kalyan");

        //10. Click on ‘Products’ page.
        driver.findElement(By.xpath("//a[@href='/products']")).click();

        //11. Add a few products to the cart by clicking ‘Add to Cart’.
        Actions act = new Actions(driver);
        WebElement element1 = driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]"));
        act.moveToElement(element1).click().build().perform();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
        driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[2]")).click();
        driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();

        //12. Click on ‘Cart’ button from top-right and verify the added products.
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(driver.findElement(By.xpath("(//a[contains(@href, '/product_details')])[1]")).getText(), "Men Tshirt");
        sa.assertEquals(driver.findElement(By.xpath("(//a[contains(@href, '/product_details')])[2]")).getText(), "Blue Top");
        sa.assertAll();

        //13. Click ‘Proceed To Checkout’.
        driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

        //14. Enter comment in the description box and click ‘Place Order’.
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Description comments message");
        driver.findElement(By.xpath("//a[text()='Place Order']")).click();

        //15. Enter payment details (dummy data), click ‘Pay and Confirm Order’, and verify the success message.
        
    }
}
