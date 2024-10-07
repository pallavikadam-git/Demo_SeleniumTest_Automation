package client.pages;

import org.testng.Assert;
import client.common.BaseClass;
import client.common.LoginCreds;

public class LoginPage extends BaseClass {

    private static final String EMAIL_XPATH = "//*[@id='email']";
    private static final String PASSWORD_XPATH = "//*[@id='password']";
    private static final String SUBMIT_BUTTON_XPATH = "//*[@type='submit']";

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String SUBMIT_BUTTON = "submit button";

    /**
     * Logs in to the application using the provided credentials.
     * @return A new instance of ClientHomePage after a successful login.
     */
    public ClientHomePage login() {
        sendKeys(EMAIL_XPATH, LoginCreds.USER_NAME, USERNAME);
        sendKeys(PASSWORD_XPATH, LoginCreds.PASSWORD, PASSWORD);
        click(SUBMIT_BUTTON_XPATH, SUBMIT_BUTTON);
        return new ClientHomePage();
    }

    /**
     * Validates that the Login page is opened by checking the current URL.
     */
    public static void validateLoginPageIsOpened() {
        Assert.assertEquals(driver.getCurrentUrl(), LoginCreds.LOGIN_URL);
    }
}
