package client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class New {
    public static void main(String[] args) {
//        LocalDate today = LocalDate.now();
//
//        // Add 10 days
//        LocalDate plusTenDays = today.plusDays(10);
//
//        // Subtract 20 days
//        LocalDate minusTwentyDays = today.minusDays(20);
//
//        // Format date (Optional)
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//
//        System.out.println("Today's Date: " + today.format(formatter));
//        System.out.println("Date + 10 Days: " + plusTenDays.format(formatter));
//        System.out.println("Date - 20 Days: " + minusTwentyDays.format(formatter));

        LocalDate today = LocalDate.now();
        LocalDate plusTen = today.plusDays(10);
        LocalDate minusTwenty = today.minusDays(20);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        plusTen.format(formatter);



    }

    @FindBy(name = "username")
    WebElement username;

    WebDriver driver;

//    public loginPage(WebDriver driver){
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }

    String str = "Java Java";
}
