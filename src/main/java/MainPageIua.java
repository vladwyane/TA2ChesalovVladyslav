import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class MainPageIua {

    private WebDriver driver;
    private String mainURI = "http://i.ua";

    public String getMainURI() {
        return mainURI;
    }

    public void setMainURI(String mainURI) {
        this.mainURI = mainURI;
    }


    public MainPageIua(WebDriver driver){
        HtmlElementLoader.populatePageObject(this, driver);
        this.driver = driver;
    }

    @Name("Login Field")
    @FindBy(xpath = "//*[@name='login']")
    private TextInput loginField;

    public void fillingLoginField(String login) {
        loginField.sendKeys(login);
    }

    public String getLoginValue() {
        return loginField.getEnteredText();
    }

    @Name("Password Field")
    @FindBy(name="pass")
    private TextInput passwordField;

    public void fillingPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public String getPasswordValue() { return passwordField.getEnteredText();
    }

    @Name("Submit Login Form Button")
    @FindBy(xpath = "//form[@name='lform']//input[@type='submit']")
    private Button submitLoginForm;

    public void clickSubmitLoginForm() {
        submitLoginForm.click();
    }

    public void enterToEmailPage(String login, String password) {
        fillingLoginField(login);
        fillingPasswordField(password);
        clickSubmitLoginForm();
    }


}
