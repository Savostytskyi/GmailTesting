package core.helpers.generalhelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.util.Strings.isNullOrEmpty;

public class VerifyHelper {

   public static boolean isElementPresent(By by, WebDriver driver) {
        return !driver.findElements(by).isEmpty();
    }
}

