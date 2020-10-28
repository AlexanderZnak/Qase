package pages;

import com.codeborne.selenide.Condition;
import elements.Input;
import elements.InputTitle;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class PlansPage extends BasePage {
    private static final By CREATE_TEST_PLAN = By.id("createButton");
    private static final String SAVE_BUTTON = ".save-button";
    private static final String TEST_PLAN_NAME = ".defect-title";
    private static final String TEST_PLAN_DESCRIPTION = ".project-cases";


    public PlansPage openPage() {
        String url = URL + "/plan/QASE";
        log.info(String.format("Opening Test Plans page by url: %s", url));
        open(url);
        isPageOpened();
        return this;
    }

    public PlansPage isPageOpened() {
        log.info("Validating is Test Plans page opened");
        $(CREATE_TEST_PLAN).shouldBe(Condition.visible);
        return this;
    }

    public PlansPage clickCreatePlan() {
        log.info("Clicking the button Create plan");
        $(CREATE_TEST_PLAN).shouldBe(Condition.visible).click();
        return this;
    }

    public AddCasesModal fillInTestPlanDetails(String planName, String planDescription) {
        log.info(String.format("Filling in test plan name: '%s', description: '%s' and adding test cases", planName, planDescription));
        new InputTitle("Title").write(planName);
        new Input(("Description")).write(planDescription);
        clickByXpathText(" Add cases");
        return new AddCasesModal();
    }

    public boolean isTestPlanExist(String planName, String planDescription) {
        log.info(String.format("Looking for test plan with name: '%s', description: '%s'", planName, planDescription));
        boolean isExist = false;
        if ($(TEST_PLAN_NAME).text().equals(planName) & $(TEST_PLAN_DESCRIPTION).text().equals(planDescription)) {
            isExist = true;
        }
        return isExist;
    }

    public PlansPage clearInputFields() {
        log.info("Clearing test plan input fields");
        new InputTitle("Title").clear();
        new Input(("Description")).clear();
        return this;
    }

    public PlansPage clickSave() {
        log.info("Clicking the button Save");
        $(SAVE_BUTTON).shouldBe(Condition.visible).click();
        return this;
    }


}
