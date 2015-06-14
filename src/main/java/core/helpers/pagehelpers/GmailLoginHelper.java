package core.helpers.pagehelpers;

import core.helpers.generalhelpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import pages.GmailLoginPage;

import static core.helpers.generalhelpers.WaitHelper.waitForElementIsClickable;

public class GmailLoginHelper {

    private WebDriver driver;

    public GmailLoginHelper(WebDriver driver) {
        this.driver = driver;
    }

    public GmailInboxHelper loginToGmail(GmailLoginPage loginPage, String user) {
        waitForElementIsClickable(loginPage.getNextButton().getWrappedElement(), driver);
        loginPage.loginAsUser(user);
        return new GmailInboxHelper(driver);
    }

    public GmailLoginHelper addOneMoreAccount(GmailLoginPage loginPage) {
        loginPage.addAccountInMail();
        return new GmailLoginHelper(driver);
    }

    public GmailLoginHelper fastAddAnAccount(GmailLoginPage loginPage) {
        loginPage.getAddButton().click();
        return new GmailLoginHelper(driver);
    }
}
