package elements;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Input {
    String inputLocator = "//*[text()='%s']/parent::div//input";
    String hiddenInputLocator = "//*[text()='%s']/parent::div//*[contains(@class, 'empty-node')]";
    String locatorForGetText = "//*[text()='%s']/parent::div//*[@class= 'ProseMirror']";
    String label;

    public Input(String label) {
        this.label = label;
    }

    public void write(String text) {
        log.info(String.format("Writing text: %s into input with label: %s", text, label));
        if (label.equals("Title") | label.equals("Suite name") | label.equals("Run title")) {
            $x(String.format(inputLocator, label)).shouldBe(Condition.visible).sendKeys(text);
        } else {
            $x(String.format(hiddenInputLocator, label)).shouldBe(Condition.visible).sendKeys(text);
        }
    }

    public String getText() {
        log.info(String.format("Getting text from '%s'", label));
        if (label.equals("Title") | label.equals("Suite name") | label.equals("Run title")) {
            return $x(String.format(inputLocator, label)).shouldBe(Condition.visible).getValue();
        } else {
            return $x(String.format(locatorForGetText, label)).shouldBe(Condition.visible).text();
        }
    }

    public void clear() {
        log.info(String.format("Clearing input with label: %s", label));
        if (label.equals("Title") | label.equals("Suite name") | label.equals("Run title")) {
            $x(String.format(inputLocator, label)).shouldBe(Condition.visible).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        } else {
            $x(String.format(locatorForGetText, label)).shouldBe(Condition.visible).clear();
        }
    }

}
