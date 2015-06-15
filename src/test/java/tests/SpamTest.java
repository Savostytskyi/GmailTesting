package tests;

import base.BaseTest;
import core.helpers.pagehelpers.GmailLoginHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.GmailInboxPage;
import pages.GmailLoginPage;

public class SpamTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setupBeforeSuite")
    public void setUpConfiguration() {
        loginPage = new GmailLoginPage(driver);
        inboxPage = new GmailInboxPage(driver);
        loginHelper = new GmailLoginHelper(driver);
    }

/*    @Test(description = "Check that the letters sent to the spam. Subtask #1")
    public void checkLettersInSpamFolder() throws InterruptedException {
        loginHelper
                .loginToGmail(loginPage, "user-first")
                .createAndSendNewLetter(inboxPage, "letter-first")
                .logOutFromMail(inboxPage)
                .addOneMoreAccount(loginPage)
                .loginToGmail(loginPage, "user-second")
                .markLetterAsASpam(inboxPage, "letter-first")
                .logOutFromMail(inboxPage);
    }*/

    @AfterMethod
    public void goBack() throws InterruptedException {
        Thread.sleep(3000);
    }
}
