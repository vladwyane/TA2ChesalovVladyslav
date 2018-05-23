
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

public class ChromeTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    @BeforeSuite
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        Reporter.log("Before Suit executed", 1, true);
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        //driver.manage().window().fullscreen();
        driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, 3);
        softAssert = new SoftAssert();
        driver.get("http://i.ua");
        Reporter.log("Before Method executed", 1, true);
    }

    @AfterMethod
    public void teardown() {
        Reporter.log("After Method executed", 1, true);
        if (driver != null) {
            driver.quit();
        }

    }

    @Test(groups = "regression")
    public void testSelect() throws InterruptedException {
        driver.findElement(By.id("email_create")).sendKeys("dwyane@i.ua");
        driver.findElement(By.id("SubmitCreate")).click();
        Thread.sleep(2000);
        Select selDr = new Select(driver.findElements(By.id("days")).get(0));
        selDr.selectByValue("10");
        Thread.sleep(10000);
        Select sel = new Select(driver.findElements(By.id("years")).get(0));
        sel.selectByValue("2016");
        Thread.sleep(5000);

        driver.findElement(By.id("user_pass")).sendKeys("V123456789ch");
        driver.findElement(By.cssSelector("input[value='sign in']")).click();

        driver.findElement(By.cssSelector(".link-open")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='type-select']/following-sibling::span")).click();
        driver.findElement(By.cssSelector("span[data-index='1']")).click();
        driver.findElement(By.cssSelector("input[value*='Save']")).click();
        driver.findElement(By.cssSelector("#date")).sendKeys("02/01/1988");
        Thread.sleep(5000);


    }




    @Test(groups = "regression")
    public void testSearch() throws InterruptedException {
        driver.findElement(By.id("user_login")).sendKeys("vladwyane@gmail.com");
        driver.findElement(By.id("user_pass")).sendKeys("V123456789ch");
        driver.findElement(By.cssSelector("input[value='sign in']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".link-open")).click();
        Thread.sleep(2000);
        Select selDr = new Select(driver.findElements(By.id("type-select")).get(0));
        selDr.selectByValue("trust");
        driver.findElement(By.cssSelector("input[value*='Save']")).click();
        Thread.sleep(10000);

        driver.findElement(By.xpath("//*[@id='type-select']/following-sibling::span")).click();
        driver.findElement(By.cssSelector("span[data-index='1']")).click();
        driver.findElement(By.cssSelector("input[value*='Save']")).click();
        driver.findElement(By.cssSelector("#date")).sendKeys("02/01/1988");
        Thread.sleep(5000);


    }

    @Test
    public void hoverTest() {
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart")))).build().perform();
        softAssert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#button_order_cart"))).isDisplayed());
    }


    @Test
    public void testSearchElements() throws InterruptedException {


       MainPageIua mainPageIua = new MainPageIua(driver);
       mainPageIua.fillingLoginField("sdsadas");
        Thread.sleep(3000);
       mainPageIua.fillingPasswordField("sdadasarqewr");

        Thread.sleep(10000);
    }


    @Test
    public void test() {
        softAssert.assertEquals(
                wait
                        .until(ExpectedConditions
                                .presenceOfAllElementsLocatedBy(
                                        By.cssSelector("a.button[title='Add to cart']")))
                        .size(), 12,
                "Size not equal");
        softAssert.assertEquals(
                wait
                        .until(ExpectedConditions
                                .presenceOfAllElementsLocatedBy(
                                        By.cssSelector("a.button[title='Add to cart']")))
                        .size(), 13,
                "Size not equal");
        softAssert.assertEquals(
                wait
                        .until(ExpectedConditions
                                .presenceOfAllElementsLocatedBy(
                                        By.cssSelector("a.button[title='Add to cart']")))
                        .size(), 14,
                "Size not equal");
        softAssert.assertAll();


    }

}