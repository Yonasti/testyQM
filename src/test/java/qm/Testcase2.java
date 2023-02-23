package qm;

import org.testng.annotations.Test;
import page.objects.TopMenuPage;

public class Testcase2 extends BaseTest {

    @Test
    public void Test2() throws InterruptedException {
        TopMenuPage.using(getDriver())
                .goToHomePage()
                .verifyThatEngVersionIdDisplayed()
                .hoverOverAboutUsSection()
                .chooseEventLink()
                .typeSpecifiedYearValueAndClickFind()
                .clickOnOnwardsAndVerifyCalendarIsThere()
                .navigateToDesiredDate()
                .verifyThatItHasSpecifiedResult();
    }
}