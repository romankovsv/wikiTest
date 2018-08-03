package pages;

import framework.DriverManager;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private PageFactory factory = new PageFactory();

    public BasePage(){
        factory.initElements(DriverManager.getDriver(), this);
    }
}
