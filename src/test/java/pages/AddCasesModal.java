package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class AddCasesModal extends BasePage {
    private static final String SUITE_CHECKBOX = "//*[text()='%s']/parent::div//div[contains(@class, 'checkbox')]";
    private static final String ACCEPT_BUTTON = "//*[contains(@class, 'modal-footer')]//*[contains(text(), '%s')]";

    public AddCasesModal selectSuite(String suiteName) {
        log.info(String.format("Selecting suite: %s", suiteName));
        $x(String.format(SUITE_CHECKBOX, suiteName)).shouldBe(Condition.enabled).click();
        return this;
    }

    public PlansPage clickDone() {
        log.info("Clicking the button Done");
        $x(String.format(ACCEPT_BUTTON, "Done")).shouldBe(Condition.visible).click();
        return new PlansPage();
    }

    public AddCasesModal uncheckSuite(String suiteName) {
        log.info(String.format("Unchecking suite: %s", suiteName));
        $x(String.format(SUITE_CHECKBOX, suiteName)).shouldBe(Condition.checked).click();
        return this;
    }
}
