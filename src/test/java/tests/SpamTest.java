package tests;

import base.BaseTest;
import core.helpers.pagehelpers.GmailInboxHelper;
import core.helpers.pagehelpers.GmailLoginHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GmailInboxPage;
import pages.GmailLoginPage;

public class SpamTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setupBeforeSuite")
    public void setUpConfiguration() {
        loginPage = new GmailLoginPage(driver);
        inboxPage = new GmailInboxPage(driver);
        loginHelper = new GmailLoginHelper(driver);
        inboxHelper = new GmailInboxHelper(driver);
    }

    @Test(description = "Check that the letters sent to the spam. Subtask #1")
    public void checkLettersInSpamFolder() throws InterruptedException {
        loginHelper
                .loginToGmail(loginPage, "user-second")
                .skipAllSettingsWindows(inboxPage)
                .createAndSendNewLetter(inboxPage, "letter-second")
                .logOutFromMail(inboxPage)
                .loginToGmail(loginPage, "user-first")
                .markLetterAsASpam(inboxPage, "letter-second")
                .logOutFromMail(inboxPage)
                .loginToGmail(loginPage, "user-second")
                .skipAllSettingsWindows(inboxPage)
                .createAndSendNewLetter(inboxPage, "letter-second")
                .logOutFromMail(inboxPage)
                .loginToGmail(loginPage, "user-first")
                .navigateToSpamFolder(inboxPage)
                .checkThatLetterInSpam(inboxPage, "letter-second");
    }

    @AfterMethod
    public void goBack() {
        inboxHelper.logOutFromMail(inboxPage);
    }
}
