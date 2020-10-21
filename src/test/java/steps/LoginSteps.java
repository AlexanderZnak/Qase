package steps;

import pages.LoginPage;
import pages.ProjectsPage;

import static org.testng.Assert.assertEquals;

public class LoginSteps {
    LoginPage loginPage;
    ProjectsPage projectsPage;

    public LoginSteps() {
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
    }

    public LoginSteps logIn(String email, String password) {
        loginPage
                .openPage()
                .fillInEmailAndPassword(email, password)
                .clickLogin();
        return this;
    }

    public LoginSteps validateIsPageOpened() {
        projectsPage.isPageOpened();
        return this;
    }

    public LoginSteps sendEmailToResetPassword(String email) {
        loginPage
                .openPage()
                .clickByXpathText("Forgot Your Password?");
        loginPage
                .sendEmailToResetPassword(email);
        return this;
    }

    public LoginSteps validateIsEmailSent(String expectedMessage) {
        assertEquals(loginPage.getMessageAboutResettingPassword(), expectedMessage);
        return this;
    }
}
