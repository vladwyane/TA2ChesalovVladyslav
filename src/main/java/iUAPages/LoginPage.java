package iUAPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    @Name("Error Message")
    @FindBy(css = "div.content.clear")
    private HtmlElement errorMessage;

    public String getErrorMessageValue() {
        return errorMessage.getText();
    }
}
