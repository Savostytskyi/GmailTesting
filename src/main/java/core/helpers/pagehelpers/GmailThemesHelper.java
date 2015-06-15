package core.helpers.pagehelpers;

import core.helpers.generalhelpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.GmailThemesPage;

import java.util.Random;

import static core.helpers.generalhelpers.VerifyHelper.isElementPresent;

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
        WaitHelper.waitForElementLocated(themesPage.NAVIGATE_MENU, driver);
        themesPage.getThemesList().get(rand.nextInt(themesPage.getThemesList().size()-1)).click();
        Assert.assertTrue(isElementPresent(themesPage.THEME_CHENGED_MESSAGE, driver));
        return new GmailThemesHelper(driver);
    }
}
