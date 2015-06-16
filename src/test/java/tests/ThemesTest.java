package tests;

import base.BaseTest;
import core.helpers.pagehelpers.GmailInboxHelper;
import core.helpers.pagehelpers.GmailLoginHelper;
import core.helpers.pagehelpers.GmailThemesHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GmailInboxPage;
import pages.GmailLoginPage;
import pages.GmailThemesPage;

/**
 * Created by Savostytskyi Anton on 14.06.2015.
 */
public class ThemesTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setupBeforeSuite")
    public void setUpConfiguration() {
        loginPage = new GmailLoginPage(driver);
        inboxPage = new GmailInboxPage(driver);
        loginHelper = new GmailLoginHelper(driver);
        inboxHelper = new GmailInboxHelper(driver);
        themesPage = new GmailThemesPage(driver);
        themesHelper = new GmailThemesHelper(driver);
    }

    @Test(description = "Check that the background theme setting up. Subtask #4")
    public void checkLettersInSpamFolder() throws InterruptedException{

        loginHelper
                .loginToGmail(loginPage, "user-first")
                .navigateToThemesPage(inboxPage)
                .checkSetRandomTheme(themesPage);
    }

    @AfterMethod
    public void goBack(){
        inboxHelper.logOutFromMail(inboxPage);
    }
}
