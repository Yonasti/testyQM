package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

public class TopMenuPage extends BasePage {

    public static final String URL = "https://qualityminds.com/";
    private WebDriverWait wait;
    private int timeout = 5;
    private long polling = 500;

    @FindBy(css = "#top-menu > li > a :nth-last-child(1)")
    public WebElement languageSubmenu;

    @FindBy(css = "img[alt='DE']")
    private WebElement germanLanguage;

    @FindBy(css = "#top-menu > li.mega-menu.menu-item > ul > li> ul > li:nth-child(3) > a")
    private WebElement automatedTestsLink;

    @FindBy(css = "#top-menu-nav > ul > li:nth-child(4) > ul > li:nth-child(5) > a")
    private WebElement eventLink;

    @FindBy(css = "#logo > img")
    private WebElement homePageLink;

    @FindBy(css = "#top-menu > li")
    private List<WebElement> topMenuNav;

    public TopMenuPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(getDriver(), 15, polling);
    }

    public static TopMenuPage using (WebDriver driver) {
        return new TopMenuPage(driver);
    }

    public TopMenuPage hoverOverLanguageSection() {
        Actions action = new Actions(getDriver());
        action.moveToElement(this.languageSubmenu).perform();
        return this;
    }

    public WebElement getTopMenuItemByText(String txt) {
        return this.topMenuNav.stream().filter (item -> item.getText()
                        .toLowerCase()
                        .contains(txt))
                        .findFirst().get();
    }

    public TopMenuPage chooseGermanLanguage() {
        wait.until(ExpectedConditions.visibilityOf(this.germanLanguage));
        this.germanLanguage.click();
        return this;
    }

    public TopMenuPage hoverOverTopMenuElement(String txt) {
        Actions action = new Actions(getDriver());
        WebElement el = this.getTopMenuItemByText(txt.toLowerCase());
        action.moveToElement(el).perform();
        return this;
    }

    public AutomatedTestPage chooseAutomatedTests() {
        this.automatedTestsLink.click();
        return new AutomatedTestPage(getDriver());
    }

    public String getLanguageAttr() {
        return this.languageSubmenu.getAttribute("alt");
    }


    @Step("Hover over the language menu and click German flag")
    public TopMenuPage hoverOverLanguageAndChooseGerman() {
        this.hoverOverLanguageSection();
        this.chooseGermanLanguage();
        getLogger().info("Hovering over language submenu and choosing German.");
        return this;
    }

    @Step("Hover over Portfolio and click Automatisiertes Testen")
    public AutomatedTestPage hoverOverPortfolioAndChooseAT(String txt) {
        this.hoverOverTopMenuElement(txt);
        this.chooseAutomatedTests();
        getLogger().info("Hovering over portfolio and choosing AT.");
        return new AutomatedTestPage(getDriver());
    }

    @Step("Go to main page URL")
    public TopMenuPage goToHomePage() {
        this.getDriver().get(this.URL);
        getLogger().info("Go to home page.");
        return this;
    }

    @Step("Verify that you see the English version of page")
    public TopMenuPage verifyThatEngVersionIdDisplayed() {
        Assert.assertEquals(this.getLanguageAttr(), "EN");
        getLogger().info("Checking if english version is on the screen.");
        return this;
    }

    @Step("Hover on ABOUT US at the top navigation and verify if submenu is displayed")
    public TopMenuPage hoverOverAboutUsSection() {
        this.hoverOverTopMenuElement("about us");
        getLogger().info("Hovering over AboutUs section.");
        return this;
    }

    @Step("Click on Events")
    public EventsPage chooseEventLink() {
        this.eventLink.click();
        getLogger().info("Choosing link to events page.");
        return new EventsPage(getDriver());
    }
}
