package client.pages;

import client.common.BaseClass;
import org.testng.Assert;

public class  YourCartPage extends BaseClass {
    // XPaths
    private static final String ITEM_DESCRIPTION_XPATH = "//*[text()='%s']/ancestor::div[@class='cart_item_label']/child::div[@class='inventory_item_desc']";
    private static final String ITEM_PRICE_XPATH = "//*[text()='%s']/ancestor::div[@class='cart_item_label']/descendant::div[@class='inventory_item_price']";
    private static final String REMOVE_ITEM_XPATH = "//*[text()='%s']/ancestor::div[@class='cart_item_label']/descendant::button";
    private static final String CONTINUE_SHOPPING_BUTTON_XPATH = "//*[@id='continue-shopping']";

    // Field Names
    private static final String ITEM_DESCRIPTION = "Item Description";
    private static final String ITEM_PRICE = "Item Price";
    private static final String REMOVE_ITEM = "Remove Item";
    private static final String CONTINUE_SHOPPING = "Continue Shopping Button";

    /**
     * Validates description for item.
     * @param itemName - the item for which the description is being validated.
     * @param description
     */
    public void validateDescriptionForItems(String itemName, String description) {
        Assert.assertEquals(getText(String.format(ITEM_DESCRIPTION_XPATH, itemName), ITEM_DESCRIPTION), description);
    }

    /**
     * Validate price for item.
     * @param itemName - the item for which the price is being validated.
     * @param price
     */
    public void validatePriceForItems(String itemName, String price) {
        Assert.assertEquals(getText(String.format(ITEM_PRICE_XPATH, itemName), ITEM_PRICE), price);
    }

    /**
     * Removes Item from Cart.
     * @param itemName - the item to be removed from cart.
     */
    public void removeItemFromCart(String itemName) {
        click(String.format(REMOVE_ITEM_XPATH, itemName), REMOVE_ITEM);
    }

    /**
     * Clicks continue shopping button.
     */
    public ClientHomePage clickContinueShoppingButton() {
        click(CONTINUE_SHOPPING_BUTTON_XPATH, CONTINUE_SHOPPING);
        return new ClientHomePage();
    }
}
