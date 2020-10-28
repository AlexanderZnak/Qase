package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import elements.Input;
import elements.Select;
import lombok.extern.log4j.Log4j2;
import models.TestRun;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class RunsPage extends BasePage {
    private static final String SEARCH_INPUT = ".form-control";
    private static final String SAVE_BUTTON = ".save-button";
    private static final String TEST_RUN_NAMES = ".defect-title";

    public RunsPage openPage() {
        String url = URL + "/run/QASE";
        log.info(String.format("Opening Test Runs page by url: %s", url));
        open(url);
        isPageOpened();
        return this;
    }

    public RunsPage isPageOpened() {
        log.info("Validating is Test Runs page opened");
        $(SEARCH_INPUT).shouldBe(Condition.visible);
        return this;
    }

    public RunsPage clickSave() {
        log.info("Clicking the button to save the changes");
        $(SAVE_BUTTON).shouldBe(Condition.visible).click();
        return this;
    }

    public RunsPage fillInTestRun(TestRun testRun) {
        log.info(String.format("Filling in test run: '%s'", testRun));
        new Input("Run title").clear();
        new Input("Run title").write(testRun.getRunTitle());
        new Input("Description").write(testRun.getDescription());
        new Select("Plan").select(testRun.getPlan());
        new Select("Environment").select(testRun.getEnvironment());
        new Select("Milestone").select(testRun.getMilestone());
        new Select("Default assignee").select(testRun.getDefaultAssignee());
        return this;
    }

    public boolean isTestRunExist(TestRun testRun) {
        log.info(String.format("Looking for test run: '%s'", testRun.getRunTitle()));
        ElementsCollection testRunName = $$(TEST_RUN_NAMES).shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));
        boolean isExisted = false;
        for (int i = 0; i < testRunName.size(); i++) {
            if (testRunName.get(i).text().equals(testRun.getRunTitle())) {
                isExisted = true;
            }
        }
        return isExisted;
    }

    public RunsPage clearTestRunInputFields(TestRun testRun) {
        log.info(String.format("Clearing input fields in test run: '%s'", testRun));
        new Input("Run title").clear();
        new Input("Description").clear();
        return this;
    }

    public RunsPage fillInEditingTestRun(TestRun testRun) {
        log.info(String.format("Filling in editing test run: '%s'", testRun));
        new Input("Run title").write(testRun.getRunTitle());
        new Input("Description").write(testRun.getDescription());
        new Select("Environment").select(testRun.getEnvironment());
        new Select("Milestone").select(testRun.getMilestone());
        return this;
    }

}
