package client.pages;

import client.common.BaseClass;
import client.common.ClientTestValues;
import client.common.ClientUrl;
import com.epam.reportportal.annotations.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CheckOutPage extends BaseClass {
    // XPaths
    private static final String FIRST_NAME_XPATH = "//input[@id='first-name']";
    private static final String LAST_NAME_XPATH = "//input[@id='last-name']";
    private static final String POSTAL_CODE_XPATH = "//input[@id='postal-code']";
    private static final String BUTTON_XPATH = "//*[@id='%s']";

    // Field Names
    private static final String FIRST_NAME = "First Name";
    private static final String LAST_NAME = "Last Name";
    private static final String POSTAL_CODE = "Postal Code";
    private static final String CANCEL_BUTTON = "Cancel Button";
    private static final String CONTINUE_BUTTON = "Continue Button";


    /**
     * Validates Checkout page is opened.
     */
    @Step
    public void validateCheckOutPageIsOpened() {
        Assert.assertEquals(getURL(), ClientUrl.CHECKOUT_PAGE_URL, "CheckOut Page is Not Opened");
    }

    /**
     * Validates First Name, Last Name and Postal Code fields are displayed.
     */
    @Step
    public void validateTextInputFieldsAreDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isElementDisplayed(FIRST_NAME_XPATH, FIRST_NAME), FIRST_NAME + " is not displayed.");
        softAssert.assertTrue(isElementDisplayed(LAST_NAME_XPATH, LAST_NAME), LAST_NAME + " is not displayed.");
        softAssert.assertTrue(isElementDisplayed(POSTAL_CODE_XPATH, POSTAL_CODE), POSTAL_CODE + " is not displayed.");
        softAssert.assertAll();
    }

    /**
     * Validates Cancel or Continue buttons Background color.
     * @param buttonName - Cancel or Continue
     * @param color - Background color of button
     */
    @Step
    public void validateBackGroundColorOfButton(String buttonName, String color) {
        Assert.assertEquals(getBackgroundColor(String.format(BUTTON_XPATH, buttonName.toLowerCase()), buttonName + " Button"), color,
                "The button color is not matching.");
    }

    /**
     * Clicks Cancel button.
     */
    @Step
    public YourCartPage clickCancelButton() {
        click(String.format(BUTTON_XPATH, ClientTestValues.CANCEL.toLowerCase()), CANCEL_BUTTON);
        return new YourCartPage();
    }
}
