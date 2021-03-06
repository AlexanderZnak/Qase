package steps;

import elements.Input;
import elements.InputTitle;
import elements.Select;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestRun;
import pages.RunsPage;

import static org.testng.Assert.*;

@Log4j2
public class RunsSteps {
    RunsPage runsPage;

    public RunsSteps() {
        runsPage = new RunsPage();
    }

    @Step("Creating test run: '{testRun}'")
    public RunsSteps createTestRun(TestRun testRun) {
        log.info(String.format("Creating test run: '%s'", testRun));
        runsPage
                .openPage()
                .clickByXpathText("Start new test run");
        runsPage
                .fillInTestRun(testRun)
                .clickSave();
        return this;
    }

    @Step("Validating is Test run: '{testRun}' exists")
    public RunsSteps validateIsTestRunCreated(TestRun testRun) {
        log.info(String.format("Validating is Test run: '%s' exists", testRun));
        boolean isExist = runsPage
                .openPage()
                .isTestRunExist(testRun);
        assertTrue(isExist);
        return this;
    }

    @Step("Validating details of expected test run: '{expectedTestRun}'")
    public RunsSteps validateDetailsOfCreatedTestRun(TestRun expectedTestRun) {
        log.info(String.format("Validating details of expected test run: %s", expectedTestRun));
        expectedTestRun.setPlan("");
        expectedTestRun.setDefaultAssignee("");
        runsPage
                .openPage()
                .selectOption(expectedTestRun.getRunTitle(), "Edit run");
        TestRun actualTestRun = TestRun.builder()
                .runTitle(new InputTitle("Run title").getText())
                .description(new Input("Description").getText())
                .environment(new Select("Environment").getText())
                .milestone(new Select("Milestone").getText())
                .plan("")
                .defaultAssignee("")
                .build();
        log.info(String.format("Validating details of actual test run: %s", actualTestRun));
        assertEquals(actualTestRun, expectedTestRun);
        return this;
    }

    @Step("Editing test run: '{testRun}'")
    public RunsSteps editTestRun(TestRun testRun) {
        log.info(String.format("Editing test run: '%s'", testRun));
        runsPage
                .clearTestRunInputFields(testRun)
                .fillInEditingTestRun(testRun)
                .clickSave();
        return this;
    }

    @Step("Validating is Test run: '{testRun}' exists")
    public RunsSteps validateIsTestRunEdited(TestRun testRun) {
        validateIsTestRunCreated(testRun);
        return this;
    }

    @Step("Validating details of expected test run: '{testRun}'")
    public RunsSteps validateDetailsOfEditedTestRun(TestRun testRun) {
        validateDetailsOfCreatedTestRun(testRun);
        return this;
    }

    @Step("Deleting test run: '{testRun}'")
    public RunsSteps deleteTestRun(TestRun testRun) {
        log.info(String.format("Deleting test run: '%s'", testRun.getRunTitle()));
        runsPage
                .openPage()
                .selectOption(testRun.getRunTitle(), "Delete")
                .clickByXpathText(" Delete run");
        return this;
    }

    @Step("Validating is Test run: '{testRun}' doesn't exists")
    public RunsSteps validateIsTestRunDeleted(TestRun testRun) {
        log.info(String.format("Validating is Test run: '%s' doesn't exists", testRun.getRunTitle()));
        boolean isExist = runsPage
                .openPage()
                .isTestRunExist(testRun);
        assertFalse(isExist);
        return this;
    }
}
