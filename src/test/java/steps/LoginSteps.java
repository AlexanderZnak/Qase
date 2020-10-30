package steps;

import lombok.extern.log4j.Log4j2;
import pages.LoginPage;
import pages.ProjectsPage;

import static org.testng.Assert.assertEquals;

@Log4j2
public class LoginSteps {
    LoginPage loginPage;
    ProjectsPage projectsPage;

    public LoginSteps() {
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
    }

    public LoginSteps logIn(String email, String password) {
        log.info(String.format("Logging in with email: '%s', password: '%s'", email, password));
        loginPage
                .openPage()
                .fillInEmailAndPassword(email, password)
                .clickLogin();
        return this;
    }

    public LoginSteps validateIsPageOpened() {
        log.info("Validating is projects page opened");
        projectsPage.isPageOpened();
        return this;
    }

    public LoginSteps sendEmailToResetPassword(String email) {
        log.info(String.format("Resetting password sending email: '%s'", email));
        loginPage
                .openPage()
                .clickByXpathText("Forgot Your Password?");
        loginPage
                .sendEmailToResetPassword(email);
        return this;
    }

    public LoginSteps validateIsEmailSent(String expectedMessage) {
        log.info(String.format("Validating is message: '%s' appeared", expectedMessage));
        assertEquals(loginPage.getMessageAboutResettingPassword(), expectedMessage);
        return this;
    }
}
