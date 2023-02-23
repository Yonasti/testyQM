package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class JobPositionPage extends BasePage {

    public static final String URL = "https://qualityminds.com/de/karriere/stellenangebote/";

    @FindBy(css = ".awsm-job-listing-item.awsm-grid-item > a")
    private WebElement firstJobPositionBox;

    @FindBy(css = ".awsm-job-listing-item.awsm-grid-item > a > div:nth-child(2) > div:nth-child(2) > span")
    private WebElement firstJobPositionDetailsLink;

    public JobPositionPage(WebDriver driver) {
        super(driver);
    }

    public static JobPositionPage using (WebDriver driver) {
        return new JobPositionPage(driver);
    }

    public boolean checkIfThereIsOneOfferAtLeast() {
        return this.firstJobPositionBox.isDisplayed();
    }

    @Step("In the first (top, left) box click Erfahre mehr")
    public JobPositionDetailsPage firstJobPositionGoToDetails() {
        ((JavascriptExecutor) getDriver()).executeScript("javascript:window.scrollBy(0,250)");//
        this.firstJobPositionDetailsLink.click();
        getLogger().info("Go to details of first job position.");
        return new JobPositionDetailsPage(getDriver());
    }

    @Step("Go to URL")
    public JobPositionPage goToPage() {
        getDriver().get(this.URL);
        getLogger().info("Go to home page.");
        return this;
    }

    @Step("Validate that at least one job offer is available")
    public JobPositionPage verifyIThereIsOneOfferAtLeast() {
        Assert.assertTrue(this.checkIfThereIsOneOfferAtLeast());
        getLogger().info("Veryfing that at least one job offer is available");
        return this;
    }

}
