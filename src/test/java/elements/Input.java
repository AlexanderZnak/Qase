package elements;

import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Input {
    String locator = "//*[contains(text(),'%s')]/following::input";
    String label;

    public Input(String label) {
        this.label = label;
    }

    public void write(String text) {
        log.info(String.format("Writing text: %s into input with label: %s", text, label));
        $x(String.format(locator, label)).sendKeys(text);
    }
}
