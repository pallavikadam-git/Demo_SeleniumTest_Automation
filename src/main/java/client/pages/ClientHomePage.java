package client.pages;

import com.epam.reportportal.annotations.Step;
import org.testng.Assert;
import client.common.BaseClass;

import java.util.List;

public class ClientHomePage extends BaseClass {
    // XPaths
    private static final String FILTER_DROPDOWN_XPATH = "//select[@class='product_sort_container']";
    private static final String INVENTORY_ITEM_TEXT_XPATH = "//*[@data-test = 'inventory-item-name']";
    private static final String BURGER_BUTTON_XPATH = "//*[@class='bm-burger-button']";
    private static final String LOGOUT_XPATH = "//*[@id='logout_sidebar_link']";
    private static final String INVENTORY_ITEM_PRICE_XPATH = "//*[@class='inventory_item_price']";
    private static final String INVENTORY_PRICE_XPATH = "//*[text()='%s']/ancestor::div[@class='inventory_item_label']/following-sibling::div/child::div";
    private static final String ADD_TO_CART_REMOVE_XPATH = "//*[text()='%s']/ancestor::div[@class='inventory_item_label']/following-sibling::div/child::button";
    private static final String ITEMS_COUNT_IN_CART_XPATH = "//*[@class='shopping_cart_badge']";
    private static final String CART_XPATH = "//*[@class='shopping_cart_link']";

    // Field Names
    private static final String FILTER_DROPDOWN_FIELD = "Filter dropdown field";
    private static final String INVENTORY_ITEM_ORDER = "Inventory Items Order";
    private static final String LEFT_PANEL_BURGER_BUTTON = "Left panel burger button";
    private static final String LOGOUT_BUTTON = "Logout Button";
    private static final String ADD_TO_CART = "Add to Cart Button";
    private static final String CART_ITEM_COUNT = "Cart item count";
    private static final String CART = "Cart";

    /**
     * Switches to the other open tab in the browser.
     */
    public void switchToOtherTabs() {
        switchToOtherTab();
    }

    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        refresh();
    }

    /**
     * validates selected option from the filter dropdown.
     * @param option
     */
    public void validateSelectedOptionForFilterDropdown(String option) {
        Assert.assertEquals(getSelectedOption(FILTER_DROPDOWN_FIELD, FILTER_DROPDOWN_XPATH), option);
    }

    /**
     * validates inventory items order on the page.
     * @param items
     */
    public void validateInventoryItemsOrder(List<String> items) {
        List<String> actualOrder = getTexts(INVENTORY_ITEM_TEXT_XPATH, INVENTORY_ITEM_ORDER);
        Assert.assertEquals(actualOrder, items);
    }

    /**
     * Selects option for Filter dropdown field.
     */
    public void selectOptionForFilterDropdown(String option) {
        selectByText(FILTER_DROPDOWN_XPATH, option, FILTER_DROPDOWN_FIELD);
    }

    /**
     * Opens left menu on the page.
     */
    @Step
    public void openLeftMenu(){
        click(BURGER_BUTTON_XPATH, LEFT_PANEL_BURGER_BUTTON);
    }

    /**
     * Clicks logout link.
     */
    @Step("clicking on Logout Button")
    public void clickLogout(){
        waitUntilElementIsDisplayed(LOGOUT_XPATH, LOGOUT_BUTTON);
        click(LOGOUT_XPATH, LOGOUT_BUTTON);
    }

    /**
     * logouts from the application.
     */
    @Step
    public void logout(){
        openLeftMenu();
        clickLogout();
    }

    /**
     * validates inventory items price order on the page.
     * @param prices
     */
    public void validateInventoryItemsPriceOrder(List<String> prices) {
        List<String> actualOrder = getTexts(INVENTORY_ITEM_PRICE_XPATH, INVENTORY_ITEM_ORDER);
        Assert.assertEquals(actualOrder, prices);
    }

    /**
     * validates inventory item price.
     * @param itemName - item name for which the price is being validated.
     * @param price
     */
    @Step
    public void validatePriceForItem(String itemName, String price) {
        String actualprice = getText(String.format(INVENTORY_PRICE_XPATH, itemName), itemName);
        Assert.assertEquals(actualprice, price);
    }

    /**
     * Adds Item to Cart
     * @param itemName - item name for which the price is being validated.
     */
    @Step
    public void addItemToCartOrRemove(String itemName) {
        click(String.format(ADD_TO_CART_REMOVE_XPATH, itemName), ADD_TO_CART);
    }

    /**
     * Validates the number of items in cart.
     * @param itemsCount - item name for which the price is being validated.
     */
    @Step
    public void validateItemsInCart(Integer itemsCount) {
        Assert.assertEquals(getText(ITEMS_COUNT_IN_CART_XPATH, CART_ITEM_COUNT), String.valueOf(itemsCount));
        Assert.assertTrue(false);
    }

    /**
     * Opens Cart page
     */
    @Step
    public YourCartPage clickOpenCart() {
        click(CART_XPATH, CART);
        return new YourCartPage();
    }
}
