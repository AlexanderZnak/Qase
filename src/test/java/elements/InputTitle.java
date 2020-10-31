package elements;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class InputTitle {
    String inputLocator = "//*[text()='%s']/parent::div//input";
    String label;

    public InputTitle(String label) {
        this.label = label;
    }

    public void write(String text) {
        log.info(String.format("Writing text: %s into input with label: %s", text, label));
        $x(String.format(inputLocator, label)).shouldBe(Condition.visible).sendKeys(text);
    }

    public String getText() {
        log.info(String.format("Getting text from '%s'", label));
        return $x(String.format(inputLocator, label)).shouldBe(Condition.visible).getValue();
    }

    public void clear() {
        log.info(String.format("Clearing input with label: %s", label));
        $x(String.format(inputLocator, label)).shouldBe(Condition.visible).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }
}
