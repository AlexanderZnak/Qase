package steps;

import pages.ProjectsPage;

import static org.testng.Assert.*;

public class ProjectsSteps {
    ProjectsPage projectsPage;

    public ProjectsSteps() {
        projectsPage = new ProjectsPage();
    }

    public ProjectsSteps createNewProject(String projectName, String projectCode, String projectDescription) {
        projectsPage
                .openPage()
                .clickCreateNewProject()
                .fillInDataForCreatingProject(projectName, projectCode, projectDescription);
        return this;
    }

    public ProjectsSteps validateIsProjectCreated(String expectedProjectName) {
        String actualProjectName = projectsPage
                .openPage()
                .getCreatedProject();
        assertEquals(actualProjectName, expectedProjectName);
        return this;
    }

    public ProjectsSteps editExistingProject(String projectName, String projectOption, String inputName, String inputCode, String inputDescription, String buttonName) {
        projectsPage
                .openPage()
                .selectOption(projectName, projectOption)
                .clearProjectInput()
                .fillInProjectNameProjectCodeProjectDescription(inputName, inputCode, inputDescription)
                .clickByXpathText(buttonName);
        return this;
    }

    public ProjectsSteps validateIsProjectEdited(String expectedAlertMesaage) {
        assertEquals(projectsPage.getAlertMessage(), expectedAlertMesaage);
        return this;
    }

    public ProjectsSteps deleteProject(String projectName, String projectOption, String buttonName) {
        projectsPage
                .openPage()
                .selectOption(projectName, projectOption)
                .clickForAcceptingToDelete(buttonName);
        return this;
    }

    public ProjectsSteps validateIsProjectDeleted(String projectName) {
        if (projectsPage.isProjectExisted(projectName)) {
            assertFalse(projectsPage.isProjectExisted(projectName));
        } else {
            assertTrue(projectsPage.isProjectExisted(projectName));
        }
        return this;
    }
}
