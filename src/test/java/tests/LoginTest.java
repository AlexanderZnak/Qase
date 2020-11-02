package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class LoginTest extends BaseTest {

    @Test(description = "Success login", retryAnalyzer = Retry.class)
    @Description("Validation of correct working the login with the valid data")
    public void projectsPageShouldBeOpenedAfterLogin() {
        loginSteps
                .logIn(EMAIL, PASSWORD)
                .validateIsPageOpened();
    }

    @Test(description = "Success reset the password", retryAnalyzer = Retry.class)
    @Description("Validation of correct working to reset forgotten password")
    public void resetForgottenPassword() {
        loginSteps
                .sendEmailToResetPassword(EMAIL)
                .validateIsEmailSent("We have e-mailed your password reset link!");
    }
}
