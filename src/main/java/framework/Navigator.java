package framework;

import pages.WikiArticlePage;

import static framework.DriverManager.getDriver;

public class Navigator {

    public static WikiArticlePage launchWiki() {
        getDriver().navigate().to(Settings.getURL());
        return new WikiArticlePage();
    }
}
