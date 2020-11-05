package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestProject;
import pages.ProjectsPage;

import static org.testng.Assert.*;

@Log4j2
public class ProjectsSteps {
    ProjectsPage projectsPage;

    public ProjectsSteps() {
        projectsPage = new ProjectsPage();
    }

    @Step("Creating test project: '{testProject}'")
    public ProjectsSteps createNewProject(TestProject testProject) {
        log.info(String.format("Creating test project: '%s'", testProject));
        projectsPage
                .openPage()
                .clickCreateNewProject()
                .fillInDataForCreatingProject(testProject);
        return this;
    }

    @Step("Validating is Test project: '{testProject}' exists")
    public ProjectsSteps validateIsProjectCreated(TestProject testProject) {
        log.info(String.format("Validating is Test project: '%s' exists", testProject));
        String actualProjectName = projectsPage
                .openPage()
                .getCreatedProject();
        assertEquals(actualProjectName, testProject.getProjectName());
        return this;
    }

    @Step("Editing test project: '{testProject}'")
    public ProjectsSteps editExistingProject(TestProject testProject) {
        log.info(String.format("Editing test project: '%s'", testProject));
        projectsPage
                .clearProjectInput()
                .fillInProjectNameProjectCodeProjectDescription(testProject)
                .clickByXpathText("Update settings");
        return this;
    }

    @Step("Validating details of test project: '{testProject}'")
    public ProjectsSteps validateDetailsOfCreatedProject(String projectOption, TestProject testProject) {
        log.info(String.format("Validating details of test project: '%s'", testProject));
        projectsPage.selectOption(testProject.getProjectName(), projectOption);
        assertTrue(projectsPage.compareAllDetailsInTestProject(testProject));
        return this;
    }

    @Step("Validating is test project: '{testProject}' edited")
    public ProjectsSteps validateIsProjectEdited(TestProject testProject) {
        log.info(String.format("Validating is test project: '%s' edited", testProject));
        assertTrue(projectsPage.compareAllDetailsInTestProject(testProject));
        return this;
    }

    @Step("Deleting test project: '{testProject}'")
    public ProjectsSteps deleteProject(TestProject testProject, String projectOption) {
        log.info(String.format("Deleting test project: '%s'", testProject));
        projectsPage
                .openPage()
                .selectOption(testProject.getProjectName(), projectOption)
                .clickByXpathText(" Delete project");
        return this;
    }

    @Step("Validating is test project: '{testProject}' deleted")
    public ProjectsSteps validateIsProjectDeleted(TestProject testProject) {
        log.info(String.format("Validating is test project: '%s' deleted", testProject));
        boolean isExist = projectsPage
                .openPage()
                .isProjectExisted(testProject.getProjectName());
        assertFalse(isExist);
        return this;
    }
}
