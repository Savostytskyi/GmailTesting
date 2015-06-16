package base;

import core.driver.DriverInitializer;
import core.helpers.pagehelpers.*;
import core.reporting.ReportCustomizer;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;
import static core.property.PropertyReader.getProperty;

public class BaseTest {

    protected WebDriver driver;

    protected GmailLoginPage loginPage;
    protected GmailInboxPage inboxPage;
    protected GmailThemesPage themesPage;

    protected GmailLoginHelper loginHelper;
    protected GmailInboxHelper inboxHelper;
    protected GmailThemesHelper themesHelper;

    @BeforeMethod
    protected void setupBeforeSuite() {
        driver = DriverInitializer.getWebFactoryInstance("gc");
        driver.manage().window().maximize();
        driver.get(getProperty("url.gmail"));

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws Exception {
        if (!result.isSuccess())
           new ReportCustomizer().catchExceptions(result, driver);
            Thread.sleep(1000);
    }

    @AfterSuite
    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

