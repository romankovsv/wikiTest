package framework;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static framework.DriverManager.getDriver;
import static framework.Settings.DEFAULT_IMPLICIT_TIMEOUT;


public class TestSessionListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(String.format("\n***** Test '%s' started ******\n", result.getName()));
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
        DriverManager.setWebDriver(Settings.getDriver(Settings.getBrowser()));
        DriverManager.getDriver().manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        DriverManager.getDriver().manage().window().maximize();

    }


    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(String.format("\n******* Test '%s' : '%s' passed   *******\n", result.getTestClass().getName(), result.getName()));
        DriverManager.getDriver().quit();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(String.format("\n******* Test '%s':'%s' failed ******\n", result.getTestClass().getName(), result.getName()));
        createScreenshot(result.getTestName());
        DriverManager.getDriver().quit();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(String.format("\n***** Test '%s' skipped ******\n", result.getName()));
        DriverManager.getDriver().quit();

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        DriverManager.getDriver().quit();
    }


    @Override
    public void onFinish(ITestContext context) {

    }

    public void createScreenshot(String screenName) {
        try {
            File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            String destDir = "target/surefire-reports/screenshots";
            boolean isFolderExists = (new File(destDir).exists());

            if (!isFolderExists) {
                boolean isFolder = (new File(destDir).mkdirs());
                Assert.assertTrue(isFolder, "Folder was not created");
            }
            String destFile = String.format("%s/%s.png", destDir, screenName);
            FileUtils.copyFile(scrFile, new File(destFile));
        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onStart(ITestContext context) {
    }

}
