package client.test;

import client.common.extentreport.TestListener;
import client.pages.CheckOutPage;
import client.pages.ClientHomePage;
import client.pages.LoginPage;
import client.pages.YourCartPage;
import com.epam.reportportal.annotations.Description;
import com.epam.reportportal.annotations.TestCaseId;
import org.testng.Assert;
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
    @Test(description = "TC-01", groups = {"FilterTests", "Regression"})
    @TestCaseId("TC-01") // Test case ID in Report Portal
    @Description("Validate Filter Operations For Alphabetical and Reverse Alphabetical Options.")
    public static void validateFilterOperationsForAlphabeticalAndReverseAlphabeticalOptions() {
        String nameA2Z = "Name (A to Z)";
        String nameZ2A = "Name (Z to A)";
        List<String> a2ZList = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)");
        List<String> z2AList = Arrays.asList("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie",
                "Sauce Labs Fleece Jacket", "Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light",
                "Sauce Labs Backpack");

        /* Step 1: Login to the application */
        LoginPage loginPage = new LoginPage();
        ClientHomePage clientHomePage = loginPage.login();

        /* Step 2: Validate default filter is Name (A to Z) */
        clientHomePage.validateSelectedOptionForFilterDropdown(nameA2Z);

        /* Step 3: Validate inventory is sorted in ascending order */
        clientHomePage.validateInventoryItemsOrder(a2ZList);

        /* Step 4: Change filter to Name (Z to A) */
        clientHomePage.selectOptionForFilterDropdown(nameZ2A);

        /* Step 5: Validate inventory is sorted in descending order */
        clientHomePage.validateInventoryItemsOrder(z2AList);

        /* Step 6: Logout */
        clientHomePage.logout();
    }

    /**
     * Author - Kalyan Pawar
     * TC-02 - To validate Filter operations for price low to high and high to low.
     */
    @Test(description = "TC-02", groups = {"FilterTests", "Regression"})
    @TestCaseId("TC-02") // Test case ID in Report Portal
    @Description("Validate Filter Operations For Price Low-High and High-Low.")
    public static void validateFilterOperationsForPriceLowToHighAndHighToLow() {
        String nameA2Z = "Name (A to Z)";
        String priceLow2High = "Price (low to high)";
        String priceHigh2Low = "Price (high to low)";
        List<String> a2ZList = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)");
        List<String> inventoryLow2HighList = Arrays.asList("Sauce Labs Onesie", "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack",
                "Sauce Labs Fleece Jacket");
        List<String> priceLow2HighList = Arrays.asList("$7.99", "$9.99", "$15.99", "$15.99", "$29.99", "$49.99");
        List<String> inventoryHigh2LowList = Arrays.asList("Sauce Labs Fleece Jacket", "Sauce Labs Backpack",
                "Sauce Labs Bolt T-Shirt", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light",
                "Sauce Labs Onesie");
        List<String> priceHigh2LowList = Arrays.asList("$49.99", "$29.99", "$15.99", "$15.99", "$9.99", "$7.99");

        /* Step 1: Login to the application */
        ClientHomePage clientHomePage = new LoginPage().login();

        /* Step 2: Validate default filter is Name (A to Z) and order */
        clientHomePage.validateSelectedOptionForFilterDropdown(nameA2Z);
        clientHomePage.validateInventoryItemsOrder(a2ZList);

        /* Step 3: Change filter to Price (low to high) and validate order and prices */
        clientHomePage.selectOptionForFilterDropdown(priceLow2High);
        clientHomePage.validateInventoryItemsOrder(inventoryLow2HighList);
        clientHomePage.validateInventoryItemsPriceOrder(priceLow2HighList);

        /* Step 4: Change filter to Price (high to low) and validate order and prices */
        clientHomePage.selectOptionForFilterDropdown(priceHigh2Low);
        clientHomePage.validateInventoryItemsOrder(inventoryHigh2LowList);
        clientHomePage.validateInventoryItemsPriceOrder(priceHigh2LowList);
        clientHomePage.validatePriceForItem("Sauce Labs Onesie", "$7.99");

        /* Step 5: Logout */
        clientHomePage.logout();
    }

    /**
     * Author - Kalyan Pawar
     * TC-03 - To validate item details on Cart page.
     */
    @Test(description = "TC-03", groups = {"PriceTests", "Regression"})
    @TestCaseId("TC-03") // Test case ID in Report Portal
    @Description("To validate Item Details On Cart Page.")
    public static void toValidateItemDetailsOnCartPage() {
        String item1 = "Sauce Labs Onesie";
        String price1 = "$7.99";
        String item2 = "Sauce Labs Bike Light";
        String price2 = "$9.99";
        String description1 = "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.";
        String description2 = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";

        /* Step 1: Login */
        ClientHomePage clientHomePage = new LoginPage().login();

        /* Step 2: Validate prices for items */
        clientHomePage.validatePriceForItem(item1, price1);
        clientHomePage.validatePriceForItem(item2, price2);

        /* Step 3: Add/remove items and validate cart count */
        clientHomePage.addItemToCartOrRemove(item1);
        clientHomePage.validateItemsInCart(1);
        clientHomePage.addItemToCartOrRemove(item2);
        clientHomePage.validateItemsInCart(2);
        clientHomePage.addItemToCartOrRemove(item1);
        clientHomePage.validateItemsInCart(1);
        clientHomePage.addItemToCartOrRemove(item1);

        /* Step 4: Open cart and validate descriptions/prices */
        YourCartPage yourCartPage = clientHomePage.clickOpenCart();
        yourCartPage.validateDescriptionForItems(item1, description1);
        yourCartPage.validatePriceForItems(item1, price1);
        yourCartPage.validateDescriptionForItems(item2, description2);
        yourCartPage.validatePriceForItems(item1, price1);

        /* Step 5: Remove item and continue shopping */
        yourCartPage.removeItemFromCart(item2);
        clientHomePage = yourCartPage.clickContinueShoppingButton();
        clientHomePage.validateItemsInCart(1);

        /* Step 6: Logout and validate login page */
        clientHomePage.logout();
        LoginPage.validateLoginPageIsOpened();
    }

    /**
     * Author - Kalyan Pawar
     * TC-04 - To validate Social Media hyperlinks.
     */
    @Test(description = "TC-04", groups = {"Regression"})
    @TestCaseId("TC-04") // Test case ID in Report Portal
    @Description("To validate Social Media hyperlinks.")
    public static void toValidateSocialMediaHyperLinks() {
        String twitterUrl = "https://x.com/saucelabs";
        String facebookUrl = "https://www.facebook.com/saucelabs";
        String linkedInUrl = "https://www.linkedin.com/company/sauce-labs/";

        /* Step 1: Login */
        ClientHomePage clientHomePage = new LoginPage().login();

        /* Step 2: Scroll to footer */
        BaseClass.scrollToBottom();

        /* Step 3: Validate Twitter link opens correct page */
        clientHomePage.clickTwitterIcon();
        clientHomePage.validateNewlyOpenedTab(twitterUrl);

        /* Step 4: Validate Facebook link opens correct page */
        clientHomePage.clickFacebookIcon();
        clientHomePage.validateNewlyOpenedTab(facebookUrl);

        /* Step 5: Validate LinkedIn link opens correct page */
        clientHomePage.clickLinkedInIcon();
        clientHomePage.validateNewlyOpenedTab(linkedInUrl);

        /* Step 6: Logout */
        clientHomePage.logout();
    }

    /**
     * Author - Kalyan Pawar
     * TC-05 - To validate App reset functionality on Items page.
     */
    @Test(description = "TC-05", groups = {"PriceTests", "Regression"})
    @TestCaseId("TC-05") // Test case ID in Report Portal
    @Description("To validate App reset functionality on Items page.")
    public static void toValidateAppResetFunctionalityOnItemsPage() {
        String item1 = "Sauce Labs Onesie";
        String price1 = "$7.99";

        /* Step 1: Login */
        ClientHomePage clientHomePage = new LoginPage().login();

        /* Step 2: Validate price of item */
        clientHomePage.validatePriceForItem(item1, price1);

        /* Step 3: Add item to cart and validate count */
        clientHomePage.addItemToCartOrRemove(item1);
        clientHomePage.validateItemsInCart(1);

        /* Step 4: Logout */
        clientHomePage.logout();

        /* Step 5: Login again and validate cart retains item */
        clientHomePage = new LoginPage().login();
        clientHomePage.validateItemsInCart(1);

        /* Step 6: Refresh page and validate cart still has item */
        clientHomePage.refreshPage();
        clientHomePage.validateItemsInCart(1);

        /* Step 7: Reset app state and validate cart is empty */
        clientHomePage.openLeftMenuPanel();
        clientHomePage.clickResetAppState();
        clientHomePage.closeLeftMenuPanel();
        clientHomePage.validateCartIsEmpty();

        /* Step 8: Logout and validate login page */
        clientHomePage.logout();
        LoginPage.validateLoginPageIsOpened();
    }

    /**
     * Author - Kalyan Pawar
     * TC-06 - To validate App reset functionality on Carts page.
     */
    @Test(description = "TC-06", groups = {"Regression"})
    @TestCaseId("TC-06") // Test case ID in Report Portal
    @Description("To validate App reset functionality on Carts page.")
    public static void toValidateAppResetFunctionalityOnCartsPage() {
        String item2 = "Sauce Labs Bike Light";
        String price2 = "$9.99";
        String description2 = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";

        /* Step 1: Login */
        ClientHomePage clientHomePage = new LoginPage().login();

        /* Step 2: Validate price of item and add to cart */
        clientHomePage.validatePriceForItem(item2, price2);
        clientHomePage.addItemToCartOrRemove(item2);
        clientHomePage.validateItemsInCart(1);

        /* Step 3: Open cart and validate item details */
        YourCartPage yourCartPage = clientHomePage.clickOpenCart();
        yourCartPage.validateDescriptionForItems(item2, description2);
        yourCartPage.validatePriceForItems(item2, price2);

        /* Step 4: Logout */
        clientHomePage.logout();
        LoginPage.validateLoginPageIsOpened();

        /* Step 5: Login again and validate item persists */
        clientHomePage = new LoginPage().login();
        clientHomePage.clickOpenCart();
        yourCartPage.validateDescriptionForItems(item2, description2);
        yourCartPage.validatePriceForItems(item2, price2);

        /* Step 6: Reset app state and validate cart is empty */
        clientHomePage.openLeftMenuPanel();
        clientHomePage.clickResetAppState();
        clientHomePage.refreshPage();
        yourCartPage.validateNoItemsInCart();

        /* Step 7: Logout and force failure (expected for testing) */
        clientHomePage.logout();
        Assert.assertTrue(false);
    }

    /**
     * Author - Kalyan Pawar
     * TC-07 - To validate item details on Cart page with checkout.
     */
    @Test(description = "TC-07", groups = {"PriceTests", "Regression"})
    @TestCaseId("TC-07") // Test case ID in Report Portal
    @Description("To validate Item Details On Cart Page.")
    public static void toValidateItemDetailsOnCartPage2() {
        String item1 = "Sauce Labs Backpack";
        String price1 = "$29.99";
        String item2 = "Sauce Labs Bolt T-Shirt";
        String price2 = "$15.99";
        String description1 = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        String description2 = "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";
        String cancelButton = ClientTestValues.CANCEL;
        String cancelButtonColor = Colors.WHITE;
        String continueButton = ClientTestValues.CONTINUE;
        String continueButtonColor = Colors.MINT_GREEN;

        /* Step 1: Login */
        ClientHomePage clientHomePage = new LoginPage().login();
        clientHomePage.validateHomePageIsOpened();

        /* Step 2: Validate prices for items */
        clientHomePage.validatePriceForItem(item1, price1);
        clientHomePage.validatePriceForItem(item2, price2);

        /* Step 3: Add items to cart and validate count */
        clientHomePage.addItemToCartOrRemove(item1);
        clientHomePage.validateItemsInCart(1);
        clientHomePage.addItemToCartOrRemove(item2);
        clientHomePage.validateItemsInCart(2);

        /* Step 4: Open cart and validate item details */
        YourCartPage yourCartPage = clientHomePage.clickOpenCart();
        yourCartPage.validateCartPageIsOpened();
        yourCartPage.validateDescriptionForItems(item1, description1);
        yourCartPage.validatePriceForItems(item1, price1);
        yourCartPage.validateDescriptionForItems(item2, description2);
        yourCartPage.validatePriceForItems(item1, price1);

        /* Step 5: Proceed to checkout and validate page */
        CheckOutPage checkOutPage = yourCartPage.clickCheckOutButton();
        checkOutPage.validateCheckOutPageIsOpened();
        checkOutPage.validateTextInputFieldsAreDisplayed();

        /* Step 6: Validate button background colors */
        checkOutPage.validateBackGroundColorOfButton(cancelButton, cancelButtonColor);
        checkOutPage.validateBackGroundColorOfButton(continueButton, continueButtonColor);

        /* Step 7: Cancel checkout and return to cart page */
        yourCartPage = checkOutPage.clickCancelButton();
        yourCartPage.validateCartPageIsOpened();

        /* Step 8: Logout and validate login page */
        clientHomePage.logout();
        LoginPage.validateLoginPageIsOpened();
    }
}

