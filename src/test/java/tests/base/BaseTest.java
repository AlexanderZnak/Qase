package tests.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.LoginSteps;
import steps.ProjectsSteps;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    public static final String EMAIL = System.getProperty("email");
    public static final String PASSWORD = System.getProperty("password");
    public LoginSteps loginSteps;
    public ProjectsSteps projectsSteps;

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
        Configuration.clickViaJs = true;
        Configuration.startMaximized = true;
//        Configuration.headless = true; //#configuration to run tests without browser
        loginSteps = new LoginSteps();
        projectsSteps = new ProjectsSteps();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        getWebDriver().quit();
    }
}
