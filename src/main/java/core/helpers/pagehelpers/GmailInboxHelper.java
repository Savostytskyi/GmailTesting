package core.helpers.pagehelpers;

import core.helpers.generalhelpers.VerifyHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.GmailInboxPage;
import static core.helpers.generalhelpers.WaitHelper.waitForElementLocated;
import static org.testng.Assert.assertTrue;

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

    public GmailInboxHelper createAndNewLetterWithFile(GmailInboxPage inboxPage, String letter){
        inboxPage.createNewFileLetter(letter);
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
        waitForElementLocated(By.xpath(inboxPage.STARRED_LINK), driver);
        inboxPage.dragLetterWithCorrespondingTopic(letter);
        return new GmailInboxHelper(driver);
    }

    public GmailInboxHelper checkThatLetterPresentInStarred(GmailInboxPage inboxPage, String letter){
        waitForElementLocated(inboxPage.findLetterInStarred(letter), driver);
        assertTrue(VerifyHelper.isElementPresent(inboxPage.findLetterInStarred(letter), driver), "Verify that letter present in starred");
        return new GmailInboxHelper(driver);
    }

    public GmailThemesHelper navigateToThemesPage(GmailInboxPage inboxPage){
        inboxPage.goToThemes();
        return new GmailThemesHelper(driver);
    }
}
