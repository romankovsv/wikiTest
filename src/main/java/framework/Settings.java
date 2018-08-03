package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.fail;


public class Settings {
    public static final int DEFAULT_IMPLICIT_TIMEOUT = 10;
    public static BrowserType browser;
    public static Config config;


    static {
        config = new Config();
        browser = BrowserType.Browser(config.getBrowser());
    }

    public static URL getURL() {
        try {
            return new URL(config.getUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            fail("There was an error. Please see log");
            return null;
        }
    }

    public static BrowserType getBrowser() {
        return browser;
    }


    public static WebDriver getDriver(BrowserType browserType) {

        switch (browserType) {
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                return new ChromeDriver(options);
            default:
                throw new RuntimeException("Exception by creating driver");
        }


    }


}

