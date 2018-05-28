package mailinatorPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class InboxPage extends IndexMailinatorPage {

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    @Name("Title Sender")
    @FindBy(xpath = "//div[@title='FROM']")
    private HtmlElement titleFrom;

    public String getTitleFromValue() {
        return titleFrom.getText();
    }

    public void clickTitleFrom() {
        titleFrom.click();
    }

    @Name("Title Subject")
    @FindBy(xpath = "//div[@id='msgpane']/div/div[1]")
    private HtmlElement themeTitle;

    public String getThemeTitleValue() {
        return themeTitle.getText();
    }

    @Name("Iframe Message")
    @FindBy(xpath = "//iframe[@id='msg_body']")
    private HtmlElement iframe;

    @Name("Iframe body")
    @FindBy(tagName = "body")
    private HtmlElement iframeBody;

    public String getIframeBodyValue(WebDriver driver) {
        driver.switchTo().frame(iframe);
        return iframeBody.getText();
    }
}
