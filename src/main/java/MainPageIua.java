import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class MainPageIua {

    private WebDriver driver;

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


    public void doSearch(String query){

        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL)
                .build().perform();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
