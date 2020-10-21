package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class Select {
    String labelLocator = "//*[text()='%s']/following::div";
    String optionLocator = "//*[text()='%s']/following::input";
    String label;

    public Select(String label) {
        this.label = label;
    }

    public void select(String option) {
        log.info(String.format("Selecting option: %s with label: %s", option, label));
//        $x(String.format(labelLocator, label)).shouldBe(Condition.visible).click();
//        $x(String.format(optionLocator, label)).shouldBe(Condition.visible).sendKeys(option);
//        actions().sendKeys(Keys.ENTER).perform();
        SelenideElement  element = $x(String.format(optionLocator, label));
        actions().moveToElement(element).click(element).sendKeys(option).sendKeys(Keys.ENTER).perform();
    }
}
