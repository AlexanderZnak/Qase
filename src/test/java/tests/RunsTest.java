package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import models.TestRun;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class RunsTest extends BaseTest {
    Faker faker = new Faker();
    TestRun testRun;

    @Test(description = "Success CRUD for Test Run", retryAnalyzer = Retry.class)
    @Description("Validation of correct working to create, edit and delete Test Run")
    public void testRunShouldBeCreatedEditedDeleted() {
        testRun = TestRun.builder()
                .runTitle(faker.rickAndMorty().character())
                .description(faker.superhero().descriptor())
                .plan("Regression")
                .environment("Production")
                .milestone("Release 2.0")
                .defaultAssignee("1")
                .build();
        loginSteps
                .logIn(EMAIL, PASSWORD);
        runsSteps
                .createTestRun(testRun)
                .validateIsTestRunCreated(testRun)
                .validateDetailsOfCreatedTestRun(testRun);
        testRun = TestRun.builder()
                .runTitle(faker.rickAndMorty().character())
                .description(faker.superhero().descriptor())
                .plan("Regression")
                .environment("Production")
                .milestone("Release 1.0")
                .defaultAssignee("1")
                .build();
        runsSteps
                .editTestRun(testRun)
                .validateIsTestRunEdited(testRun)
                .validateDetailsOfEditedTestRun(testRun);
        runsSteps
                .deleteTestRun(testRun)
                .validateIsTestRunDeleted(testRun);
    }
}
