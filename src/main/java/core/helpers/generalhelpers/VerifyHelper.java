package core.helpers.generalhelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyHelper {

   public static boolean isElementPresent(By by, WebDriver driver) {
        return !driver.findElements(by).isEmpty();
    }
}

