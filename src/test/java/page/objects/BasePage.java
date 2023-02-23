package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.util.logging.Logger;

public abstract class BasePage {

    private WebDriver driver;
    private Logger log;

    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        log = Logger.getLogger(String.valueOf(EventsPage.class));
    }

    public Logger getLogger() {
        return this.log;
    }

    public WebDriver getDriver() {
        return this.driver;
    }


}
