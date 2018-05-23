import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class LoginPage extends MainPageIua{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Name("Error Message")
    @FindBy(css = "div.content.clear")
    private HtmlElement errorMessage;

    public String getErrorMessageValue() {
        return errorMessage.getText();
    }
}
