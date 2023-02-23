package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Months;
import java.util.List;

public class EventsPage extends BasePage{

    private WebDriverWait wait;
    private int timeout = 5;
    private long polling = 500;

    @FindBy(id = "tribe-events-events-bar-keyword")
    private WebElement searchingBar;

    @FindBy(name = "submit-bar")
    private WebElement findEventsButton;

    @FindBy(css = "#main-content > div > div > div > div > div > div > div > div > div > header > div > div > button > svg")
    private WebElement nowOnwardsArrow;

    @FindBy(css = "#main-content > div > div > div> div > div > div > div > div > div > header > div.tribe-events-c-top-bar.tribe-events-header__top-bar > div > div >div")
    private WebElement datePicker;

    @FindBy(css = "div.datepicker-days > table > thead > tr th.datepicker-switch")
    private WebElement datepickerDaysSwitch;

    @FindBy(css = "div.datepicker-months > table > thead > tr th.datepicker-switch")
    private WebElement datepickerMonthsSwitch;

    @FindAll(@FindBy(css = "div.datepicker-years > table > tbody > tr > td > span"))
    private List<WebElement> setYear;

    @FindAll(@FindBy(css = "div.datepicker-months > table > tbody > tr > td > span"))
    private List<WebElement>  setMonth;

    @FindAll(@FindBy(css = "div.datepicker-days > table > tbody > tr:nth-child(5) > td"))
    private List<WebElement>  setDay;

    @FindBy(className = "tribe-events-calendar-list__event-title-link")
    private WebElement resultTitle;

    @FindBy(className = "tribe-events-calendar-list__event-date-tag-datetime")
    private WebElement resultDate;

    public EventsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(getDriver(), 15, polling);
    }

    public EventsPage typeValueToSearch(String value) {
         this.searchingBar.sendKeys(value);
         return this;
    }

    public EventsPage clickFindEvents() throws InterruptedException {
        this.findEventsButton.click();
        getLogger().info("Click find events button.");
        Thread.sleep(5000);
        return this;
    }

    public EventsPage clickNowOnwards() {
        wait.until(ExpectedConditions.visibilityOf(this.nowOnwardsArrow));
        this.nowOnwardsArrow.click();
        getLogger().info("Click now onwards.");
        return this;
    }

    public boolean isDataPickerDisplayed() {
       return this.datePicker.isDisplayed();
    }

    public EventsPage goToYearPicking() {
        this.datepickerDaysSwitch.click();
        this.datepickerMonthsSwitch.click();
        getLogger().info("Go to year choosing.");
        return this;
    }

    public EventsPage chooseDate(String yyyy, Months months, String dd) throws InterruptedException {
        for (WebElement year : this.setYear) {
            if (year.getText().equals(yyyy)) {
                year.click();
                break;
            }
        }

        for (WebElement month : this.setMonth) {
            if (month.getText().equals(months.toString())) {
                ((JavascriptExecutor) getDriver()).executeScript("javascript:window.scrollBy(0,350)");
                month.click();
                break;
            }
        }

        for (WebElement day : this.setDay) {
            if (day.getText().equals(dd)) {
                day.click();
                break;
            }
        }
        getLogger().info("Choose the date.");
        return this;
    }

    public String getSearchingResultTitle() {
        wait.until(ExpectedConditions.visibilityOf(this.resultTitle));
        return this.resultTitle.getText();
    }

    public String getSearchingResultDate() {
        return this.resultDate.getAttribute("datetime");
    }


    public EventsPage verifyThatCalenderIsDisplayed() {
        Assert.assertTrue(this.isDataPickerDisplayed());
        return this;
    }


    @Step("Verify that you have 1 search result")
    public EventsPage verifyThatItHasSpecifiedResult() {
        Assert.assertTrue(this.getSearchingResultTitle().contains("ICSTTP 2021"));
        Assert.assertTrue(this.getSearchingResultDate().equals("2022-01-04"));
        getLogger().info("Checking if there is 1 result at least.");
        return this;
    }

    @Step("In the Search for events bar, type 2021 and click Find Events")
    public EventsPage typeSpecifiedYearValueAndClickFind() throws InterruptedException {
        this.typeValueToSearch("2021");
        this.clickFindEvents();
        getLogger().info("Searching for events in 2021.");
        return this;
    }
    @Step("Click down arrow next to Now onwards. Verify if calendar is displayed")
    public EventsPage clickOnOnwardsAndVerifyCalendarIsThere() {
        this.clickNowOnwards();
        this.verifyThatCalenderIsDisplayed();
        getLogger().info("Checking if calender is there.");
        return this;
    }

    @Step("Navigate through the calendar to December 2021 and select day 31.")
    public EventsPage navigateToDesiredDate() throws InterruptedException {
        this.goToYearPicking();
        this.chooseDate("2021", Months.Dec, "31");
        getLogger().info("Navigating to specified date.");
        return this;
    }
}
