package tests;

import base.BaseTest;
import core.helpers.pagehelpers.GmailInboxHelper;
import core.helpers.pagehelpers.GmailLoginHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GmailInboxPage;
import pages.GmailLoginPage;

public class StarredTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setupBeforeSuite")
    public void setUpConfiguration() {
        loginPage = new GmailLoginPage(driver);
        inboxPage = new GmailInboxPage(driver);
        loginHelper = new GmailLoginHelper(driver);
        inboxHelper = new GmailInboxHelper(driver);
    }

    @Test(description = "Check that the letters adding to starred folder after drag it. Subtask #2")
    public void checkLettersDragToStarred() {
        loginHelper
                .loginToGmail(loginPage, "user-first")
                .createAndSendNewLetter(inboxPage, "letter-first")
                .logOutFromMail(inboxPage)
                .loginToGmail(loginPage, "user-second")
                .dragLetterToStarred(inboxPage, "letter-first")
                .checkThatLetterPresentInStarred(inboxPage, "letter-first");
    }

    @AfterMethod
    public void goBack() {
        inboxHelper.logOutFromMail(inboxPage);
    }
}
