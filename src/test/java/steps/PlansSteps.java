package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.PlansPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class PlansSteps {
    PlansPage plansPage;

    public PlansSteps() {
        plansPage = new PlansPage();
    }

    @Step("Creating test plan with name: '{planName}', description: '{description}', suite: '{suiteName}'")
    public PlansSteps createTestPlan(String planName, String description, String suiteName) {
        log.info(String.format("Creating test plan with name: '%s', description: '%s', suite: '%s'", planName, description, suiteName));
        plansPage
                .openPage()
                .clickCreatePlan()
                .fillInTestPlanDetails(planName, description)
                .selectSuite(suiteName)
                .clickDone()
                .clickSave();
        return this;
    }

    @Step("Validating is Test Plan with name: '{planName}' and description: '{planDescription}' exists")
    public PlansSteps validateIsTestPlanCreated(String planName, String planDescription) {
        log.info(String.format("Validating is Test Plan with name: '%s' and description: '%s' exists", planName, planDescription));
        boolean isExist = plansPage
                .openPage()
                .isTestPlanExist(planName, planDescription);
        assertTrue(isExist);
        return this;
    }

    @Step("Editing test plan: '{planName}' on the new data. Name: '{newPlanName}', description: '{newPlanDescription}', suite: '{suiteName}'")
    public PlansSteps editTestPlan(String planName, String newPlanName, String newPlanDescription, String suiteName) {
        log.info(String.format("Editing test plan: '%s' on the new data. Name: '%s', description: '%s', suite: '%s'", planName, newPlanName, newPlanDescription, suiteName));
        plansPage
                .selectOption(planName, "Edit");
        plansPage
                .clearInputFields()
                .fillInTestPlanDetails(newPlanName, newPlanDescription)
                .selectSuite(suiteName)
                .clickDone()
                .clickSave();
        return this;
    }

    @Step("Validating is Test Plan with name: '{planName}' and description: '{planDescription}' exists")
    public PlansSteps validateIsTestPlanEdited(String planName, String planDescription) {
        validateIsTestPlanCreated(planName, planDescription);
        return this;
    }

    @Step("Deleting test plan: '{planName}'")
    public PlansSteps deleteTestPlan(String planName) {
        log.info(String.format("Deleting test plan: '%s'", planName));
        plansPage
                .openPage()
                .selectOption(planName, "Delete")
                .clickByXpathText(" Delete plan");
        return this;
    }

    @Step("Validating is Test Plan with name: '{planName}' and description: '{planDescription}' doesn't exists")
    public PlansSteps validateIsTestPlanDeleted(String planName, String planDescription) {
        log.info(String.format("Validating is Test Plan with name: '%s' and description: '%s' doesn't exists", planName, planDescription));
        boolean isExist = plansPage
                .openPage()
                .isTestPlanExist(planName, planDescription);
        assertFalse(isExist);

        return this;
    }
}
