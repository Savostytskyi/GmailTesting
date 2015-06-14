package core.helpers.pagehelpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GmailInboxPage;
import pages.GmailLoginPage;

import static core.helpers.generalhelpers.WaitHelper.alertWaiter;
import static core.helpers.generalhelpers.WaitHelper.waitForElementIsClickable;

/**
 * Created by Savostytskyi Anton on 13.06.2015.
 */
public class GmailInboxHelper {

    private WebDriver driver;

    public GmailInboxHelper(WebDriver driver) {
        this.driver = driver;
    }

    public GmailInboxHelper createAndSendNewLetter(GmailInboxPage inboxPage, String letter) {
        inboxPage.createNewLetter(letter);
        return new GmailInboxHelper(driver);
    }

    public GmailLoginHelper logOutFromMail(GmailInboxPage inboxPage){
        inboxPage.logOutFromMail();
        inboxPage.alertHandler();
        return new GmailLoginHelper(driver);
    }

    public GmailInboxHelper markLetterAsASpam(GmailInboxPage inboxPage, String letter){
        inboxPage.findLetterWithCorrespondingTopic(letter);
        inboxPage.markLetter();
        return new GmailInboxHelper(driver);
    }

}
