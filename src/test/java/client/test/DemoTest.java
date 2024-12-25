package client.test;

import client.common.extentreport.TestListener;
import client.pages.ClientHomePage;
import client.pages.LoginPage;
import client.pages.YourCartPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import client.common.*;

import java.util.Arrays;
import java.util.List;


@Listeners(TestListener.class)
public class DemoTest extends DriverClass {

    /**
     * Author - Kalyan Pawar
     * TC-01 - To validate Filter operations for Alphabetical and reverse Alphabetical options.
     */
    @Test(description = "TC-01")
    public static void validateFilterOperationsForAlphabeticalAndReverseAlphabeticalOptions() {
        String nameA2Z = "Name (A to Z)";
        String nameZ2A = "Name (Z to A)";
        List<String> a2ZList = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)");
        List<String> z2AList = Arrays.asList("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie", "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Bike Light", "Sauce Labs Backpack");

        ClientHomePage clientHomePage = new LoginPage().login();
        clientHomePage.validateSelectedOptionForFilterDropdown(nameA2Z);
        clientHomePage.validateInventoryItemsOrder(a2ZList);
        clientHomePage.selectOptionForFilterDropdown(nameZ2A);
        clientHomePage.validateInventoryItemsOrder(z2AList);
        clientHomePage.logout();
    }

    /**
     * Author - Kalyan Pawar
     * TC-02 - To validate Filter operations for price low to high and high to low.
     */
    @Test(description = "TC-02")
    public static void validateFilterOperationsForPriceLowToHighAndHighToLow() {
        String nameA2Z = "Name (A to Z)";
        String priceLow2High = "Price (low to high)";
        String priceHigh2Low = "Price (high to low)";
        List<String> a2ZList = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)");
        List<String> inventoryLow2HighList = Arrays.asList("Sauce Labs Onesie", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Backpack", "Sauce Labs Fleece Jacket");
        List<String> priceLow2HighList = Arrays.asList("$7.99", "$9.99", "$15.99", "$15.99", "$29.99", "$49.99");
        List<String> inventoryHigh2LowList = Arrays.asList("Sauce Labs Fleece Jacket", "Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Bike Light", "Sauce Labs Onesie");
        List<String> priceHigh2LowList = Arrays.asList("$49.99", "$29.99", "$15.99", "$15.99", "$9.99", "$7.99");

        ClientHomePage clientHomePage = new LoginPage().login();
        clientHomePage.validateSelectedOptionForFilterDropdown(nameA2Z);
        clientHomePage.validateInventoryItemsOrder(a2ZList);
        clientHomePage.selectOptionForFilterDropdown(priceLow2High);
        clientHomePage.validateInventoryItemsOrder(inventoryLow2HighList);
        clientHomePage.validateInventoryItemsPriceOrder(priceLow2HighList);

        clientHomePage.selectOptionForFilterDropdown(priceHigh2Low);
        clientHomePage.validateInventoryItemsOrder(inventoryHigh2LowList);
        clientHomePage.validateInventoryItemsPriceOrder(priceHigh2LowList);

//        clientHomePage.validatePriceForItem("Sauce Labs Onesie", "$7.99");
        clientHomePage.logout();
    }

    /**
     * Author - Kalyan Pawar
     * TC-03 - To validate item details on Cart page.
     */
    @Test(description = "TC-03")
    public static void toValidateItemDetailsOnCartPage() {
        String item1 = "Sauce Labs Onesie";
        String price1 = "$7.99";
        String item2 = "Sauce Labs Bike Light";
        String price2 = "$9.99";
        String description1 = "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed " +
                "sleeved and bottom won't unravel.";
        String description2 = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting " +
                "modes, 1 AAA battery included.";

        ClientHomePage clientHomePage = new LoginPage().login();
        clientHomePage.validatePriceForItem(item1, price1);
        clientHomePage.validatePriceForItem(item2, price2);
        clientHomePage.addItemToCartOrRemove(item1);
        clientHomePage.validateItemsInCart(1);
        clientHomePage.addItemToCartOrRemove(item2);
        clientHomePage.validateItemsInCart(2);
        clientHomePage.addItemToCartOrRemove(item1);
        clientHomePage.validateItemsInCart(1);
        clientHomePage.addItemToCartOrRemove(item1);
        YourCartPage yourCartPage = clientHomePage.clickOpenCart();
        yourCartPage.validateDescriptionForItems(item1, description1);
        yourCartPage.validatePriceForItems(item1, price1);
        yourCartPage.validateDescriptionForItems(item2, description2);
        yourCartPage.validatePriceForItems(item1, price1);
        yourCartPage.removeItemFromCart(item2);
        yourCartPage.clickContinueShoppingButton();
        clientHomePage.validateItemsInCart(1);
        clientHomePage.logout();
    }
}

