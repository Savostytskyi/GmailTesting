package core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Anton_Savostytskyi on 14.06.2015.
 * This class responsible for initializing web driver.
 * Depending on the received parameters initialized object of a driver
 */

public class DriverInitializer {

    private static WebDriver DRIVER;

    private static WebDriver getDriver(String browserName) {

        if (browserName.equals("ff")) {
            setFireFoxDriver();
        } else if (browserName.equals("gc")) {
            setChromeDriver();
        } else if (browserName.equals("ie")) {
            setIEDriver();
        } else setFireFoxDriver();
        DRIVER.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return DRIVER;
    }

    public static WebDriver getWebFactoryInstance(String browserName) {
        if (DRIVER == null) {
            return getDriver(browserName);
        }
        return DRIVER;
    }

    private static void setFireFoxDriver() {
        DRIVER = new FirefoxDriver();
    }

    private static void setIEDriver() {
        File file = new File("libdrivers/IEDriverServer32.exe");
        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        DRIVER = new InternetExplorerDriver();
    }

    private static void setChromeDriver() {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("libdrivers/chromedriver.exe"))
                .usingAnyFreePort().build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER = new ChromeDriver(service);
    }
}


