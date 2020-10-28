package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.extern.log4j.Log4j2;
import models.TestProject;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ProjectsPage extends BasePage {
    private static final By CREATE_NEW_PROJECT_BUTTON = By.id("createButton");
    private static final By PROJECT_NAME_INPUT = By.id("inputTitle");
    private static final By PROJECT_CODE_INPUT = By.id("inputCode");
    private static final By PROJECT_DESCRIPTION_INPUT = By.id("inputDescription");
    private static final String PROJECT_NAME = ".defect-title";

    public ProjectsPage openPage() {
        String url = URL + "/projects";
        log.info(String.format("Opening projects page by url: %s", url));
        open(url);
        isPageOpened();
        return this;
    }

    public ProjectsPage isPageOpened() {
        log.info("Validating is products page opened");
        $(CREATE_NEW_PROJECT_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public ProjectsPage clickCreateNewProject() {
        log.info("Clicking the button create new project");
        $(CREATE_NEW_PROJECT_BUTTON).shouldBe(Condition.visible).click();
        return this;
    }

    public ProjectsPage clearProjectInput() {
        log.info("Clearing fields for editing");
        $(PROJECT_NAME_INPUT).shouldBe(Condition.visible).clear();
        $(PROJECT_CODE_INPUT).shouldBe(Condition.visible).clear();
        $(PROJECT_DESCRIPTION_INPUT).shouldBe(Condition.visible).clear();
        return this;
    }

    public ProjectsPage fillInProjectNameProjectCodeProjectDescription(TestProject testProject) {
        log.info(String.format("Filling in project name: '%s', code: '%s', description: '%s'", testProject.getProjectName(), testProject.getProjectCode(), testProject.getDescription()));
        $(PROJECT_NAME_INPUT).shouldBe(Condition.visible).sendKeys(testProject.getProjectName());
        $(PROJECT_CODE_INPUT).shouldBe(Condition.visible).clear();
        $(PROJECT_CODE_INPUT).shouldBe(Condition.visible).sendKeys(testProject.getProjectCode());
        $(PROJECT_DESCRIPTION_INPUT).shouldBe(Condition.visible).sendKeys(testProject.getDescription());
        return this;
    }

    public ProjectsPage fillInDataForCreatingProject(TestProject testProject) {
        log.info(String.format("Creating Test Project: '%s'", testProject));
        fillInProjectNameProjectCodeProjectDescription(testProject);
        clickByXpathText("Create project");
        return this;
    }

    public String getCreatedProject() {
        log.info("Getting text the last test project");
        ElementsCollection projectsName = $$(PROJECT_NAME).shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));
        return projectsName.last().text();
    }

    public boolean isProjectExisted(String projectName) {
        log.info(String.format("Looking for project: '%s'", projectName));
        ElementsCollection projectsName = $$(PROJECT_NAME).shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1));
        boolean isExisted = false;
        for (int i = 0; i < projectsName.size(); i++) {
            if (projectsName.get(i).text().equals(projectName)) {
                isExisted = true;
            }
        }
        return isExisted;
    }

    public boolean compareAllDetailsInTestProject(TestProject expectedTestProject) {
        log.info("Expected test project: " + expectedTestProject);
        boolean name = false;
        TestProject actualTestProject = TestProject.builder()
                .projectName($(PROJECT_NAME_INPUT).shouldBe(Condition.visible).getValue())
                .projectCode($(PROJECT_CODE_INPUT).shouldBe(Condition.visible).getValue())
                .description($(PROJECT_DESCRIPTION_INPUT).shouldBe(Condition.visible).getValue())
                .build();
        log.info("Actual test project: " + actualTestProject);
        if (actualTestProject.equals(expectedTestProject))
            name = true;

        return name;
    }
}
