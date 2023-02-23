package qm;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.objects.JobPositionPage;

public class Testcase3 extends BaseTest {


    @Test
    @Parameters("browserType")
    public void Test3(String browserType) throws InterruptedException {
        JobPositionPage.using(getDriver())
                .goToPage()
                .verifyIThereIsOneOfferAtLeast()
                .firstJobPositionGoToDetails()
                .clickSendButton()
                .typeNameAndVerifyValidationMessage("Markus Test")
                .fillEmailWithEmoji("\uD83D\uDE0A", browserType)
                .fillDescription()
                .uploadFileAndVerifyButton()
                .checkAgreementCheckbox();
    }
}