package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import static core.helpers.generalhelpers.WaitHelper.waitForElementLocated;
import static core.property.PropertyReader.getProperty;
import static org.openqa.selenium.By.xpath;

public class GmailLoginPage extends HtmlElement {

    private WebDriver driver;

    public final String LOGIN_INPUT_FIELD = "//input[@id='Email']";
    public final String PASSWORD_INPUT_FIELD = "//input[@id='Passwd']";
    public final String NEXT_BUTTON = "//input[@id='next']";
    public final String SIGNIN_BUTTON = "//input[@id='signIn']";
    public final String ACCOUNT_CHOOSER = "//a[@id='account-chooser-link']";
    public final String ADD_ACCOUNT = "//a[@id='account-chooser-add-account']";

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Name("Login input field")
    @FindBy(xpath = LOGIN_INPUT_FIELD)
    private TextInput loginField;

    public TextInput getLoginField() {
        return loginField;
    }

    @Name("Password input field")
    @FindBy(xpath = PASSWORD_INPUT_FIELD)
    private TextInput passwordField;

    public TextInput getPasswordField() {
        return passwordField;
    }

    @Name("Login button")
    @FindBy(xpath = NEXT_BUTTON)
    private Button nextButton;

    public Button getNextButton() {
        return nextButton;
    }

    @Name("Sign in button")
    @FindBy(xpath = SIGNIN_BUTTON)
    private Button signinButton;

    public Button getSigninButton() {
        return signinButton;
    }

    @Name("Choose one more account button")
    @FindBy(xpath = ACCOUNT_CHOOSER)
    private Button chooseButton;

    public Button getChooseButton() {
        return chooseButton;
    }

    @Name("Add account button")
    @FindBy(xpath = ADD_ACCOUNT)
    private Button addButton;

    public Button getAddButton() {
        return addButton;
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
