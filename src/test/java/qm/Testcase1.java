package qm;

import org.testng.annotations.Test;
import page.objects.AutomatedTestPage;
import page.objects.TopMenuPage;

public class Testcase1 extends BaseTest {

    @Test
    public void Test1(){
        String url = TopMenuPage.using(getDriver())
                .goToHomePage()
                .hoverOverLanguageAndChooseGerman()
                .hoverOverPortfolioAndChooseAT("portfolio")
                .verifyContacUsButton()
                .getPageUrl();
        TopMenuPage.using(getDriver())
                .goToHomePage()
                .verifyThatEngVersionIdDisplayed()
                .hoverOverPortfolioAndChooseAT("services");
        TopMenuPage.using(getDriver())
                .hoverOverLanguageAndChooseGerman();
        AutomatedTestPage.using(getDriver())
                .verifyTheLink(url);
    }
}