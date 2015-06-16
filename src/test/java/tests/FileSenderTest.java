package tests;

import base.BaseTest;
import core.helpers.pagehelpers.GmailLoginHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GmailInboxPage;
import pages.GmailLoginPage;

import java.awt.*;

/**
 * Created by Savostytskyi Anton on 14.06.2015.
 */
public class FileSenderTest extends BaseTest {

    @BeforeMethod(dependsOnMethods = "setupBeforeSuite")
    public void setUpConfiguration() {
        loginPage = new GmailLoginPage(driver);
        inboxPage = new GmailInboxPage(driver);
        loginHelper = new GmailLoginHelper(driver);
    }

    @Test(description = "Check that that letter can be send with file. Subtask #3")
    public void checkLettersInSpamFolder() throws InterruptedException{
        loginHelper
                .loginToGmail(loginPage, "user-first")
                .createAndNewLetterWithFile(inboxPage, "letter-first")
                .logOutFromMail(inboxPage)
                .addOneMoreAccount(loginPage)
                .loginToGmail(loginPage, "user-second");
    }

    @AfterMethod
    public void goBack() throws InterruptedException {
        Thread.sleep(3000);
    }
}
