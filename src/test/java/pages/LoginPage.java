package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage extends BasePage {
    public static final By EMAIL_INPUT = By.id("inputEmail");
    public static final By PASSWORD_INPUT = By.id("inputPassword");
    public static final By LOGIN_BUTTON = By.id("btnLogin");
    public static final String SUCCESS_ALERT = ".alert-success";

    public LoginPage openPage() {
        String url = URL + "/login";
        log.info(String.format("Opening login page by url: %s", url));
        open(url);
        isPageOpened();
        return this;
    }

    public LoginPage isPageOpened() {
        log.info("Validating is login page opened");
        $(LOGIN_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public LoginPage fillInEmailAndPassword(String email, String password) {
        log.info(String.format("Filling in email:'%s', password: '%s'", email, password));
        $(EMAIL_INPUT).shouldBe(Condition.visible).sendKeys(email);
        $(PASSWORD_INPUT).shouldBe(Condition.visible).sendKeys(password);
        return this;
    }

    public ProjectsPage clickLogin() {
        log.info("Clicking the button Login");
        $(LOGIN_BUTTON).shouldBe(Condition.visible).click();
        return new ProjectsPage();
    }


    public LoginPage sendEmailToResetPassword(String email) {
        log.info(String.format("Sending email: '%s' and clicking to reset password", email));
        $(EMAIL_INPUT).shouldBe(Condition.visible).sendKeys(email);
        clickByXpathText("Send Password Reset Link");
        return this;
    }

    public String getMessageAboutResettingPassword() {
        log.info("Getting message about resetting password");
        return $(SUCCESS_ALERT).shouldBe(Condition.visible).text();
    }


}
