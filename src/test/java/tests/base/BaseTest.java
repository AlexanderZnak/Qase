package tests.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.*;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

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
        Configuration.timeout = 10000;
        Configuration.startMaximized = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = true; //#configuration to run tests without browser
        loginSteps = new LoginSteps();
        projectsSteps = new ProjectsSteps();
        repositorySteps = new RepositorySteps();
        plansSteps = new PlansSteps();
        runsSteps = new RunsSteps();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        getWebDriver().quit();
    }
}
