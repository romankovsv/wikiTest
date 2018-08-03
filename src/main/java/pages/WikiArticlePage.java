package pages;

import framework.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WikiArticlePage extends BasePage {

    @FindBy(xpath = "(//div[@id='mw-content-text']//ul)[2]/li/a")
    private List<WebElement> actors;

    public WikiArticlePage saveActorsTo(List<String> list) {
        actors.stream().forEach(a -> list.add(a.getAttribute("href")));
        return this;
    }

    public ActorPage openActorPage(String href) {

        DriverManager.getDriver().get(href);
        return new ActorPage();
    }
}
