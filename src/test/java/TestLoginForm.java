import io.github.bonigarcia.wdm.WebDriverManager;
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
        MainPageIua mainPageIua = new MainPageIua(driver);
        mainPageIua.enterToEmailPage("vladyslav.chesalov", "rdf49dw23");
        EmailPage emailPage = new EmailPage(driver);
        softAssert.assertEquals(emailPage.getEmailTitleValue(), "vladyslav.chesalov@i.ua");
        softAssert.assertAll();
    }

    @Test
    public void testInvalidLoginValue() {
        MainPageIua mainPageIua = new MainPageIua(driver);
        mainPageIua.enterToEmailPage("error", "rdf49dw23");
        LoginPage loginPage = new LoginPage(driver);
        softAssert.assertEquals(loginPage.getErrorMessageValue(), "Неверный логин или пароль");
        softAssert.assertAll();
    }

    @Test
    public void testInvalidPasswordValue() {
        MainPageIua mainPageIua = new MainPageIua(driver);
        mainPageIua.enterToEmailPage("vladyslav.chesalov", "error");
        LoginPage loginPage = new LoginPage(driver);
        softAssert.assertEquals(loginPage.getErrorMessageValue(), "Неверный логин или пароль");
        softAssert.assertAll();
    }
}
