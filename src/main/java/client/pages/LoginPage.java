package client.pages;

import org.testng.Assert;
import client.common.BaseClass;
import client.common.LoginCreds;

public class LoginPage extends BaseClass {

    private static final String USER_NAME_XPATH = "//*[@id='user-name']";
    private static final String PASSWORD_XPATH = "//*[@id='password']";
    private static final String LOGIN_BUTTON_XPATH = "//*[@id='login-button']";

    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String LOGIN_BUTTON = "Login button";

    /**
     * Logs in to the application using the provided credentials.
     * @return A new instance of ClientHomePage after a successful login.
     */
    public ClientHomePage login() {
        sendKeys(USER_NAME_XPATH, LoginCreds.USER_NAME, USERNAME);
        sendKeys(PASSWORD_XPATH, LoginCreds.PASSWORD, PASSWORD);
        click(LOGIN_BUTTON_XPATH, LOGIN_BUTTON);
        return new ClientHomePage();
    }

    /**
     * Validates that the Login page is opened by checking the current URL.
     */
    public static void validateLoginPageIsOpened() {
        Assert.assertEquals(driver.getCurrentUrl(), LoginCreds.LOGIN_URL);
    }
}
