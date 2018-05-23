import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

@Name("Shopping Item")
@Block(@FindBy(css = ".product_list"))
public class ResultItem extends HtmlElement {
    private WebDriver driver;
    private WebDriverWait wait;

    public ResultItem(WebDriver driver){
        HtmlElementLoader.populatePageObject(this, driver);
        wait = new WebDriverWait(driver, 3);
    }

    @Name("Item Name")
    @FindBy(css="a.product-name")
    private HtmlElement name;

    @Name("Item Price")
    @FindBy(css=".product-price")
    private HtmlElement price;


    public String getName(){
        return wait.until(ExpectedConditions.visibilityOf(name)).getText();
    }

}