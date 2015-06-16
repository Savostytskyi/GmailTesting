package core.helpers.pagehelpers;

import core.helpers.generalhelpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.GmailLoginPage;

import static core.helpers.generalhelpers.VerifyHelper.isElementPresent;
import static core.helpers.generalhelpers.VerifyHelper.isElementVisible;
import static core.helpers.generalhelpers.WaitHelper.waitForElementIsClickable;

public class GmailLoginHelper {

    private WebDriver driver;

    public GmailLoginHelper(WebDriver driver) {
        this.driver = driver;
    }

    public GmailInboxHelper loginToGmail(GmailLoginPage loginPage, String user) {
        if (isElementVisible(By.xpath(loginPage.LINK_SIGNIN_DIFFERENT), driver)) {
            waitForElementIsClickable(loginPage.getDiffButton().getWrappedElement(), driver);
            loginPage.getDiffButton().click();
        }
        if (isElementPresent(By.xpath(loginPage.ACCOUNT_CHOOSER), driver))
            loginPage.getChooseButton().click();
        if (isElementPresent(By.xpath(loginPage.ADD_ACCOUNT), driver))
            loginPage.getAddButton().click();
        waitForElementIsClickable(loginPage.getNextButton().getWrappedElement(), driver);
        loginPage.loginAsUser(user);
        return new GmailInboxHelper(driver);
    }
}
