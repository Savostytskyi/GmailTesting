package core.helpers.generalhelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VerifyHelper {

   public static boolean isElementPresent(By by, WebDriver driver) {
        return !driver.findElements(by).isEmpty();
    }

    public static boolean isElementVisible(By by, WebDriver driver) {
        if(driver.findElement(by).isDisplayed()){
            return true;
        }else{
            return false;
        }
    }
}

