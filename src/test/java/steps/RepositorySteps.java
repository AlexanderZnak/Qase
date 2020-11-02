package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import models.TestSuite;
import pages.RepositoryPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Log4j2
public class RepositorySteps {
    RepositoryPage repositoryPage;

    public RepositorySteps() {
        repositoryPage = new RepositoryPage();
    }

    @Step("Creating new test case: '{testCase}', on the project: '{projectCode}'")
    public RepositorySteps createNewTestCase(String projectCode, TestCase testCase) {
        log.info(String.format("Creating new test case: '%s', on the project: '%s'", testCase, projectCode));
        repositoryPage
                .openPage(projectCode)
                .clickByXpathText(" Case");
        repositoryPage
                .fillInTestCase(testCase)
                .clickByXpathText("Save");
        return this;
    }

    @Step("Validating is test case with name: '{testCase}' created, from project: '{projectName}'")
    public RepositorySteps validateIsTestCaseCreated(TestCase testCase, String projectName) {
        log.info(String.format("Validating is test case with name: '%s' created, from project: '%s'", testCase.getTitle(), projectName));
        repositoryPage.openPage(projectName);
        assertTrue(repositoryPage.findTestCaseByName(testCase.getTitle()));
        return this;
    }

    @Step("Validating details of test case: '{testCase}', from project: '{projectCode}'")
    public RepositorySteps validateDetailsOfCreatedTestCase(TestCase testCase, String projectCode) {
        log.info(String.format("Validating details of test case: '%s', from project: '%s'", testCase, projectCode));
        boolean isTrue = repositoryPage
                .openPage(projectCode)
                .clickOnCreatedCase(testCase.getTitle())
                .clickEditTestCase()
                .compareAllDetailsInTestCase(testCase);
        assertTrue(isTrue);
        return this;
    }

    @Step("Validating details of test case: '{testCase}'")
    public RepositorySteps validateDetailsOfEditedTestCase(TestCase testCase, String projectCode) {
        log.info(String.format("Validating details of test case: '%s'", testCase));
        validateDetailsOfCreatedTestCase(testCase, projectCode);
        return this;
    }

    @Step("Editing all details of test case: '{testCase}'")
    public RepositorySteps editTestCase(TestCase testCase) {
        log.info(String.format("Editing all details of test case: '%s'", testCase));
        repositoryPage
                .clearInputFields()
                .fillInTestCase(testCase)
                .clickByXpathText("Save");
        return this;
    }

    @Step("Deleting test case: '{testCase}' from project: '{projectCode}'")
    public RepositorySteps deleteTestCase(String projectCode, TestCase testCase) {
        log.info(String.format("Deleting test case: '%s' from project: '%s'", testCase, projectCode));
        repositoryPage
                .openPage(projectCode)
                .clickOnCreatedCase(testCase.getTitle())
                .clickDeleteTestCase()
                .acceptToDeleteTestCase();
        return this;
    }

    @Step("Validating is test case: '{testCase}', from project: '{projectCode}' deleted")
    public RepositorySteps validateIsTestCaseDeleted(String projectCode, TestCase testCase) {
        log.info(String.format("Validating is test case: '%s', from project: '%s' deleted", testCase, projectCode));
        boolean isCase = repositoryPage
                .openPage(projectCode)
                .findTestCaseByName(testCase.getTitle());
        assertFalse(isCase);
        return this;
    }

    @Step("Creating new test suite: '{testSuite}', on the project: '{projectCode}'")
    public RepositorySteps createNewTestSuite(String projectCode, TestSuite testSuite) {
        log.info(String.format("Creating new test suite: '%s', on the project: '%s'", testSuite, projectCode));
        repositoryPage
                .openPage(projectCode)
                .clickByXpathText(" Suite");
        repositoryPage
                .fillInTestSuite(testSuite)
                .clickCreateSuite();
        return this;
    }

    @Step("Validating is test suite with name: '{testSuite}' created, from project: '{projectCode}'")
    public RepositorySteps validateIsTestSuiteCreated(String projectCode, TestSuite testSuite) {
        log.info(String.format("Validating is test suite with name: '%s' created, from project: '%s'", testSuite, projectCode));
        boolean isSuite = repositoryPage
                .openPage(projectCode)
                .findTestSuiteByName(testSuite.getSuiteName());
        assertTrue(isSuite);
        return this;
    }

    @Step("Validating details of test suite: '{testSuite}', from project: '{projectCode}'")
    public RepositorySteps validateDetailsOfCreatedTestSuite(String projectCode, TestSuite testSuite) {
        log.info(String.format("Validating details of test suite: '%s', from project: '%s'", testSuite, projectCode));
        boolean isTrue = repositoryPage
                .openPage(projectCode)
                .clickOnEditSuite(testSuite.getSuiteName())
                .compareAllDetailsInTestSuite(testSuite);
        assertTrue(isTrue);
        return this;
    }

    @Step("Editing all details of test suite: '{testSuite}'")
    public RepositorySteps editTestSuite(TestSuite testSuite) {
        log.info(String.format("Editing all details of test suite: '%s'", testSuite));
        repositoryPage
                .clearSuiteInputFields()
                .fillInTestSuite(testSuite)
                .clickSaveChanges();
        return this;
    }

    @Step("Validating details of test suite: '{testSuite}', from project: '{projectCode}'")
    public RepositorySteps validateDetailsOfEditedTestSuite(String projectCode, TestSuite testSuite) {
        validateDetailsOfCreatedTestSuite(projectCode, testSuite);
        return this;
    }

    @Step("Deleting test suite: '{testSuite}' from project: '{projectCode}'")
    public RepositorySteps deleteTestSuite(String projectCode, TestSuite testSuite) {
        log.info(String.format("Deleting test suite: '%s' from project: '%s'", testSuite, projectCode));
        repositoryPage
                .openPage(projectCode)
                .clickOnDeleteSuite(testSuite.getSuiteName())
                .acceptToDeleteTestSuite();
        return this;
    }

    @Step("Validating is test suite: '{testSuite}', from project: '{projectCode}' deleted")
    public RepositorySteps validateIsTestSuiteDeleted(String projectCode, TestSuite testSuite) {
        log.info(String.format("Validating is test suite: '%s', from project: '%s' deleted", testSuite, projectCode));
        boolean isSuite = repositoryPage
                .openPage(projectCode)
                .findTestSuiteByName(testSuite.getSuiteName());
        assertFalse(isSuite);
        return this;
    }
}
