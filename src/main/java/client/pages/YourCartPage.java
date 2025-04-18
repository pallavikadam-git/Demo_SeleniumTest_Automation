package client.pages;

import client.common.BaseClass;
import client.common.ClientUrl;
import com.epam.reportportal.annotations.Step;
import org.testng.Assert;

public class  YourCartPage extends BaseClass {
    // XPaths
    private static final String ITEM_DESCRIPTION_XPATH = "//*[text()='%s']/ancestor::div[@class='cart_item_label']/child::div[@class='inventory_item_desc']";
    private static final String ITEM_PRICE_XPATH = "//*[text()='%s']/ancestor::div[@class='cart_item_label']/descendant::div[@class='inventory_item_price']";
    private static final String REMOVE_ITEM_XPATH = "//*[text()='%s']/ancestor::div[@class='cart_item_label']/descendant::button";
    private static final String CONTINUE_SHOPPING_BUTTON_XPATH = "//*[@id='continue-shopping']";
    private static final String CART_ITEMS_XPATH = "//*[@class='cart_item']";
    private static final String CHECKOUT_BUTTON_XPATH = "//button[@id='checkout']";

    // Field Names
    private static final String ITEM_DESCRIPTION = "Item Description";
    private static final String ITEM_PRICE = "Item Price";
    private static final String REMOVE_ITEM = "Remove Item";
    private static final String CONTINUE_SHOPPING = "Continue Shopping Button";
    private static final String CART_ITEMS = "Cart Items";
    private static final String CHECKOUT_BUTTON = "Checkout Button";

    /**
     * Validates Cart page is opened.
     */
    @Step
    public void validateCartPageIsOpened() {
        Assert.assertEquals(getURL(), ClientUrl.CART_PAGE_URL, "Cart Page is Not Opened");
    }

    /**
     * Validates description for item.
     * @param itemName - the item for which the description is being validated.
     * @param description - description of item.
     */
    @Step
    public void validateDescriptionForItems(String itemName, String description) {
        Assert.assertEquals(getText(String.format(ITEM_DESCRIPTION_XPATH, itemName), ITEM_DESCRIPTION), description);
    }

    /**
     * Validate price for item.
     * @param itemName - the item for which the price is being validated.
     * @param price - price of item.
     */
    @Step
    public void validatePriceForItems(String itemName, String price) {
        Assert.assertEquals(getText(String.format(ITEM_PRICE_XPATH, itemName), ITEM_PRICE), price);
    }

    /**
     * Removes Item from Cart.
     * @param itemName - the item to be removed from cart.
     */
    @Step
    public void removeItemFromCart(String itemName) {
        click(String.format(REMOVE_ITEM_XPATH, itemName), REMOVE_ITEM);
    }

    /**
     * Clicks continue shopping button.
     */
    @Step
    public ClientHomePage clickContinueShoppingButton() {
        click(CONTINUE_SHOPPING_BUTTON_XPATH, CONTINUE_SHOPPING);
        return new ClientHomePage();
    }

    /**
     * Validates Cart is empty.
     */
    @Step
    public void validateNoItemsInCart() {
        boolean isAbsent = waitUntilElementIsDisappeared(CART_ITEMS_XPATH, CART_ITEMS);
        Assert.assertTrue(isAbsent);
    }

    /**
     * Clicks checkout button.
     */
    @Step
    public CheckOutPage clickCheckOutButton() {
        click(CHECKOUT_BUTTON_XPATH, CHECKOUT_BUTTON);
        return new CheckOutPage();
    }
}
