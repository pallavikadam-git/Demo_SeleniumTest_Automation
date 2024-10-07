package client.pages;

import org.testng.Assert;
import client.common.BaseClass;
import client.common.ClientTestValues;

public class ClientHomePage extends BaseClass {
    // XPaths
    private static final String COMMON_HEADER_XPATH = "//*[@role='button']//child::span[text()='%s']";
    

    // Field Names
    private static final String DASHBOARD_URL = "https://qa.veraxerp.com/dashboard";
    private static final String LOGOUT = "Log Out";
    

//    /**
//     * Logs out from the application by clicking on the logout button.
//     */
//    public void logout() {
//        openProfilePanel();
//        click(String.format(COMMON_XPATH, LOGOUT), LOGOUT);
//    }
//
//    /**
//     * Clicks on the CRM header.
//     */
//    public void clickOnCRM() {
//        click(String.format(COMMON_HEADER_XPATH, CRM), CRM_HEADER);
//    }
//
//    /**
//     * Clicks on the Opportunities link.
//     */
//    public OpportunitiesPage clickOpportunities() {
//        click(String.format(COMMON_XPATH, OPPORTUNITIES), OPPORTUNITIES);
//        return new OpportunitiesPage();
//    }
//
//    /**
//     * Performs a control-click on the Opportunities link and opens it in a new tab.
//     * @return an instance of the OpportunitiesPage.
//     */
//    public OpportunitiesPage controlClickAndOpenOpportunities() {
//        controlClickAndOpen(String.format(COMMON_XPATH, OPPORTUNITIES), OPPORTUNITIES);
//        return new OpportunitiesPage();
//    }
//
//    /**
//     * Validates that the dashboard page is opened by checking the URL and the presence of a panel heading.
//     */
//    public void validateDashboardPageIsOpened() {
//        waitUntilElementIsDisplayed(String.format(COMMON_XPATH, ClientTestValues.PANEL_HEADING), DASHBOARD_PANEL_HEADING);
//        Assert.assertEquals(driver.getCurrentUrl(), DASHBOARD_URL);
//    }
//
//    /**
//     * Opens the profile panel by clicking on the corresponding navbar item.
//     */
//    public void openProfilePanel() {
//        click(String.format(NAV_BAR_XPATH, 3), PROFILE_PANEL);
//    }
//
//    /**
//     * Clicks on the TPI Shop Setup link.
//     * @return an instance of the TPIShopSetupPage.
//     **/
//    public TPIShopSetupPage clickTPIShopSetUp() {
//        click(String.format(COMMON_XPATH, TPI_SHOP_SETUP), TPI_SHOP_SETUP);
//        return new TPIShopSetupPage();
//    }
//
//    /**
//     * Validates the toaster message displayed after an action.
//     * @param message the expected message to validate against.
//     */
//    public void validateToasterMessage(String message) {
//        waitUntilElementIsLoaded(TOASTER_XPATH, message, TOASTER_MESSAGE);
//        Assert.assertEquals(getText(TOASTER_XPATH, TOASTER_MESSAGE), message);
////        waitUntilElementIsDisappeared(TOASTER_XPATH, TOASTER_MESSAGE);
//    }
//
//    /**
//     * Switches to the other open tab in the browser.
//     */
//    public void switchToOtherTabs() {
//        switchToOtherTab();
//    }
//
//    /**
//     * Refreshes the current page.
//     */
//    public void refreshPage() {
//        refresh();
//    }
//
//    /**
//     * Clicks on the Inventory header.
//     */
//    public void clickInventory() {
//        click(String.format(COMMON_HEADER_XPATH, INVENTORY), INVENTORY_HEADER);
//    }
//
//    /**
//     * Clicks on the Routers link.
//     * return RoutersPage instance.
//     */
//    public RoutersPage clickRouters() {
//        click(String.format(COMMON_XPATH, ROUTERS), ROUTERS);
//        return new RoutersPage();
//    }
//
//    /**
//     * Clicks on the CRM header.
//     */
//    public void clickOnWorkFlowHeader() {
//        click(String.format(COMMON_HEADER_XPATH, WORKFLOW), WORKFLOW_HEADER);
//    }
//
//    /**
//     * Clicks on the Release Work link.
//     */
//    public ReleaseWorkPage controlClickReleaseWork() {
//        controlClickAndOpen(String.format(COMMON_XPATH, RELEASE_WORK), RELEASE_WORK);
//        return new ReleaseWorkPage();
//    }
//
//    /**
//     * Clicks on the Work Order.
//     */
//    public void clickWorkOrder() {
//        click(String.format(COMMON_XPATH, WORK_ORDERS), WORK_ORDERS);
//    }
//
//    /**
//     * Clicks on the Manage Work Order link.
//     * return ManageWorkOrderPage instance.
//     */
//    public ManageWorkOrderPage clickManageWorkOrder() {
//        click(String.format(COMMON_XPATH, MANAGE_WORK_ORDERS), MANAGE_WORK_ORDERS);
//        return new ManageWorkOrderPage();
//    }
}
