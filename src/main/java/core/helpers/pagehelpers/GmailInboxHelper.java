package core.helpers.pagehelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.GmailInboxPage;

import static core.handlers.FilePathHandler.actionExecutor;
import static core.helpers.generalhelpers.VerifyHelper.isElementPresent;
import static core.helpers.generalhelpers.WaitHelper.*;
import static org.testng.Assert.assertTrue;

/**
 * @author Anton_Savostytskyi on 13.06.2015.
 *         Helper for inbox page
 */

public class GmailInboxHelper {

    private WebDriver driver;

    public GmailInboxHelper(WebDriver driver) {
        this.driver = driver;
    }

    public GmailInboxHelper createAndSendNewLetter(GmailInboxPage inboxPage, String letter) {
        inboxPage.createNewLetter(letter);
        waitForElementLocated(inboxPage.LETTER_SENT_MESSAGE, driver);
        return new GmailInboxHelper(driver);
    }

    public GmailInboxHelper skipAllSettingsWindows(GmailInboxPage inboxPage) {
      /*  if (isElementPresent(By.xpath(inboxPage.CANCEL_BUTTON), driver))
            inboxPage.getCancelButton().click();*/
        delay(2000);
        actionExecutor();
        return new GmailInboxHelper(driver);
    }

    public GmailInboxHelper createAndNewLetterWithFile(GmailInboxPage inboxPage, String letter) {
        inboxPage.createNewFileLetter(letter);
        waitForElementLocated(inboxPage.LETTER_SENT_MESSAGE, driver);
        return new GmailInboxHelper(driver);
    }

    public GmailLoginHelper logOutFromMail(GmailInboxPage inboxPage) {
        waitForPageLoad(driver);
        actionExecutor();
        inboxPage.logOutFromMail();
        return new GmailLoginHelper(driver);
    }

    public GmailInboxHelper markLetterAsASpam(GmailInboxPage inboxPage, String letter) {
        inboxPage.findLetterWithCorrespondingTopic(letter);
        inboxPage.markLetter();
        if (isElementPresent(By.xpath(inboxPage.SPAM_CONFIRM_BUTTON), driver)) {
            inboxPage.getSpanConfirmButton().click();
            waitForElementNotVisible(By.xpath(inboxPage.SPAM_CONFIRM_BUTTON), driver);
        }
        return new GmailInboxHelper(driver);
    }

    public GmailInboxHelper dragLetterToStarred(GmailInboxPage inboxPage, String letter) {
        waitForElementLocated(By.xpath(inboxPage.STARRED_LINK), driver);
        inboxPage.dragLetterWithCorrespondingTopic(letter);
        return new GmailInboxHelper(driver);
    }

    public GmailInboxHelper checkThatLetterPresentInStarred(GmailInboxPage inboxPage, String letter) {
        waitForElementLocated(inboxPage.findLetterInStarred(letter), driver);
        assertTrue(isElementPresent(inboxPage.findLetterInStarred(letter), driver), "Verify that letter present in starred");
        return new GmailInboxHelper(driver);
    }

    public GmailInboxHelper checkThatLetterContainFile(GmailInboxPage inboxPage, String letter) {
        waitForPageLoad(driver);
        inboxPage.findLetterWithCorrespondingTopic(letter);
        assertTrue(isElementPresent(inboxPage.findFileInLetter(letter), driver), "Verify that letter contains file");
        return new GmailInboxHelper(driver);
    }

    public GmailThemesHelper navigateToThemesPage(GmailInboxPage inboxPage) {
        inboxPage.goToThemes();
        return new GmailThemesHelper(driver);
    }

    public GmailInboxHelper navigateToSpamFolder(GmailInboxPage inboxPage) {
        inboxPage.getMoreLink().click();
        waitForElementIsVisible(inboxPage.getSpamFolderButton().getWrappedElement(), driver);
        inboxPage.getSpamFolderButton().click();
        return new GmailInboxHelper(driver);
    }

    public GmailInboxHelper checkThatLetterInSpam(GmailInboxPage inboxPage, String letter) {
        waitForPageLoad(driver);
        assertTrue(isElementPresent(inboxPage.findLetterInSpamFolder(letter), driver), "Verify that letter present in spam");
        return new GmailInboxHelper(driver);
    }

}
