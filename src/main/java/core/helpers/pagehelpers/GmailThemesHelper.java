package core.helpers.pagehelpers;

import org.openqa.selenium.WebDriver;
import pages.GmailThemesPage;
import java.util.Random;
import static core.helpers.generalhelpers.VerifyHelper.isElementPresent;
import static core.helpers.generalhelpers.WaitHelper.delay;
import static core.helpers.generalhelpers.WaitHelper.waitForElementLocated;
import static core.helpers.generalhelpers.WaitHelper.waitForPageLoad;
import static org.testng.Assert.assertTrue;

/**
 * @author Anton_Savostytskyi on 13.06.2015.
 *         Helper for themes page
 */

public class GmailThemesHelper {

    private WebDriver driver;

    public GmailThemesHelper(WebDriver driver) {
        this.driver = driver;
    }

    public GmailThemesHelper checkSetRandomTheme(GmailThemesPage themesPage){
        Random randomTheme = new Random();
        waitForPageLoad(driver);
        waitForElementLocated(themesPage.NAVIGATE_MENU, driver);
        themesPage.getThemesList().get(randomTheme.nextInt(themesPage.getThemesList().size()-1)).click();
        assertTrue(isElementPresent(themesPage.THEME_CHENGED_MESSAGE, driver), "Verify that new theme has been set");
        delay(2000);
        return new GmailThemesHelper(driver);
    }
}
