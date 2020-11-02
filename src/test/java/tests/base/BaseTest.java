package tests.base;

import com.codeborne.selenide.Configuration;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.*;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    public static final String EMAIL = System.getenv("email");
    public static final String PASSWORD = System.getenv("password");
    public LoginSteps loginSteps;
    public ProjectsSteps projectsSteps;
    public RepositorySteps repositorySteps;
    public PlansSteps plansSteps;
    public RunsSteps runsSteps;

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 20000;
        Configuration.startMaximized = true;
        Configuration.pageLoadStrategy = "eager";
        loginSteps = new LoginSteps();
        projectsSteps = new ProjectsSteps();
        repositorySteps = new RepositorySteps();
        plansSteps = new PlansSteps();
        runsSteps = new RunsSteps();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        try {
            getWebDriver().quit();
        } catch (IllegalStateException ex) {
            log.warn("WebDriver is not opened on attempt to close it");
            log.warn(ex.getLocalizedMessage());
        }
    }

}
