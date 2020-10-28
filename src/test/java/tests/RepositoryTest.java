package tests;

import com.github.javafaker.Faker;
import models.TestCase;
import models.TestSuite;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class RepositoryTest extends BaseTest {
    Faker faker = new Faker();
    TestCase testCase;
    TestSuite testSuite;

    @Test(retryAnalyzer = Retry.class)
    public void testCaseShouldBeCreatedEditedDeleted() {
        testCase = TestCase.builder()
                .title(faker.gameOfThrones().character())
                .status("Actual")
                .description(faker.chuckNorris().fact())
                .suite("Authorization")
                .severity("Major")
                .priority("High")
                .type("Smoke")
                .milestone("Release 1.0")
                .behavior("Positive")
                .automationStatus("Automated")
                .preConditions(faker.harryPotter().spell())
                .postConditions(faker.harryPotter().quote())
                .build();

        loginSteps
                .logIn(EMAIL, PASSWORD);
        repositorySteps
                .createNewTestCase("QASE", testCase)
                .validateIsTestCaseCreated(testCase, "QASE")
                .validateDetailsOfCreatedTestCase(testCase, "QASE");
        testCase = TestCase.builder()
                .title(faker.gameOfThrones().character())
                .status("Draft")
                .description(faker.chuckNorris().fact())
                .suite("Team")
                .severity("Minor")
                .priority("Low")
                .type("Security")
                .milestone("Release 2.0")
                .behavior("Negative")
                .automationStatus("Not automated")
                .preConditions(faker.harryPotter().spell())
                .postConditions(faker.harryPotter().quote())
                .build();
        repositorySteps
                .editTestCase(testCase)
                .validateDetailsOfEditedTestCase(testCase, "QASE");
        repositorySteps
                .deleteTestCase("QASE", testCase)
                .validateIsTestCaseDeleted("QASE", testCase);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testSuiteShouldBeCreatedEditedDeleted() {
        testSuite = TestSuite.builder()
                .suiteName(faker.funnyName().name())
                .parentSuite("Projects")
                .description(faker.chuckNorris().fact())
                .preconditions(faker.backToTheFuture().quote())
                .build();
        loginSteps
                .logIn(EMAIL, PASSWORD);
        repositorySteps
                .createNewTestSuite("QASE", testSuite)
                .validateIsTestSuiteCreated("QASE", testSuite)
                .validateDetailsOfCreatedTestSuite("QASE", testSuite);
        testSuite = TestSuite.builder()
                .suiteName(faker.funnyName().name())
                .parentSuite("Authorization")
                .description(faker.chuckNorris().fact())
                .preconditions(faker.backToTheFuture().quote())
                .build();
        repositorySteps
                .editTestSuite(testSuite)
                .validateDetailsOfEditedTestSuite("QASE", testSuite);
        repositorySteps
                .deleteTestSuite("QASE", testSuite)
                .validateIsTestSuiteDeleted("QASE", testSuite);
    }

}
