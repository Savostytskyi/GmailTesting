package core.helpers.generalhelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Anton_Savostytskyi on 13.06.2015.
 *         Class VerifyHelper responsible for verifying presence element
 *         on the web page
 */

public class VerifyHelper {

    public static boolean isElementPresent(By by, WebDriver driver) {
        return !driver.findElements(by).isEmpty();
    }

}

