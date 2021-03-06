package core.helpers.generalhelpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

/**
 * @author Anton_Savostytskyi on 13.06.2015.
 *         Class WaitHelper responsible for waiting conditions of
 *         web element on the web page
 */

public class WaitHelper {

    public static void waitForElementIsClickable(WebElement webElement, WebDriver driver) {
        Wait wait = new FluentWait(driver)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withTimeout(25, TimeUnit.SECONDS)
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitForElementIsVisible(WebElement webElement, WebDriver driver) {
        Wait wait = new FluentWait(driver)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withTimeout(25, TimeUnit.SECONDS)
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementLocated(By locator, WebDriver driver) {
        Wait wait = new FluentWait(driver)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withTimeout(25, TimeUnit.SECONDS)
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public static void waitForElementNotVisible(By locator, WebDriver driver) {
        delay(1000);
        Wait wait = new FluentWait(driver)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withTimeout(25, TimeUnit.SECONDS)
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void delay(int sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            Reporter.log("Interrupted Exception!");
        }
    }
}

