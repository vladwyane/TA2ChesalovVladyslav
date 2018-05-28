import iUAPages.LoginPage;
import iUAPages.MainPageIua;
import iUAPages.emailPage.EmailPage;
import iUAPages.emailPage.emaiPageLinks.CreateLetterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import mailinatorPage.InboxPage;
import mailinatorPage.IndexMailinatorPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.concurrent.TimeUnit;

public class TestMailAccountIua {

    private WebDriver driver;
    private SoftAssert softAssert;
    private MainPageIua mainPageIua;

    @BeforeSuite
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        Reporter.log("Before Suit executed", 1, true);
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        softAssert = new SoftAssert();
        mainPageIua = new MainPageIua(driver);
        driver.get( mainPageIua.getMainURI());
        Reporter.log("Before Method executed", 1, true);
    }

    @AfterMethod
    public void teardown() {
        Reporter.log("After Method executed", 1, true);
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCorrectEnterToEmail() {
        EmailPage emailPage = mainPageIua.enterToEmailPage("vladyslav.chesalov", "rdf49dw23");
        softAssert.assertEquals(emailPage.getEmailTitleValue(), "vladyslav.chesalov@i.ua");
        softAssert.assertNotNull(emailPage);
        softAssert.assertAll();
    }

    @Test
    public void testInvalidLoginValue() {
        EmailPage emailPage = mainPageIua.enterToEmailPage("error", "rdf49dw23");
        LoginPage loginPage = new LoginPage(driver);
        softAssert.assertEquals(loginPage.getErrorMessageValue(), "Неверный логин или пароль");
        softAssert.assertNotNull(emailPage);
        softAssert.assertAll();
    }

    @Test
    public void testCorrectSendLetter() throws InterruptedException {
        EmailPage emailPage = mainPageIua.enterToEmailPage("vladyslav.chesalov", "rdf49dw23");
        emailPage.clickLinkCreateLetter();
        CreateLetterPage createLetterPage = new CreateLetterPage(driver);
        createLetterPage.sendLetter("vladyslav.chesalov@mailinator.com", "theme", "body new content");
        IndexMailinatorPage indexMailinatorPage = new IndexMailinatorPage(driver);
        driver.get( indexMailinatorPage.getMailinatorURI());
        InboxPage inboxPage = indexMailinatorPage.enterToInboxPage("vladyslav.chesalov");;
        softAssert.assertEquals(inboxPage.getTitleFromValue(), "Владислав");
        inboxPage.clickTitleFrom();
        //Waiting appears frame
        Thread.sleep(2000);
        softAssert.assertEquals(inboxPage.getThemeTitleValue(), "theme");
        softAssert.assertEquals(inboxPage.getIframeBodyValue(driver), "body new content");
        softAssert.assertAll();
    }
}
