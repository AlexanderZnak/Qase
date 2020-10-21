package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {
    public static final By EMAIL_INPUT = By.id("inputEmail");
    public static final By PASSWORD_INPUT = By.id("inputPassword");
    public static final By LOGIN_BUTTON = By.id("btnLogin");
    public static final String SUCCESS_ALERT = ".alert-success";
    private static final String URL = "https://app.qase.io/login";

    public LoginPage openPage() {
        open(URL);
        isPageOpened();
        return this;
    }

    public LoginPage isPageOpened() {
        $(LOGIN_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public LoginPage fillInEmailAndPassword(String email, String password) {
        $(EMAIL_INPUT).sendKeys(email);
        $(PASSWORD_INPUT).sendKeys(password);
        return this;
    }

    public ProjectsPage clickLogin() {
        $(LOGIN_BUTTON).click();
        return new ProjectsPage();
    }


    public LoginPage sendEmailToResetPassword(String email) {
        $(EMAIL_INPUT).sendKeys(email);
        clickByXpathText("Send Password Reset Link");
        return this;
    }

    public String getMessageAboutResettingPassword() {
        return $(SUCCESS_ALERT).shouldBe(Condition.visible).text();
    }


}
