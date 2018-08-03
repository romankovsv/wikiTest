package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActorPage extends BasePage {


    @FindAll({
            @FindBy(xpath = ".//h3/span[@id='Filmography' or @id='Selected_filmography']/../following-sibling::div/ul/li"),
            @FindBy(xpath = "(.//h2/span[@id='Filmography' or @id='Selected_filmography']/../following-sibling::ul)[1]/li/i"),
            @FindBy(xpath = "(.//*[@id='Filmography']/../following-sibling::table)[1]/tbody/tr/td[2]//a")
    })
    private List<WebElement> movies;

    public boolean FilmographyContainsMovie(String expectedMovie) {

      return movies.stream()
                .filter(m -> m.getText()
                        .contains(expectedMovie))
                .count() > 0;
    }
}
