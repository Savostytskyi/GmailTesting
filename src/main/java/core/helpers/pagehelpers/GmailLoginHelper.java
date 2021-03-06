package core.helpers.pagehelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.GmailLoginPage;

import static core.helpers.generalhelpers.VerifyHelper.isElementPresent;
import static core.helpers.generalhelpers.WaitHelper.waitForElementIsClickable;

/**
 * @author Anton_Savostytskyi on 13.06.2015.
 *         Helper for login page
 */

public class GmailLoginHelper {

    private WebDriver driver;

    public GmailLoginHelper(WebDriver driver) {
        this.driver = driver;
    }

    public GmailInboxHelper loginToGmail(GmailLoginPage loginPage, String user) {
        if (isElementPresent(By.xpath(loginPage.ACCOUNT_CHOOSER), driver))
            loginPage.getChooseButton().click();
        if (isElementPresent(By.xpath(loginPage.ADD_ACCOUNT), driver))
            loginPage.getAddButton().click();
        waitForElementIsClickable(loginPage.getNextButton().getWrappedElement(), driver);
        loginPage.loginAsUser(user);
        return new GmailInboxHelper(driver);
    }
}