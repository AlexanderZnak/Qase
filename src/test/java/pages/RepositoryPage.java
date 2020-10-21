package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import elements.Input;
import elements.Select;
import lombok.extern.log4j.Log4j2;
import models.TestCase;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class RepositoryPage extends BasePage {
    private static final String URL = "https://app.qase.io/project/%s";
    private static final String IMPORT_BUTTON = "[title='Import']";
    private static final String CASE_NAME = ".case-row-title";


    public RepositoryPage openPage(String projectCode) {
        open(String.format(URL, projectCode));
        isPageOpened();
        return this;
    }

    public RepositoryPage isPageOpened() {
        $(IMPORT_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public boolean findTestCaseByName(String caseName) {
        ElementsCollection cases = $$(CASE_NAME);
        log.info(String.format("Looking for test case: %s from list", caseName));
        boolean name = false;
        for (int i = 0; i < cases.size(); i++) {
            if (caseName.equals(cases.get(i).text())) {
                name = true;
            }
        }
        return name;
    }

    public RepositoryPage fillInTestCase(TestCase testCase) {
        new Input("Title").write(testCase.getTitle());
        new Input("Description").write(testCase.getDescription());
        new Select("Status").select(testCase.getStatus());
        new Select("Suite").select(testCase.getSuite());
        new Select("Severity").select(testCase.getSeverity());
        new Select("Priority").select(testCase.getPriority());
        new Select("Type").select(testCase.getType());
        new Select("Milestone").select(testCase.getMilestone());
        new Select("Behavior").select(testCase.getBehavior());
        new Select("Automation status").select(testCase.getAutomationStatus());
        new Input("Pre-conditions").write(testCase.getPreConditions());
        new Input("Post-conditions").write(testCase.getPostConditions());
        return this;
    }

    public RepositoryPage clickCase() {
        clickByXpathText(" Case");
        return this;
    }

    public RepositoryPage clickSave() {
        clickByXpathText("Save");
        return this;
    }
}
