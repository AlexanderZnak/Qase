package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class PlansTest extends BaseTest {
    Faker faker = new Faker();

    @Test(retryAnalyzer = Retry.class)
    public void testPlanShouldBeCreatedEditedDeleted() {
        String planName = faker.funnyName().name();
        String planDescription = faker.gameOfThrones().quote();
        String editPlanName = faker.funnyName().name();
        String editPlanDescription = faker.gameOfThrones().quote();
        loginSteps
                .logIn(EMAIL, PASSWORD);
        plansSteps
                .createTestPlan(planName, planDescription, "Projects")
                .validateIsTestPlanCreated(planName, planDescription);
        plansSteps
                .editTestPlan(planName, editPlanName, editPlanDescription, "Team")
                .validateIsTestPlanEdited(editPlanName, editPlanDescription);
        plansSteps
                .deleteTestPlan(editPlanName)
                .validateIsTestPlanDeleted(editPlanName, editPlanDescription);
    }
}
