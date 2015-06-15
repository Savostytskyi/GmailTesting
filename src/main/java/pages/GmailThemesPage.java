package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;

/**
 * Created by Anton_Savostytskyi on 15.06.2015.
 */
public class GmailThemesPage {

    private WebDriver driver;

    public final String THEMES_LIST = "//tr[position()=2 or position()=4]//input[@name]/following-sibling::div[1]";
    public final By THEME_CHENGED_MESSAGE = By.xpath("//div[@role='alert']/div/div[2]");
    public final By NAVIGATE_MENU = By.xpath("(//a[@role='tab'])[1]");

    public GmailThemesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    @FindBy(xpath = THEMES_LIST)
    private List<Link> themesList;

    public List<Link> getThemesList() {
        return themesList;
    }

}
