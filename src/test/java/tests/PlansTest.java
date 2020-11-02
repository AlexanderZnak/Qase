package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class PlansTest extends BaseTest {
    Faker faker = new Faker();

    @Test(description = "Success CRUD for Test Plan", retryAnalyzer = Retry.class)
    @Description("Validation of correct working to create, edit and delete Test Plan")
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
