package core.helpers.generalhelpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

public class WaitHelper {

    public static void waitForElementIsClickable(WebElement webElement, WebDriver driver) {
        Wait wait = new FluentWait(driver)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withTimeout(25,TimeUnit.SECONDS)
                .ignoring(WebDriverException.class);
       wait.until(ExpectedConditions.elementToBeClickable(webElement));
   }

    public static void waitForElementIsVisible(WebElement webElement, WebDriver driver) {
        Wait wait = new FluentWait(driver)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withTimeout(25,TimeUnit.SECONDS)
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForElementLocated(By locator, WebDriver driver) {
        Wait wait = new FluentWait(driver)
                .pollingEvery(2, TimeUnit.SECONDS)
                .withTimeout(25,TimeUnit.SECONDS)
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static void alertWaiter(WebDriver driver){
        Alert alert = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
}

