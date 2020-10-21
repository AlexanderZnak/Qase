package pages;

import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    public static final String XPATH_BY_TEXT = "//*[contains(text(),'%s')]";

    public BasePage clickByXpathText(String text) {
        $x(String.format(XPATH_BY_TEXT, text)).click();
        return this;
    }
}
