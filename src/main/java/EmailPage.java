import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class EmailPage {

    private WebDriver driver;

    public EmailPage(WebDriver driver){
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    @Name("Email Title")
    @FindBy(css = "span.sn_menu_title")
    private HtmlElement emailTitle;

    public String getEmailTitleValue() {
        return emailTitle.getText();
    }
}
