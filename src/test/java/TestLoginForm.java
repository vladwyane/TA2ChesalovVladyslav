import iUAPages.LoginPage;
import iUAPages.MainPageIua;
import iUAPages.emailPage.EmailPage;
import iUAPages.emailPage.emaiPageLinks.CreateLetterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import mailinatorPage.InboxPage;
import mailinatorPage.IndexMailinatorPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.concurrent.TimeUnit;

public class TestLoginForm {

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
        //EmailPage emailPage = mainPageIua.enterToEmailPage("vladyslav.chesalov", "rdf49dw23");
        //emailPage.clickLinkCreateLetter();
        //CreateLetterPage createLetterPage = new CreateLetterPage(driver);
       // createLetterPage.sendLetter("vladyslav.chesalov@mailinator.com", "theme", "body");
        IndexMailinatorPage indexMailinatorPage = new IndexMailinatorPage(driver);
        driver.get( indexMailinatorPage.getMailinatorURI());
        indexMailinatorPage.fillingCheckInboxField("vladyslav.chesalov");
        indexMailinatorPage.clickGoButton();
        InboxPage inboxPage = new InboxPage(driver);
        softAssert.assertEquals(inboxPage.getTitleFromValue(), "Владислав1");
        inboxPage.clickTitleFrom();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='msg_body']")));

        //Thread.sleep(5000);

        softAssert.assertEquals(inboxPage.getThemeTitleValue(), "Владислав2");
        softAssert.assertEquals(driver.findElement(By.tagName("body")).getText(), "Владислав3");

        softAssert.assertAll();
    }

}
