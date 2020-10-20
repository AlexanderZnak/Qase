package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class LoginTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void projectsPageShouldBeOpenedAfterLogin() {
        loginSteps
                .logIn(EMAIL, PASSWORD)
                .validateIsPageOpened();
    }

    @Test(retryAnalyzer = Retry.class)
    public void resetForgotPassword() {
        loginSteps
                .sendEmailToResetPassword(EMAIL)
                .validateIsEmailSent("We have e-mailed your password reset link!");
    }
}
