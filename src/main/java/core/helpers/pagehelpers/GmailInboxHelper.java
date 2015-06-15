package core.helpers.pagehelpers;

import core.helpers.generalhelpers.VerifyHelper;
import core.helpers.generalhelpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.GmailInboxPage;

/**
 * Created by Savostytskyi Anton on 13.06.2015.
 */
public class GmailInboxHelper {

    private WebDriver driver;

    public GmailInboxHelper(WebDriver driver) {
        this.driver = driver;
    }

    public GmailInboxHelper createAndSendNewLetter(GmailInboxPage inboxPage, String letter) throws InterruptedException {
        inboxPage.createNewLetter(letter);
        Thread.sleep(3000);
        return new GmailInboxHelper(driver);
    }

    public GmailLoginHelper logOutFromMail(GmailInboxPage inboxPage){
        inboxPage.logOutFromMail();
      //  inboxPage.alertHandler();
        return new GmailLoginHelper(driver);
    }

    public GmailInboxHelper markLetterAsASpam(GmailInboxPage inboxPage, String letter){
        inboxPage.findLetterWithCorrespondingTopic(letter);
        inboxPage.markLetter();
        inboxPage.suspeciousButtonHendler();
        return new GmailInboxHelper(driver);
    }

    public GmailInboxHelper dragLetterToStarred(GmailInboxPage inboxPage, String letter){
        inboxPage.dragLetterWithCorrespondingTopic(letter);
        return new GmailInboxHelper(driver);
    }

    public GmailInboxHelper checkThatLetterPresentInStarred(GmailInboxPage inboxPage, String letter){
        WaitHelper.waitForElementLocated(inboxPage.findLetterInStarred(letter), driver);
        Assert.assertTrue(VerifyHelper.isElementPresent(inboxPage.findLetterInStarred(letter), driver));
        return new GmailInboxHelper(driver);
    }

    public GmailThemesHelper navigateToThemesPage(GmailInboxPage inboxPage){
        inboxPage.goToThemes();
        return new GmailThemesHelper(driver);
    }
}
