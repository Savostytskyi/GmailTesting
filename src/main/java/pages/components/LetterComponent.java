package pages.components;

import core.property.PropertyReader;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.awt.*;
import java.awt.event.KeyEvent;

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
        getSendButton().click();
    }

    public void fillInFieldsAndFile(String letter) {
        getRecipientInput().sendKeys(getProperty(letter + ".recipient"));
        getSubjectInput().sendKeys(getProperty(letter + ".subject"));
        getTextArea().sendKeys(getProperty(letter + ".text"));
        getFileButton().click();

        try {
            String path=System.getProperty("user.dir");
            System.out.println(System.getProperty("user.dir"));
            Robot robot = new Robot();
            robot.delay(5000);
            String message = (path+ PropertyReader.getProperty(letter+".file")).toUpperCase();
            for (int i=0; i<message.length(); i++) {
                int c = message.charAt(i);

                if(c==':') {

                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_NUMPAD5);
                    robot.keyRelease(KeyEvent.VK_NUMPAD5);
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_NUMPAD8);
                    robot.keyRelease(KeyEvent.VK_NUMPAD8);
                    robot.delay(20);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.delay(20);
                } else if (c=='/' || c=='\\') {robot.keyPress(KeyEvent.VK_BACK_SLASH);}
                else if (c=='.') {
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_NUMPAD4);
                    robot.keyRelease(KeyEvent.VK_NUMPAD4);
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_NUMPAD6);
                    robot.keyRelease(KeyEvent.VK_NUMPAD6);
                    robot.delay(20);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.delay(20);}
                else
                robot.keyPress(c);
            }
            // 2nd Robot to my avail
            Robot okRobot = new Robot();

            // presses Enter
            okRobot.keyPress(KeyEvent.VK_ENTER);    // press Enter
            okRobot.keyRelease(KeyEvent.VK_ENTER);  // release Enter
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch (AWTException e) {
            e.printStackTrace();
        }

        getSendButton().click();
    }
}

