package core.helpers.pagehelpers;

import org.openqa.selenium.WebDriver;
import pages.GmailThemesPage;
import java.util.Random;
import static core.helpers.generalhelpers.VerifyHelper.isElementPresent;
import static core.helpers.generalhelpers.WaitHelper.waitForElementLocated;
import static core.helpers.generalhelpers.WaitHelper.waitForPageLoad;
import static org.testng.Assert.assertTrue;

/**
 * Created by Anton_Savostytskyi on 15.06.2015.
 */
public class GmailThemesHelper {

    private WebDriver driver;

    public GmailThemesHelper(WebDriver driver) {
        this.driver = driver;
    }

    public GmailThemesHelper checkSetRandomTheme(GmailThemesPage themesPage){
        Random rand = new Random();
        waitForPageLoad(driver);
        waitForElementLocated(themesPage.NAVIGATE_MENU, driver);
        themesPage.getThemesList().get(rand.nextInt(themesPage.getThemesList().size()-1)).click();
        assertTrue(isElementPresent(themesPage.THEME_CHENGED_MESSAGE, driver), "Verify that new theme has been set");
        return new GmailThemesHelper(driver);
    }
}
