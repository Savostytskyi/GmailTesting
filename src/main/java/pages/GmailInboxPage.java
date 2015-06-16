package pages;

import core.helpers.generalhelpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.components.LetterComponent;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import static core.helpers.generalhelpers.VerifyHelper.isElementPresent;
import static core.helpers.generalhelpers.WaitHelper.alertWaiter;
import static core.helpers.generalhelpers.WaitHelper.waitForElementLocated;
import static core.helpers.generalhelpers.WaitHelper.waitForPageLoad;
import static core.property.PropertyReader.getProperty;
import static org.openqa.selenium.By.xpath;

public class GmailInboxPage {

    private WebDriver driver;

    public final String CREATE_LETTER_BUTTON = "(//div[@role='button'])[6]";
    public final String POPUP_MENU = "(//a[@aria-haspopup='true'])[3]";
    public final String EXIT_BUTTON = "//div[2]/div[3]/div[2]/a";
    public final String TO_SPAM_BUTTON = "(//div[2]/div[1]/div/div[2]/div[2]/div/div)[1]";
    public final String STARRED_LINK = "(//div[@id]/div/div[1]/span/a)[2]";
    public final String SUSPECIOUS_LETTER_BUTTON = "//div[3]/button[1]";
    public final String CHANGE_THEME_BUTTON = "//span[text()='Выберите тему']";

    private LetterComponent letterComponent;

    public GmailInboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    @Name("Button for creation new letter")
    @FindBy(xpath = CREATE_LETTER_BUTTON)
    private Button newLetterButton;

    @Name("Popup menu")
    @FindBy(xpath = POPUP_MENU)
    private Button popupMenu;

    @Name("Exit button")
    @FindBy(xpath = EXIT_BUTTON)
    private Button exitButton;

    @Name("Add to spam button")
    @FindBy(xpath = TO_SPAM_BUTTON)
    private Button toSpamButton;

    @Name("Starred menu item")
    @FindBy(xpath = STARRED_LINK)
    private Link starredLink;

    @Name("Suspicious letter button")
    @FindBy(xpath = SUSPECIOUS_LETTER_BUTTON)
    private Button suspiciousButton;

    @Name("Themes button")
    @FindBy(xpath = CHANGE_THEME_BUTTON)
    private Button themesButton;

    public Button getPopupMenu() {
        return popupMenu;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Button getNewLetterButton() {
        return newLetterButton;
    }

    public Button getToSpamButton() {
        return toSpamButton;
    }

    public Link getStarredLink() {
        return starredLink;
    }

    public Button getThemesButton() {
        return themesButton;
    }

    public Button getSuspiciousButton() {
        return suspiciousButton;
    }

    public void createNewLetter(String letter) {
        waitForPageLoad(driver);
        getNewLetterButton().click();
        letterComponent.fillInField(letter);
    }

    public void createNewFileLetter(String letter) {
        waitForPageLoad(driver);
        getNewLetterButton().click();
        letterComponent.fillInFieldsAndFile(letter);
    }

    public void goToThemes() {
        getThemesButton().click();
    }

    public void logOutFromMail(){
        getPopupMenu().click();
        getExitButton().click();
    }

    public void alertHandler() {
        try {
            alertWaiter(driver);
            driver.switchTo().alert().accept();
          //  logOutFromMail();
        } catch (NoAlertPresentException e) {
            System.out.print("No alert present.");
        }
    }

    public void findLetterWithCorrespondingTopic(String letter){
        waitForElementLocated(xpath("//span/b[text()='" + getProperty(letter + ".subject") + "']"), driver);
        driver.findElement(xpath("//span/b[text()='" + getProperty(letter + ".subject") + "']")).click();
    }

    public void dragLetterWithCorrespondingTopic(String letter) {
        WebElement element = driver.findElement(xpath("//span/b[text()='" + getProperty(letter + ".subject") + "']"));
        (new Actions(driver)).dragAndDrop(element, getStarredLink().getWrappedElement()).perform();
    }

    public By findLetterInStarred (String letter) {
        getStarredLink().click();
        return xpath("//span/b[text()='" + getProperty(letter + ".subject") + "']");
    }

    public void markLetter() {
        waitForElementLocated(xpath(TO_SPAM_BUTTON), driver);
        getToSpamButton().click();
    }

    public void suspeciousButtonHendler () {
        if(isElementPresent(xpath(SUSPECIOUS_LETTER_BUTTON), driver)) {
            getSuspiciousButton().click();
        }
    }
}
