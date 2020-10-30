package elements;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Select {
    String labelLocator = "//*[text()='%s']/parent::div//div[contains(@class, 'container')]";
    String optionLocator = "//*[contains(@id, 'react-select') and contains(text(),'%s')]";
    String assignee = "#react-select-5-option-%s";
    String label;

    public Select(String label) {
        this.label = label;
    }

    public void select(String option) {
        log.info(String.format("Selecting option: %s with label: %s", option, label));
        if (StringUtils.isNotEmpty(option)) {
            $x(String.format(labelLocator, label)).shouldBe(Condition.visible).click();
            if (label.equals("Default assignee")) {
                $(String.format(assignee, option)).shouldBe(Condition.visible).click();
            } else {
                $x(String.format(optionLocator, option)).shouldBe(Condition.visible).click();
            }

        }
    }

    public String getText() {
        log.info(String.format("Getting text from '%s'", label));
        return $x(String.format(labelLocator, label)).shouldBe(Condition.visible).text();
    }
}
