package tests.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.LoginSteps;
import steps.ProjectsSteps;
import steps.RepositorySteps;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    public static final String EMAIL = "frolovruslan1990@gmail.com";
    public static final String PASSWORD = "123guki1303";
    public LoginSteps loginSteps;
    public ProjectsSteps projectsSteps;
    public RepositorySteps repositorySteps;

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;
        //Configuration.clickViaJs = true; //TODO doesn't work with such click, so let's remove
        Configuration.startMaximized = true;
        Configuration.headless = true; //#configuration to run tests without browser
        loginSteps = new LoginSteps();
        projectsSteps = new ProjectsSteps();
        repositorySteps = new RepositorySteps();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        getWebDriver().quit();
    }
}
