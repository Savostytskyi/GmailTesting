package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import static core.helpers.generalhelpers.WaitHelper.waitForElementLocated;
import static core.property.PropertyReader.getProperty;
import static org.openqa.selenium.By.xpath;

/**
 * @author Anton_Savostytskyi on 15.06.2015.
 */

public class GmailLoginPage extends HtmlElement {

    private WebDriver driver;

    public final String LOGIN_INPUT_FIELD = "//input[@id='Email']";
    public final String PASSWORD_INPUT_FIELD = "//input[@id='Passwd']";
    public final String NEXT_BUTTON = "//input[@id='next']";
    public final String SIGNIN_BUTTON = "//input[@id='signIn']";
    public final String ACCOUNT_CHOOSER = "//a[@id='account-chooser-link']";
    public final String ADD_ACCOUNT = "//a[@id='account-chooser-add-account']";
    public final String LINK_SIGNIN_DIFFERENT = "//span[@id='link-signin-different']/a";

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    @Name("Login input field")
    @FindBy(xpath = LOGIN_INPUT_FIELD)
    private TextInput loginField;

    @Name("Password input field")
    @FindBy(xpath = PASSWORD_INPUT_FIELD)
    private TextInput passwordField;

    @Name("Login button")
    @FindBy(xpath = NEXT_BUTTON)
    private Button nextButton;

    @Name("Sign in button")
    @FindBy(xpath = SIGNIN_BUTTON)
    private Button signinButton;

    @Name("Choose one more account button")
    @FindBy(xpath = ACCOUNT_CHOOSER)
    private Button chooseButton;

    @Name("Add account button")
    @FindBy(xpath = ADD_ACCOUNT)
    private Button addButton;

    @Name("Link sign in other account")
    @FindBy(xpath = LINK_SIGNIN_DIFFERENT)
    private Link diffButton;

    public WebDriver getDriver() {
        return driver;
    }

    public TextInput getLoginField() {
        return loginField;
    }

    public TextInput getPasswordField() {
        return passwordField;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Button getSigninButton() {
        return signinButton;
    }

    public Button getChooseButton() {
        return chooseButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Link getDiffButton() {
        return diffButton;
    }

    public void loginAsUser(String user) {
        getLoginField().clear();
        getLoginField().sendKeys(getProperty(user + ".login"));
        getNextButton().click();
        waitForElementLocated(xpath(PASSWORD_INPUT_FIELD), driver);
        getPasswordField().sendKeys(getProperty(user + ".pass"));
        getSigninButton().click();
    }
}
