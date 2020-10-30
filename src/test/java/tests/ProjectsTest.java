package tests;

import com.github.javafaker.Faker;
import models.TestProject;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class ProjectsTest extends BaseTest {
    TestProject testProject;
    Faker faker = new Faker();

    @Test(retryAnalyzer = Retry.class)
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
