package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.Arrays;
import java.util.stream.Collectors;


public class JobPositionDetailsPage extends BasePage {

    private WebDriverWait wait;
    private int timeout = 5;
    private long polling = 500;

    @FindBy(css = "#awsm-application-submit-btn")
    private WebElement sendButton;

    @FindBy(css = ".awsm-job-listing-item.awsm-grid-item > a > div:nth-child(2) > div:nth-child(2) > span")
    private WebElement firstJobPositionDetailsLink;

    @FindBy(id = "awsm-applicant-name")
    private WebElement nameAndSurnameTextInput;

    @FindBy(id = "awsm-applicant-email")
    private WebElement emailTextInput;

    @FindBy(id = "awsm-applicant-phone")
    private WebElement phoneTextInput;

    @FindBy(id = "awsm-cover-letter")
    private WebElement descriptionTextInput;

    @FindBy(css = "#awsm-application-form > div.awsm-job-form-group.awsm-job-form-file-group > label > div")
    private WebElement attachFileButton;

    @FindBy(id = "awsm_form_privacy_policy")
    private WebElement agreementCheckbox;

    @FindBy(id = "awsm-applicant-name-error")
    private WebElement nameErrorLabel;

    @FindBy(id = "#awsm-applicant-email-error")
    private WebElement emailErrorLabel;

    @FindBy(id = "awsm-applicant-phone-error")
    private WebElement phoneErrorLabel;

    @FindBy(id = "awsm-cover-letter-error")
    private WebElement descriptionErrorLabel;

    @FindBy(css = "div > div > div > div.awsm-job-entry-content.entry-content > p:nth-child(1)")
    private WebElement jobDescriptionParagraph;

    @FindBy(name = "awsm_file")
    private WebElement inputFile;

    @FindBy(css = "#awsm-application-form > div.awsm-job-form-group.awsm-job-form-file-group > label > div")
    private WebElement fileButtonLabel;

    public JobPositionDetailsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(getDriver(), 15, polling);
    }

    public static JobPositionDetailsPage using (WebDriver driver) {
        return new JobPositionDetailsPage(driver);
    }

    public JobPositionDetailsPage fillNameAndSurname(String value) {
        this.nameAndSurnameTextInput.sendKeys(value);
        getLogger().info("Fill name and surname.");
        return this;
    }

    public String getNWordsFromParagraph(int n) {
        String[] array = this.jobDescriptionParagraph.getText().split("\\s+");
        return Arrays.asList(array).stream()
                .limit(n)
                .map(word -> String.valueOf(word))
                .collect(Collectors.joining(" "));
    }

    public boolean isNameErrorLabelsPresent() {
        if (this.nameErrorLabel.isDisplayed())
            return true;
        else return false;
    }

    public String getFileButtonLabel() {
        return this.fileButtonLabel.getText();
    }

    public JobPositionDetailsPage verifyThatValidationMsgForNameDisappeared() {
        Assert.assertFalse(this.isNameErrorLabelsPresent());
        return this;
    }

    public JobPositionDetailsPage verifyUploadButton() {
        Assert.assertEquals(this.getFileButtonLabel(), "test_file.txt");
        return this;
    }

    @Step("In Email input enter smiling face emoji (Unicode U+1F60A)")
    public JobPositionDetailsPage fillEmailWithEmoji(String value, String browserType) {
        if (browserType.equalsIgnoreCase("chrome")) {
            String jsToExecute = "var elm = arguments[0], txt = arguments[1]; elm.value += txt;elm.dispatchEvent(new Event('change'));";
            ((JavascriptExecutor) getDriver()).executeScript(jsToExecute, this.emailTextInput, value);
            this.emailTextInput.sendKeys(Keys.RETURN);
        } else {
            this.emailTextInput.sendKeys(value);
        }
        getLogger().info("Filling email with emoji.");
        return this;
    }

    @Step("Click send button")
    public JobPositionDetailsPage clickSendButton() {
        wait.until(ExpectedConditions.elementToBeClickable(this.sendButton));
        ((JavascriptExecutor) getDriver()).executeScript("javascript:window.scrollBy(0,450)");
        this.sendButton.click();
        getLogger().info("Click send button.");
        return this;
    }

    @Step("Type a name in Vorname und Nachname input. Verify that validation message for this input disappeared")
    public JobPositionDetailsPage typeNameAndVerifyValidationMessage(String value) {
        this.fillNameAndSurname(value);
        this.verifyThatValidationMsgForNameDisappeared();
        getLogger().info("Filling name and checking validation message.");
        return this;
    }

    @Step("Read the first 10 words of job description on the left panel, and copy them to Bewerbungsschreiben input")
    public JobPositionDetailsPage fillDescription() {
        String value = this.getNWordsFromParagraph(10);
        this.descriptionTextInput.sendKeys(value);
        getLogger().info("Filling description.");
        return this;
    }

    @Step("Attach file with DATEIEN HOCHLADEN button")
    public JobPositionDetailsPage uploadFileAndVerifyButton() {
        String localDir = System.getProperty("user.dir");
        this.inputFile.sendKeys(localDir + "\\src\\test\\resources\\test_file.txt");
        this.verifyUploadButton();
        getLogger().info("Attaching file and checking the label on the button.");
        return this;
    }

    @Step("Check the checkbox for Datenschutzerklärung")
    public JobPositionDetailsPage checkAgreementCheckbox() {
        this.agreementCheckbox.click();
        getLogger().info("Checking agreement checkbox.");
        return this;
    }
}
