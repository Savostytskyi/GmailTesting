package core.reporting;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class ReportCustomizer {

    public static void getInfo(ITestResult result){
        Reporter.log("test faild");
        String s1 = "<table><tr><td><font style=\"text-decoration: underline;\" size=\"3\" face=\"Comic sans MS\" color=\"green\"><b>Screenshot</b></font></td></tr>";
        Reporter.log(s1);

        String failureImageFileName = "sample1"+".png";
        String userDirector = System.getProperty("user.dir") + "/";
        Reporter.log("<tr><td><a href=\""+ userDirector + failureImageFileName +"\"><img src=\"file:///" + userDirector+ failureImageFileName + "\" alt=\"\""+ "height='120' width='120'/></td></tr> ");
    }

    public void catchExceptions(ITestResult result, WebDriver driver) {
        BufferedWriter writer = null;
        String methodName = result.getName();
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        if (!result.isSuccess()) {
            try {
                String failureImageFileName = "sample1"+".png";
                String failureImageFileName1;
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(failureImageFileName));
                getInfo(result);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
