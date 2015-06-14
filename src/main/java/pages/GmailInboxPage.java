package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.components.LetterComponent;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import static core.helpers.generalhelpers.WaitHelper.alertWaiter;
import static core.helpers.generalhelpers.WaitHelper.waitForElementLocated;
import static core.property.PropertyReader.getProperty;

public class GmailInboxPage {

    private WebDriver driver;

    public final String CREATE_LETTER_BUTTON = "(//div[@role='button'])[6]";
    public final String POPUP_MENU = "(//a[@aria-haspopup='true'])[3]";
    public final String EXIT_BUTTON = "//div[2]/div[3]/div[2]/a";
    public final String TO_SPAM_BUTTON = "(//div[2]/div[1]/div/div[2]/div[2])[1]";

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

    public void createNewLetter(String letter) {
        getNewLetterButton().click();
        letterComponent.fillInField(letter);
    }

    public void logOutFromMail(){
        getPopupMenu().click();
        getExitButton().click();
    }

    public void alertHandler() {
        try {
            alertWaiter(driver);
            driver.switchTo().alert().accept();
            logOutFromMail();
        } catch (NoAlertPresentException e) {
            System.out.print("No alert present.");
        }
    }

    public void findLetterWithCorrespondingTopic(String letter){
        waitForElementLocated(By.xpath("//span/b[text()='" + getProperty(letter + ".subject") + "']"), driver);
        driver.findElement(By.xpath("//span/b[text()='"+ getProperty(letter + ".subject")+"']")).click();
    }

    public void markLetter() {
        getToSpamButton().click();
    }
}
