package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ProjectsPage extends BasePage {
    private static final String URL = "https://app.qase.io/projects";
    private static final By CREATE_NEW_PROJECT_BUTTON = By.id("createButton");
    private static final By PROJECT_NAME_INPUT = By.id("inputTitle");
    private static final By PROJECT_CODE_INPUT = By.id("inputCode");
    private static final By PROJECT_DESCRIPTION_INPUT = By.id("inputDescription");
    private static final String PROJECT_NAME = ".defect-title";
    private static final String PROJECT_DROPDOWN = "//*[contains(text(),'%s')]/following::td//*[contains(@class,'fa-ellipsis-h')]";
    private static final String PROJECT_SELECTS = "//*[contains(text(),'%s')]/following::td//*[contains(@class,'dropdown-item')]//*[contains(text(),'%s')]";


    public ProjectsPage openPage() {
        open(URL);
        isPageOpened();
        return this;
    }

    public ProjectsPage isPageOpened() {
        $(CREATE_NEW_PROJECT_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public ProjectsPage clickCreateNewProject() {
        $(CREATE_NEW_PROJECT_BUTTON).click();
        return this;
    }

    public ProjectsPage clearProjectInput() {
        $(PROJECT_NAME_INPUT).clear();
        $(PROJECT_CODE_INPUT).clear();
        $(PROJECT_DESCRIPTION_INPUT).clear();
        return this;
    }

    public ProjectsPage fillInProjectNameProjectCodeProjectDescription(String inputName, String inputCode, String inputDescription) {
        $(PROJECT_NAME_INPUT).sendKeys(inputName);
        $(PROJECT_CODE_INPUT).sendKeys(inputCode);
        $(PROJECT_DESCRIPTION_INPUT).sendKeys(inputDescription);
        return this;
    }

    public ProjectsPage fillInDataForCreatingProject(String inputName, String inputCode, String inputDescription) {
        fillInProjectNameProjectCodeProjectDescription(inputName, inputCode, inputDescription);
        clickByXpathText("Create project");
        return this;
    }

    public String getCreatedProject() {
        ElementsCollection projectsName = $$(PROJECT_NAME);
        return projectsName.last().text();
    }

    public ProjectsPage selectOption(String projectName, String projectOption) {
        $x(String.format(PROJECT_DROPDOWN, projectName)).click();
        $x(String.format(PROJECT_SELECTS, projectName, projectOption)).click();
        return this;
    }

    public String getAlertMessage() {
        return $(".alert-message").text();
    }

    public ProjectsPage clickForAcceptingToDelete(String buttonName) {
        clickByXpathText(buttonName);
        return this;
    }

    public boolean isProjectExisted(String projectName) {
        ElementsCollection projectsName = $$(PROJECT_NAME);
        boolean isExisted = false;
        for (int i = 0; i < projectsName.size(); i++) {
            if (!projectsName.get(i).text().equals(projectName)) {
                isExisted = true;
            }
        }
        return isExisted;
    }
}
