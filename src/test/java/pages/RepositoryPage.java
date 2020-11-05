package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.Input;
import elements.InputTitle;
import elements.Select;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import models.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class RepositoryPage extends BasePage {
    private static final String TITLE_BUTTON = "[title='%s']";
    private static final String CASE_NAME = ".case-row-title";
    private static final String ACCEPT_BUTTON = "//*[contains(@class, 'modal-footer')]//*[contains(text(), '%s')]";
    private static final String SUITE_INPUTS = "//*[contains(text(),'%s')]/parent::div//*[contains(@class, 'CodeMirror-line')]";
    private static final By CREATE_SUITE_BUTTON = By.id("saveButton");
    private static final By SELECT_SUITES = By.id("selectSuite");
    private static final String SUITE_NAME = ".suite-header";
    private static final String SUITE_EDIT = "//*[contains(text(),'%s')]//*[contains(@class, 'fa-pencil-alt')]";
    private static final String SELECT_OPTION_TO_GET_TEXT = ".filter-option";
    private static final String SUITE_INPUTS_TO_GET_TEXT = "//*[contains(text(),'%s')]/parent::div//*[contains(@class, 'CodeMirror-code')]";
    private static final By SAVE_CHANGES_BUTTON = By.id("saveButton");
    private static final String SUITE_DELETE = "//*[contains(text(),'%s')]//*[contains(@class, 'fa-trash')]";

    public RepositoryPage openPage(String projectCode) {
        String projectUrl = URL + "/project/" + projectCode;
        log.info(String.format("Opening repository page by url: %s", projectUrl));
        open(projectUrl);
        isPageOpened();
        return this;
    }

    public RepositoryPage isPageOpened() {
        log.info("Validating is repository page opened");
        $(String.format(TITLE_BUTTON, "Import")).shouldBe(Condition.visible);
        return this;
    }

    public boolean findTestCaseByName(String caseName) {
        log.info(String.format("Looking for test case: %s from list", caseName));
        ElementsCollection cases = $$(CASE_NAME).shouldHave(CollectionCondition.sizeGreaterThan(1), 15000);
        log.info(String.format("Case amount: %d", cases.size()));
        boolean name = false;
        for (int i = 0; i < cases.size(); i++) {
            if (caseName.equals(cases.get(i).text())) {
                name = true;
            }
        }
        return name;
    }

    public RepositoryPage fillInTestCase(TestCase testCase) {
        log.info(String.format("Filling in test case: '%s'", testCase));
        new InputTitle("Title").write(testCase.getTitle());
        new Input("Description").write(testCase.getDescription());
        new Input("Pre-conditions").write(testCase.getPreConditions());
        new Input("Post-conditions").write(testCase.getPostConditions());
        new Select("Status").select(testCase.getStatus());
        new Select("Suite").select(testCase.getSuite());
        new Select("Severity").select(testCase.getSeverity());
        new Select("Priority").select(testCase.getPriority());
        new Select("Type").select(testCase.getType());
        new Select("Milestone").select(testCase.getMilestone());
        new Select("Behavior").select(testCase.getBehavior());
        new Select("Automation status").select(testCase.getAutomationStatus());
        return this;
    }

    public RepositoryPage clickOnCreatedCase(String caseName) {
        log.info(String.format("Looking for test case: %s from list to click", caseName));
        ElementsCollection cases = $$(CASE_NAME).shouldHave(CollectionCondition.sizeGreaterThan(1), 15000);
        for (int i = 0; i < cases.size(); i++) {
            if (caseName.equals(cases.get(i).text())) {
                cases.get(i).click();
            }
        }
        return this;
    }

    public RepositoryPage clearInputFields() {
        log.info("Clearing input fields");
        new InputTitle("Title").clear();
        new Input("Description").clear();
        new Input("Pre-conditions").clear();
        new Input("Post-conditions").clear();
        return this;
    }

    public RepositoryPage clickEditTestCase() {
        log.info("Clicking the button Edit  test case");
        $(String.format(TITLE_BUTTON, "Edit case")).waitUntil(Condition.appear, 15000).click();
        return this;
    }

    public boolean compareAllDetailsInTestCase(TestCase testCase) {
        log.info("Expected case: " + testCase);
        boolean name = false;
        TestCase actualCase = TestCase.builder()
                .title(new InputTitle("Title").getText())
                .description(new Input("Description").getText())
                .status(new Select("Status").getText())
                .suite(new Select("Suite").getText())
                .severity(new Select("Severity").getText())
                .priority(new Select("Priority").getText())
                .type(new Select("Type").getText())
                .milestone(new Select("Milestone").getText())
                .behavior(new Select("Behavior").getText())
                .preConditions(new Input("Pre-conditions").getText())
                .postConditions(new Input("Post-conditions").getText())
                .automationStatus(new Select("Automation status").getText()).build();
        log.info("Actual case: " + actualCase);
        if (actualCase.equals(testCase))
            name = true;

        return name;
    }

    public RepositoryPage acceptToDeleteTestCase() {
        log.info("Accepting to delete test case");
        $x(String.format(ACCEPT_BUTTON, "Delete")).shouldBe(Condition.visible).click();
        return this;
    }

    public RepositoryPage clickDeleteTestCase() {
        log.info("Clicking the button Delete case");
        $(String.format(TITLE_BUTTON, "Delete case")).waitUntil(Condition.appear, 15000).click();
        return this;
    }

    public RepositoryPage fillInTestSuite(TestSuite testSuite) {
        log.info(String.format("Filling in test suite: '%s'", testSuite));
        new InputTitle("Suite name").write(testSuite.getSuiteName());
        $(SELECT_SUITES).selectOption(testSuite.getParentSuite());
        actions().moveToElement($x(String.format(SUITE_INPUTS, "Description"))).click().sendKeys(testSuite.getDescription()).perform();
        actions().moveToElement($x(String.format(SUITE_INPUTS, "Preconditions"))).click().sendKeys(testSuite.getPreconditions()).perform();
        return this;
    }

    public RepositoryPage clickCreateSuite() {
        log.info("Clicking the button Create suite");
        $(CREATE_SUITE_BUTTON).shouldBe(Condition.visible).click();
        return this;
    }

    public RepositoryPage clickOnEditSuite(String suiteName) {
        log.info(String.format("Clicking the button edit that belongs to suite: '%s'", suiteName));
        $x(String.format(SUITE_EDIT, suiteName)).hover().click();
        return this;
    }

    public boolean compareAllDetailsInTestSuite(TestSuite testSuite) {
        log.info("Expected suite: " + testSuite);
        boolean isDetails = false;
        TestSuite actualTestSuite = TestSuite.builder()
                .suiteName(new InputTitle("Suite name").getText())
                .parentSuite($(SELECT_OPTION_TO_GET_TEXT).text())
                .description($x(String.format(SUITE_INPUTS_TO_GET_TEXT, "Description")).text())
                .preconditions($x(String.format(SUITE_INPUTS_TO_GET_TEXT, "Preconditions")).text())
                .build();
        log.info("Actual suite: " + actualTestSuite);
        if (actualTestSuite.equals(testSuite))
            isDetails = true;

        return isDetails;
    }

    public boolean findTestSuiteByName(String suiteName) {
        log.info(String.format("Looking for test suite: %s from list", suiteName));
        ElementsCollection suites = $$(SUITE_NAME).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1), 15000);
        log.info(String.format("Case amount: %d", suites.size()));
        boolean name = false;
        for (int i = 0; i < suites.size(); i++) {
            if (suiteName.equals(suites.get(i).text())) {
                name = true;
            }
        }
        return name;
    }

    public RepositoryPage clearSuiteInputFields() {
        log.info("Clearing suite input fields");
        new InputTitle("Suite name").clear();
        clearWebField($x(String.format(SUITE_INPUTS_TO_GET_TEXT, "Description")));
        clearWebField($x(String.format(SUITE_INPUTS, "Preconditions")));
        return this;
    }

    public RepositoryPage clearWebField(SelenideElement element) {
        log.info(String.format("Clearing field with element: '%s', using actions", element));
        while (!element.text().equals("")) {
            actions().moveToElement(element).click().sendKeys(Keys.BACK_SPACE).perform();
        }
        return this;
    }

    public RepositoryPage clickSaveChanges() {
        log.info("Clicking the button Save changes");
        $(SAVE_CHANGES_BUTTON).shouldBe(Condition.visible).click();
        return this;
    }

    public RepositoryPage clickOnDeleteSuite(String suiteName) {
        log.info(String.format("Clicking the button delete suite that belongs to suite: '%s'", suiteName));
        $x(String.format(SUITE_DELETE, suiteName)).hover().click();
        return this;
    }

    public RepositoryPage acceptToDeleteTestSuite() {
        log.info("Accepting to delete test suite");
        $x(String.format(ACCEPT_BUTTON, "Delete suite")).shouldBe(Condition.visible).click();
        return this;
    }
}
