package client.common;

import com.aventstack.extentreports.Status;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Log4j2
public class BaseClass extends TestListener {
    public static final int minWaitTime = 2;
    public static final int midWaitTime = 10;
    public static final int maxWaitTime = 20;
    public static final String PASS = "pass";
    public static final String INFO = "info";
    public static final String FAIL = "fail";

    public static void log(String status, String message){
        switch(status.toLowerCase()) {
            case PASS:
                test.log(Status.PASS, message);
                break;
            case INFO:
                test.log(Status.INFO, message);
                break;
            case FAIL:
                test.log(Status.FAIL, message);
                break;
        }
        log.info(message);
    }

    /**
     * Retrieves a WebElement using the provided xpath.
     * @param xpath the xpath locator for the element
     * @return the WebElement found
     **/
    public static WebElement getWebElement(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try{
            element.isDisplayed();
        }
        catch (Exception e){
            element = driver.findElement(By.xpath(xpath));
        }
        return element;
    }

    /**
     * Retrieves a list of WebElements using the provided xpath.
     * @param xpath the xpath locator for the elements
     * @return a list of WebElements found
     **/
    public static List<WebElement> getWebElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    /**
     * Clicks on a WebElement located by the given xpath.
     * @param xpath the xpath locator for the element
     * @param fieldName the descriptive name of the field for logging
     **/
    public static void click(String xpath, String fieldName) {
        WebElement element = getWebElement(xpath);
        try{
            element.click();
            log(PASS, "Successfully clicked on " + fieldName);
        }
        catch(Exception e){
            element = getWebElement(xpath);
            element.click();
            log(PASS, "Successfully clicked on " + fieldName);
        }
    }

    /**
     * Sends the provided value to a WebElement located by the given xpath.
     * @param xpath the xpath locator for the element
     * @param value the value to send
     * @param fieldName the descriptive name of the field for logging
     **/
    public static void sendKeys(String xpath, String value, String fieldName) {
        getWebElement(xpath).sendKeys(value);
        log(PASS, "Successfully sent value '" + value + "' to " + fieldName);
    }

    /**
     * Selects an option by its visible text from a dropdown WebElement located by the given xpath.
     * @param xpath the xpath locator for the dropdown
     * @param text the visible text to select
     * @param fieldName the descriptive name of the field for logging
     **/
    public static void selectByText(String xpath, String text, String fieldName) {
        waitUntilElementIsDisplayed(xpath, fieldName);
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(midWaitTime))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        fluentWait.until(driver -> {
            Select select = new Select(getWebElement(xpath));
            return select.getOptions()
                    .stream()
                    .anyMatch(option -> option.getText().equals(text.trim()));
        });

        Select select = new Select(getWebElement(xpath));
        select.selectByVisibleText(text);
        log(PASS, "Successfully selected '" + text + "' for " + fieldName);
    }

    /**
     * Performs a control-click on a WebElement located by the given xpath and opens it in a new tab.
     * @param xpath the xpath locator for the element
     * @param fieldName the descriptive name of the field for logging
     **/
    public static void controlClickAndOpen(String xpath, String fieldName) {
        waitUntilElementIsClickable(xpath, fieldName);
        Actions action = new Actions(driver);
        justWait(minWaitTime);
        action.keyDown(Keys.CONTROL).click(getWebElement(xpath)).keyUp(Keys.CONTROL).build().perform();
        switchToOtherTab();
        log(PASS, "Successfully clicked and opened in new tab for " + fieldName);
    }

    /**
     * Checks if a WebElement located by the given xpath is displayed.
     * @param xpath the xpath locator for the element
     * @param fieldName the descriptive name of the field for logging
     * @return true if the element is displayed, false otherwise
     **/
    public static boolean isElementDisplayed(String xpath, String fieldName) {
        try {
            boolean isDisplayed = getWebElement(xpath).isDisplayed();
            log(PASS,"Element " + fieldName + " is Displayed.");
            return isDisplayed;
        } catch (Exception e) {
            log(INFO, "Element " + fieldName + " is Not Displayed.");
            return false;
        }
    }

    /**
     * Waits until a WebElement located by the given xpath is displayed.
     * @param xpath the xpath locator for the element
     * @param fieldName the descriptive name of the field for logging
     **/
    public static void waitUntilElementIsDisplayed(String xpath, String fieldName) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(maxWaitTime))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        log(INFO, "Waiting for element " +fieldName+ " to be displayed.");

        fluentWait.until(driver -> {
            WebElement element = getWebElement(xpath);
            if (element.isDisplayed()) {
                log(PASS, "Element " +fieldName+ " is displayed.");
                return true;
            } else {
                return false;
            }
        });
    }

    /**
     * Waits until a WebElement located by the given xpath is clickable.
     * @param xpath the xpath locator for the element
     * @param fieldName the descriptive name of the field for logging
     **/
    public static void waitUntilElementIsClickable(String xpath, String fieldName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
        log(INFO, "Waiting for element " +fieldName+ " to be clickable.");
        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(xpath)));
    }

    /**
     * Retrieves the text content of a WebElement located by the given xpath.
     * @param xpath the xpath locator for the element
     * @param fieldName the descriptive name of the field for logging
     * @return the text content of the WebElement
     **/
    public static String getText(String xpath, String fieldName) {
        String text = getWebElement(xpath).getText();
        log(PASS, "Read text from element " +fieldName+ ".");
        return text;
    }

    /**
     * Switches to the other browser tab.
     **/
    public static void switchToOtherTab() {
        String currentTab = driver.getWindowHandle();
        Set<String> set = driver.getWindowHandles();
        for (String handle : set) {
            if (!handle.equals(currentTab)) {
                driver.switchTo().window(handle);
                log(PASS, "Successfully switched to other tab.");
            }
        }
    }

    /**
     * Refreshes the current page.
     **/
    public static void refresh() {
        driver.navigate().refresh();
        log(INFO, "Refreshed the page.");
    }

    /**
     * Pauses the execution for a specified number of seconds.
     * @param seconds the number of seconds to wait
     **/
    public static void justWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Performs a JavaScript click on a WebElement located by the given xpath.
     * @param xpath the xpath locator for the element
     * @param fieldName the descriptive name of the field for logging
     **/
    public static void javascriptClick(String xpath, String fieldName) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(xpath));
        log(PASS, "Clicked on " + fieldName);
    }

    /**
     * Retrieves the text content of multiple WebElements located by the given xpath.
     * @param xpath the xpath locator for the elements
     * @param fieldName the descriptive name of the field for logging
     * @return a list of text content from each WebElement
     **/
    public List<String> getTexts(String xpath, String fieldName) {
        List<String> texts = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        List<WebElement> elements = getWebElements(xpath);

        for (WebElement element : elements) {
            try {
                texts.add(wait.until(driver -> element.isDisplayed() ? element.getText() : null));
            } catch (StaleElementReferenceException e) {
                WebElement freshElement = getWebElement(xpath);
                texts.add(freshElement.getText());
            }
        }
        log(PASS, "Read texts from elements " + fieldName + ".");
        return texts;
    }

    /**
     * Waits until the page is fully loaded.
     **/
    public static void waitForPageLoad() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        wait.until(driver -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String readyState = js.executeScript("return document.readyState").toString();
            return readyState.equals("complete");
        });
    }

    /**
     * Waits until a WebElement located by the given xpath has a specific text value.
     * @param xpath the xpath locator for the element
     * @param expectedText the expected status of the element
     * @param fieldName the descriptive name of the field for logging
     **/
    public void waitUntilElementIsLoaded(String xpath, String expectedText, String fieldName) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(maxWaitTime))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(Exception.class);

        wait.until(driver -> getText(xpath, fieldName).trim().equals(expectedText));
        log(PASS, "Successfully waited until element is in status '" + expectedText + "' for " + fieldName);
    }

    /**
     * Scrolls to a WebElement located by the given xpath.
     * @param xpath the xpath locator for the element
     * @param fieldName the descriptive name of the field for logging
     **/
    public static void scrollToElement(String xpath, String fieldName) {
        Function<WebDriver, Boolean> scrollCheck = webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(xpath));
            return (boolean) js.executeScript("return (window.pageYOffset + window.innerHeight) >= arguments[0].getBoundingClientRect().bottom;", getWebElement(xpath));
        };

        new WebDriverWait(driver, Duration.ofSeconds(midWaitTime))
                .pollingEvery(Duration.ofMillis(200))
                .until(scrollCheck);
        log(PASS, "Successfully scrolled to element " + fieldName + ".");
    }

    /**
     * Scrolls to the bottom of the page.
     **/
    public static void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long previousHeight = 0;

        while (true) {
            // Scroll down to the bottom
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            justWait(1);

            // Get the new height of the document
            long newHeight = (Long) js.executeScript("return document.body.scrollHeight;");

            // Check if we have reached the bottom
            if (newHeight == previousHeight) {
                break; // Exit the loop if no new height is found
            }
            previousHeight = newHeight; // Update previous height for the next iteration
        }
    }


    /**
     * Scrolls to the top of the page.
     **/
    public static void scrollToTop() {
        Function<WebDriver, Boolean> scrollCheck = webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
            return (boolean) js.executeScript("return window.pageYOffset == 0;");
        };

        new WebDriverWait(driver, Duration.ofSeconds(midWaitTime))
                .pollingEvery(Duration.ofMillis(200))
                .until(scrollCheck);
    }

    /**
     * Retrieves the text value of a text box WebElement located by the given xpath.
     * @param xpath the xpath locator for the text box
     * @return the text value of the text box
     **/
    public static String getTextBoxText(String xpath) {
        return getWebElement(xpath).getDomProperty("value");
    }

    /**
     * Waits until a specific value is loaded in a text box WebElement located by the given xpath.
     * @param xpath the xpath locator for the text box
     * @param expectedValue the expected value to wait for
     * @param fieldName the descriptive name of the field for logging
     * @return the text value of the text box
     **/
    public static String waitUntilValueIsLoaded(String xpath, String expectedValue, String fieldName) {
        Function<WebDriver, Boolean> isValueLoaded = webDriver -> {
            String value = getTextBoxText(xpath);
            return value.equals(expectedValue);
        };

        new WebDriverWait(driver, Duration.ofSeconds(midWaitTime))
                .pollingEvery(Duration.ofMillis(200))
                .until(isValueLoaded);
        log(PASS, "Successfully waited until element is in value '" + expectedValue + "' for " + fieldName);
        return getTextBoxText(xpath);
    }

    /**
     * Checks whether the WebElement identified by the given xpath is selected.
     * This is commonly used for checkboxes, radio buttons, or option elements.
     * Retries the operation if a StaleElementReferenceException occurs.
     * @param xpath the xpath locator for the WebElement to check
     * @return true if the WebElement is selected, false otherwise
     */
    public boolean isSelected(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
        try {
            // Wait until the element is present and check if it's selected
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return element.isSelected();
        } catch (StaleElementReferenceException e) {
            // Retry the operation if the element goes stale
            WebElement freshElement = getWebElement(xpath);
            return freshElement.isSelected();
        }
    }

    /**
     * Waits until the specified element is no longer visible or present in the DOM.
     * @param xpath the xpath locator for the element
     * @param fieldName the descriptive name of the element for logging
     * @return true if the element disappeared, false if it remains visible
     */
    public static boolean waitUntilElementIsDisappeared(String xpath, String fieldName) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))  // Maximum wait time
                .pollingEvery(Duration.ofMillis(500)) // Polling interval
                .ignoring(NoSuchElementException.class); // Ignore if element not found initially

        boolean isDisappeared = wait.until(driver -> {
            List<WebElement> elements = getWebElements(xpath);
            try {
                elements.get(0).isDisplayed();
            }
            catch (Exception e){
                elements = getWebElements(xpath);
            }
            // Return true if the element is not present or not displayed
            return elements.isEmpty() || !elements.get(0).isDisplayed();
        });

        if (isDisappeared) {
            log(PASS, "The element " + fieldName + " has disappeared.");
        } else {
            log(FAIL, "The element " + fieldName + " is still visible.");
        }
        return isDisappeared;
    }

    /**
     * Waits until the current URL matches the given pattern.
     * This method handles application slowness by polling the current URL until it matches the pattern.
     * @param partUrl the regex pattern to match the current URL against
     * @return true if the URL matches the pattern within the wait time, false otherwise
     */
    public boolean waitUntilUrlMatches(String partUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(midWaitTime));
        try {
            // Wait until the current URL matches the desired pattern
            return wait.until(webDriver -> {
                String currentUrl = driver.getCurrentUrl();
                return currentUrl.matches(partUrl);
            });
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Scrolls to a WebElement located by the given xpath inside a scrollable container.
     * @param containerXpath the xpath locator for the scrollable container
     * @param elementXpath the xpath locator for the element to scroll to
     * @param fieldName the descriptive name of the field for logging
     */
    public void scrollToElementInContainer(String containerXpath, String elementXpath, String fieldName) {
        WebElement container = getWebElement(containerXpath);
        WebElement element = getWebElement(elementXpath);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            // First, scroll the element into view within the container
            js.executeScript(
                    "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                    element
            );

            justWait(1);
            // Check if the element is within the visible bounds of the container
            Boolean isVisible = (Boolean) js.executeScript(
                    "var containerRect = arguments[0].getBoundingClientRect();" +
                            "var elementRect = arguments[1].getBoundingClientRect();" +
                            "return (elementRect.top >= containerRect.top && elementRect.bottom <= containerRect.bottom);",
                    container, element
            );
            if (isVisible) {
                log(PASS, "Successfully scrolled to element " + fieldName + " inside the container.");
            } else {
                log(FAIL, "Element " + fieldName + " is not fully visible inside the container after scrolling.");
            }
        } catch (Exception e) {
            log(FAIL, "Failed to scroll to element " + fieldName + " inside the container. Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves the background color of a WebElement located by the given xpath.
     * @param xpath the xpath locator for the WebElement
     * @param fieldName the descriptive name of the field for logging
     * @return the background color of the WebElement as a String in rgba format, or null if an error occurs
     */
    public String getBackgroundColor(String xpath, String fieldName) {
        WebElement element = getWebElement(xpath);
        try {
            // Retrieve the background color of the element
            String rgbaColor = element.getCssValue("background-color");
            String backgroundColor = convertRgbaToHex(rgbaColor);
            log(PASS, "Retrieved background color of " + fieldName + ": " + backgroundColor);
            return backgroundColor;
        } catch (Exception e) {
            log(FAIL, "Failed to retrieve background color of " + fieldName + ". Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Converts a rgba color value to a hex color string.
     * @param rgbaColor the rgba color string (e.g., "rgba(255, 255, 255, 1)")
     * @return the corresponding hex color string (e.g., "#FFFFFF")
     */
    private String convertRgbaToHex(String rgbaColor) {
        // Extract the rgba values from the string
        String[] rgbaValues = rgbaColor.replace("rgba(", "").replace(")", "").split(",");
        int red = Integer.parseInt(rgbaValues[0].trim());
        int green = Integer.parseInt(rgbaValues[1].trim());
        int blue = Integer.parseInt(rgbaValues[2].trim());
        // Convert the RGB values to hex format
        return String.format("#%02x%02x%02x", red, green, blue).toUpperCase();
    }

    /**
     * Checks whether the WebElement identified by the given xpath is Enabled.
     * @param xpath the xpath locator for the WebElement to check
     * @return true if the WebElement is enabled, false otherwise
     */
    public boolean isEnabled(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return element.isEnabled();
    }
}
