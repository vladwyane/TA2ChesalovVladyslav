package mailinatorPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class IndexMailinatorPage {

    private WebDriver driver;
    private String mailinatorURI = "https://mailinator.com";

    public String getMailinatorURI() {
        return mailinatorURI;
    }

    public void setMailinatorURI(String mailinatorURI) {
        this.mailinatorURI = mailinatorURI;
    }

    public IndexMailinatorPage(WebDriver driver){
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    @Name("Check Inbox Field")
    @FindBy(id = "inboxfield")
    private TextInput checkInboxField;

    public void fillingCheckInboxField(String email) {
        checkInboxField.sendKeys(email);
    }

    public String getCheckInboxValue() {
        return checkInboxField.getEnteredText();
    }

    @Name("Go Button")
    @FindBy(xpath = "//span[@class='input-group-btn']")
    private Button goButton;

    public void clickGoButton() {
        goButton.click();
    }
}
