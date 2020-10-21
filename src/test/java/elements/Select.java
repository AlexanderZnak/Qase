package elements;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class Select {
    String labelLocator = "//*[text()='%s']/parent::div//div[contains(@class, 'container')]";
    String optionLocator = "//*[contains(@id, 'react-select') and contains(text(),'%s')]";
    String label;

    public Select(String label) {
        this.label = label;
    }

    public void select(String option) {
        log.info(String.format("Selecting option: %s with label: %s", option, label));
        if(StringUtils.isNotEmpty(option)) {
            $x(String.format(labelLocator, label)).shouldBe(Condition.visible).click();
            $x(String.format(optionLocator, option)).shouldBe(Condition.visible).click();
        }
    }
}
