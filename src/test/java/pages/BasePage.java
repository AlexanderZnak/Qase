package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class BasePage {
    public static final String XPATH_BY_TEXT = "//*[contains(text(),'%s')]";
    public static final String URL = "https://app.qase.io";
    private static final String DROPDOWN = "//*[contains(text(),'%s')]/following::td//*[contains(@class,'fa-ellipsis-h')]";
    private static final String SELECTS_OPTION = "//*[contains(text(),'%s')]/following::td//*[contains(@class,'dropdown-item')]//*[contains(text(),'%s')]";

    public BasePage selectOption(String name, String option) {
        log.info(String.format("Selecting option: '%s', in the '%s'", option, name));
        $x(String.format(DROPDOWN, name)).shouldBe(Condition.visible).click();
        $x(String.format(SELECTS_OPTION, name, option)).shouldBe(Condition.visible).click();
        return this;
    }

    public BasePage clickByXpathText(String buttonName) {
        log.info(String.format("Clicking button: %s", buttonName));
        $x(String.format(XPATH_BY_TEXT, buttonName)).shouldBe(Condition.visible).click();
        return this;
    }

}
