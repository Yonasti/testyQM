package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AutomatedTestPage extends BasePage {

    @FindBy(css = "article > div > div > div > div> div > div > div > a")
    private WebElement contactUsButton;

    public AutomatedTestPage(WebDriver driver) {
        super(driver);
    }

    public static AutomatedTestPage using (WebDriver driver) {
        return new AutomatedTestPage(driver);
    }

    public boolean checkContactButtonIsPresent() {
       return this.contactUsButton.isDisplayed();
    }

    public String getMailingAttributeOfContactUsButton() {
        return this.contactUsButton.getAttribute("href");
    }

    public String getPageUrl() {
        getLogger().info("Getting current url.");
        return getDriver().getCurrentUrl();
    }

    @Step("Verify that page contains button Kontaktiere Uns which links to the specified email address: testing@qualityminds.de")
    public AutomatedTestPage verifyContacUsButton() {
        Assert.assertTrue(this.checkContactButtonIsPresent());
        Assert.assertTrue(this.getMailingAttributeOfContactUsButton().contains("mailto:testing@qualityminds.de") );
        getLogger().info("Checking contact us button.");
        return this;
    }

    @Step("Verify if the page displayed in step 3 is the same as page displayed in step 8")
    public AutomatedTestPage verifyTheLink(String value) {
        Assert.assertEquals(getDriver().getCurrentUrl(), value);
        getLogger().info("Checking if the page is the same as in the 8th step.");
        return this;
    }
}
