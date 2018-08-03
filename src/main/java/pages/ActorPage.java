package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActorPage extends BasePage {

    @FindBy(xpath = "(.//h2/span[@id='Filmography' or @id='Selected_filmography']/../following-sibling::ul)[1]/li/i")
    private List<WebElement> movies;

    public boolean FilmographyContainsMovie(String expectedMovie) {

      return movies.stream()
                .filter(m -> m.getText()
                        .contains(expectedMovie))
                .count() > 0;
    }
}
