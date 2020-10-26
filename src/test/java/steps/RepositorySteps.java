package steps;

import lombok.extern.log4j.Log4j2;
import models.TestCase;
import pages.RepositoryPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
public class RepositorySteps {
    RepositoryPage repositoryPage;

    public RepositorySteps() {
        repositoryPage = new RepositoryPage();
    }

    public RepositorySteps createNewTestCase(String projectCode, TestCase testCase) {
        repositoryPage
                .openPage(projectCode)
                .clickCase()
                .fillInTestCase(testCase)
                .clickSave();
        return this;
    }

    public RepositorySteps validateIsTestCaseCreated(TestCase testCase, String projectName) {
        log.info(String.format("Checking is test case with name: %s created, from project: %s", testCase.getTitle(), projectName));
        repositoryPage.openPage(projectName);
        assertTrue(repositoryPage.findTestCaseByName(testCase.getTitle()));
        return this;
    }
}
