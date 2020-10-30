package steps;

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

    public RepositorySteps validateIsTestCaseCreated(TestCase testCase, String projectName) {
        log.info(String.format("Validating is test case with name: '%s' created, from project: '%s'", testCase.getTitle(), projectName));
        repositoryPage.openPage(projectName);
        assertTrue(repositoryPage.findTestCaseByName(testCase.getTitle()));
        return this;
    }

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

    public RepositorySteps validateDetailsOfEditedTestCase(TestCase testCase, String projectCode) {
        log.info(String.format("Validating details of test case: '%s'", testCase));
        validateDetailsOfCreatedTestCase(testCase, projectCode);
        return this;
    }

    public RepositorySteps editTestCase(TestCase testCase) {
        log.info(String.format("Editing all details of test case: '%s'", testCase));
        repositoryPage
                .clearInputFields()
                .fillInTestCase(testCase)
                .clickByXpathText("Save");
        return this;
    }

    public RepositorySteps deleteTestCase(String projectCode, TestCase testCase) {
        log.info(String.format("Deleting test case: '%s' from project: '%s'", testCase, projectCode));
        repositoryPage
                .openPage(projectCode)
                .clickOnCreatedCase(testCase.getTitle())
                .clickDeleteTestCase()
                .acceptToDeleteTestCase();
        return this;
    }

    public RepositorySteps validateIsTestCaseDeleted(String projectCode, TestCase testCase) {
        log.info(String.format("Validating is test case: '%s', from project: '%s' deleted", testCase, projectCode));
        boolean isCase = repositoryPage
                .openPage(projectCode)
                .findTestCaseByName(testCase.getTitle());
        assertFalse(isCase);
        return this;
    }

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

    public RepositorySteps validateIsTestSuiteCreated(String projectCode, TestSuite testSuite) {
        log.info(String.format("Validating is test suite with name: '%s' created, from project: '%s'", testSuite, projectCode));
        boolean isSuite = repositoryPage
                .openPage(projectCode)
                .findTestSuiteByName(testSuite.getSuiteName());
        assertTrue(isSuite);
        return this;
    }

    public RepositorySteps validateDetailsOfCreatedTestSuite(String projectCode, TestSuite testSuite) {
        log.info(String.format("Validating details of test suite: '%s', from project: '%s'", testSuite, projectCode));
        boolean isTrue = repositoryPage
                .openPage(projectCode)
                .clickOnEditSuite(testSuite.getSuiteName())
                .compareAllDetailsInTestSuite(testSuite);
        assertTrue(isTrue);
        return this;
    }

    public RepositorySteps editTestSuite(TestSuite testSuite) {
        log.info(String.format("Editing all details of test suite: '%s'", testSuite));
        repositoryPage
                .clearSuiteInputFields()
                .fillInTestSuite(testSuite)
                .clickSaveChanges();
        return this;
    }

    public RepositorySteps validateDetailsOfEditedTestSuite(String projectCode, TestSuite testSuite) {
        validateDetailsOfCreatedTestSuite(projectCode, testSuite);
        return this;
    }

    public RepositorySteps deleteTestSuite(String projectCode, TestSuite testSuite) {
        log.info(String.format("Deleting test suite: '%s' from project: '%s'", testSuite, projectCode));
        repositoryPage
                .openPage(projectCode)
                .clickOnDeleteSuite(testSuite.getSuiteName())
                .acceptToDeleteTestSuite();
        return this;
    }

    public RepositorySteps validateIsTestSuiteDeleted(String projectCode, TestSuite testSuite) {
        log.info(String.format("Validating is test suite: '%s', from project: '%s' deleted", testSuite, projectCode));
        boolean isSuite = repositoryPage
                .openPage(projectCode)
                .findTestSuiteByName(testSuite.getSuiteName());
        assertFalse(isSuite);
        return this;
    }
}
