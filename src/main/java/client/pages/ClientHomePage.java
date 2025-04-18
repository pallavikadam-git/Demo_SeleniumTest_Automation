package client.pages;

import client.common.ClientUrl;
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
    private static final String TWITTER_XPATH = "//*[text()='Twitter']";
    private static final String FACEBOOK_XPATH = "//*[text()='Facebook']";
    private static final String LINKEDIN_XPATH = "//*[text()='LinkedIn']";
    private static final String RESET_APP_XPATH = "//*[@id='reset_sidebar_link']";
    private static final String LEFT_PANEL_CLOSE_ICON_XPATH = "//*[@id='react-burger-cross-btn']";


    // Field Names
    private static final String FILTER_DROPDOWN_FIELD = "Filter dropdown field";
    private static final String INVENTORY_ITEM_ORDER = "Inventory Items Order";
    private static final String LEFT_PANEL_BURGER_BUTTON = "Left panel burger button";
    private static final String LOGOUT_BUTTON = "Logout Button";
    private static final String ADD_TO_CART = "Add to Cart Button";
    private static final String CART_ITEM_COUNT = "Cart item count";
    private static final String CART = "Cart";
    private static final String TWITTER = "Twitter";
    private static final String FACEBOOK = "Facebook";
    private static final String LINKEDIN = "LinkedIn";
    private static final String RESET_APP_STATE = "Reset App State";
    private static final String LEFT_MENU_CLOSE_ICON = "Reset App State";

    /**
     * Validates Home page is opened.
     */
    @Step
    public void validateHomePageIsOpened() {
        Assert.assertEquals(getURL(), ClientUrl.HOME_PAGE_URL, "Home Page is Not Opened");
    }

    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        refresh();
    }

    /**
     * validates selected option from the filter dropdown.
     * @param option - option name.
     */
    public void validateSelectedOptionForFilterDropdown(String option) {
        Assert.assertEquals(getSelectedOption(FILTER_DROPDOWN_FIELD, FILTER_DROPDOWN_XPATH), option);
    }

    /**
     * validates inventory items order on the page.
     * @param items - list of items.
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
    public void openLeftMenuPanel(){
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
        openLeftMenuPanel();
        clickLogout();
    }

    /**
     * validates inventory items price order on the page.
     * @param prices - list of prices.
     */
    public void validateInventoryItemsPriceOrder(List<String> prices) {
        List<String> actualOrder = getTexts(INVENTORY_ITEM_PRICE_XPATH, INVENTORY_ITEM_ORDER);
        Assert.assertEquals(actualOrder, prices);
    }

    /**
     * validates inventory item price.
     * @param itemName - item name for which the price is being validated.
     * @param price - price of item.
     **/
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
//        Assert.assertTrue(false);
    }

    /**
     * Opens Cart page
     */
    @Step
    public YourCartPage clickOpenCart() {
        click(CART_XPATH, CART);
        return new YourCartPage();
    }

    /**
     * Clicks Twitter hyperlink.
     */
    @Step
    public void clickTwitterIcon() {
        click(TWITTER_XPATH, TWITTER);
    }

    /**
     * Switch and
     */
    @Step
    public void validateNewlyOpenedTab(String url) {
        String mainWindow = getCurrentWindow();
        switchToOtherTab();
        waitUntilUrlMatches(url);
        Assert.assertTrue(driver.getCurrentUrl().contains(url));
        closeTab();
        switchToWindow(mainWindow);
    }

    /**
     * Clicks Facebook hyperlink.
     */
    @Step
    public void clickFacebookIcon() {
        click(FACEBOOK_XPATH, FACEBOOK);
    }

    /**
     * Clicks LinkedIn hyperlink.
     */
    @Step
    public void clickLinkedInIcon() {
        click(LINKEDIN_XPATH, LINKEDIN);
    }

    /**
     * Clicks on Reset App State link.
     */
    @Step
    public void clickResetAppState() {
        waitUntilElementIsDisplayed(RESET_APP_XPATH, RESET_APP_STATE);
        click(RESET_APP_XPATH, RESET_APP_STATE);
    }

    /**
     * Closes left menu panel.
     */
    @Step
    public void closeLeftMenuPanel() {
        click(LEFT_PANEL_CLOSE_ICON_XPATH, LEFT_MENU_CLOSE_ICON);
    }

    /**
     * Validates no item count is displayed on cart.
     */
    @Step
    public void validateCartIsEmpty() {
        boolean isAbsent = waitUntilElementIsDisappeared(ITEMS_COUNT_IN_CART_XPATH, CART_ITEM_COUNT);
        Assert.assertTrue(isAbsent);
    }
}
