package iUAPages.emailPage.emaiPageLinks;

import iUAPages.emailPage.EmailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class CreateLetterPage extends EmailPage{

    public CreateLetterPage(WebDriver driver) {
        super(driver);
    }

    @Name("Recipient Field")
    @FindBy(id = "to")
    private TextInput recipientField;

    public void fillingRecipientField(String recipient) {
        recipientField.sendKeys(recipient);
    }

    public String getRecipientFieldValue() {
        return recipientField.getEnteredText();
    }

    @Name("Theme Field")
    @FindBy(name = "subject")
    private TextInput themeField;

    public void fillingThemeField(String theme) {
        themeField.sendKeys(theme);
    }

    public String getThemeFieldValue() {
        return themeField.getEnteredText();
    }

    @Name("Body Letter")
    @FindBy(id = "text")
    private TextInput textEditor;

    public void fillingTextEditor(String text) {
        textEditor.sendKeys(text);
    }

    public String getTextEditorValue() {
        return textEditor.getEnteredText();
    }

    @Name("Submit Letter Button")
    @FindBy(xpath = "//p[@class='send_container']//input[@name='send']")
    private Button submitLetter;

    public void clickSubmitLetter() {
        submitLetter.click();
    }

    public void sendLetter(String recipient, String theme, String letterText) {
        fillingRecipientField(recipient);
        fillingThemeField(theme);
        fillingTextEditor(letterText);
        clickSubmitLetter();
    }
}
