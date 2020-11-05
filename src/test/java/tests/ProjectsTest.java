package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import models.TestProject;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class ProjectsTest extends BaseTest {
    TestProject testProject;
    Faker faker = new Faker();

    @Test(description = "Success CRUD for Test Project", retryAnalyzer = Retry.class)
    @Description("Validation of correct working to create, edit and delete Test Project")
    public void testProjectShouldBeCreatedEditedDeleted() {
        loginSteps
                .logIn(EMAIL, PASSWORD);
        testProject = TestProject.builder()
                .projectName(faker.animal().name())
                .projectCode(faker.hacker().abbreviation())
                .description(faker.lordOfTheRings().location())
                .build();
        projectsSteps
                .createNewProject(testProject)
                .validateIsProjectCreated(testProject)
                .validateDetailsOfCreatedProject("Settings", testProject);
        testProject = TestProject.builder()
                .projectName(faker.animal().name())
                .projectCode(faker.hacker().abbreviation())
                .description(faker.lordOfTheRings().location())
                .build();
        projectsSteps
                .editExistingProject(testProject)
                .validateIsProjectEdited(testProject);
        projectsSteps
                .deleteProject(testProject, "Delete")
                .validateIsProjectDeleted(testProject);
    }

}
