package wikipedia;

import framework.Navigator;
import framework.TestSessionListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.WikiArticlePage;
import java.util.ArrayList;
import java.util.List;

@Listeners(TestSessionListener.class)
public class WikiSuite {

    @Test
    public void checkThatActorHasMovieInFilmography() {

        List<String> actorsHrefs = new ArrayList();
        WikiArticlePage page = Navigator.launchWiki().saveActorsTo(actorsHrefs);

        for (String href : actorsHrefs) {
            Assert.assertTrue(page.openActorPage(href).FilmographyContainsMovie("To Kill a Dragon"), String.format("There are no movie in fillmography for actor: %s", href.split("\\/")[href.split("\\/").length - 1]));
        }
    }
}


