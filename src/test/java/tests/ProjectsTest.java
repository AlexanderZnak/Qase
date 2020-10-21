package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class ProjectsTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void createNewProject() {
        loginSteps
                .logIn(EMAIL, PASSWORD);
        projectsSteps
                .createNewProject("Hello world", "HW", "Testing new create project")
                .validateIsProjectCreated("Hello world");
    }

    @Test()
    public void existingProjectShouldBeEdited() {
        loginSteps
                .logIn(EMAIL, PASSWORD);
        projectsSteps
                .editExistingProject("Hello world", "Settings", "Hello all", "HA", "New testing data", "Update settings")
                .validateIsProjectEdited("Project settings were successfully updated!");
    }

    @Test
    public void projectShouldBeDeleted() {
        loginSteps
                .logIn(EMAIL, PASSWORD);
        projectsSteps
                .deleteProject("Hello all", "Delete", " Delete project")
                .validateIsProjectDeleted("Hello all");
    }
}
