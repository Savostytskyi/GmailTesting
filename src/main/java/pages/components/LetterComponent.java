package pages.components;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import static core.handlers.FileHendler.filePathHandling;
import static core.property.PropertyReader.getProperty;

@Name("Form for creation new letter")
@FindBy(xpath="//div")
public class LetterComponent extends HtmlElement {
    @Name("Recipient input field")
    @FindBy(xpath = "(//div/input/following-sibling::textarea)[1]")
    private TextInput recipientInput;

    @Name("Subject input field")
    @FindBy(xpath = "//input[@name='subjectbox']")
    private TextInput subjectInput;

    @Name("Letter text area")
    @FindBy(xpath = "//table/tbody/tr/td[2]/div[2]/div")
    private TextInput textArea;

    @Name("Add file button")
    @FindBy(xpath = "//div[@command='Files']//div[@id]")
    private Button fileButton;

    @Name("Send button")
    @FindBy(xpath = "//table/tbody/tr/td[1]/div/div[@role='button']")
    private Button sendButton;

    public TextInput getRecipientInput() {
        return recipientInput;
    }

    public TextInput getSubjectInput() {
        return subjectInput;
    }

    public TextInput getTextArea() {
        return textArea;
    }

    public Button getSendButton() {
        return sendButton;
    }

    public Button getFileButton() {
        return fileButton;
    }

    public void fillInField(String letter) {
        getRecipientInput().sendKeys(getProperty(letter + ".recipient"));
        getSubjectInput().sendKeys(getProperty(letter + ".subject"));
        getTextArea().sendKeys(getProperty(letter + ".text"));
    }

    public void fillInFieldsAndFile(String letter) {
        getRecipientInput().sendKeys(getProperty(letter + ".recipient"));
        getSubjectInput().sendKeys(getProperty(letter + ".subject"));
        getTextArea().sendKeys(getProperty(letter + ".text"));
        getFileButton().click();
        filePathHandling(letter);
    }

    public void performActions(){
        getSendButton().click();
    }
}

