package tests;

import models.TestCase;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class RepositoryTest extends BaseTest {

    @Test
    public void newCaseShouldBeCreated() {
        TestCase testCase = TestCase.builder()
                .title("Example case")
                .status("Actual")
                .description("Some text")
                .suite("Authorization")
                .severity("Major")
                .priority("High")
                .type("Smoke")
                .milestone("Select...")
                .behavior("Positive")
                .automationStatus("Automated")
                .preConditions("Open page")
                .postConditions("Delete data")
                .build();

        loginSteps
                .logIn(EMAIL, PASSWORD);
        repositorySteps
                .createNewTestCase("QASE", testCase)
                .validateIsTestCaseCreated(testCase, "QASE");
//                .validateDetailsOfCreatedTestCase();

    }
}
