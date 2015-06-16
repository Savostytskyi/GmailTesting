package tests;

import base.BaseTest;
import core.helpers.pagehelpers.GmailInboxHelper;
import core.helpers.pagehelpers.GmailLoginHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GmailInboxPage;
import pages.GmailLoginPage;

/**
 * Created by Savostytskyi Anton on 14.06.2015.
 */
public class FileSenderTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setupBeforeSuite")
    public void setUpConfiguration() {
        loginPage = new GmailLoginPage(driver);
        inboxPage = new GmailInboxPage(driver);
        loginHelper = new GmailLoginHelper(driver);
        inboxHelper = new GmailInboxHelper(driver);
    }

    @Test(description = "Check that that letter can be send with file. Subtask #3")
    public void checkLettersWithFile() {
        loginHelper
                .loginToGmail(loginPage, "user-first")
                .skipAllSettingsWindows(inboxPage)
                .createAndNewLetterWithFile(inboxPage, "letter-first")
                .logOutFromMail(inboxPage)
                .loginToGmail(loginPage, "user-second")
                .checkThatLetterContainFile(inboxPage, "letter-first");
    }

    @AfterMethod
    public void goBack() {
        inboxHelper.logOutFromMail(inboxPage);
    }
}
