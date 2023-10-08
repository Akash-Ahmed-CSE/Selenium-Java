package AkashAytomation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AkashExamAutomation {
    public static String browser = "chrome";
    public static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        if(browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        WebElement LoginPage = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));
        LoginPage.click();
        WebElement Email = driver.findElement(By.xpath("//*[@id=\'Email\']"));
        Email.sendKeys("akash@gmail.com");

        WebElement Password = driver.findElement(By.xpath("//*[@id=\'Password\']"));
        Password.sendKeys("Test@123");
        Thread.sleep(1000);

        WebElement ClickLogin = driver.findElement(By.xpath("//button[normalize-space()='Log in']"));
        ClickLogin.click();
        Thread.sleep(1000);
        WebElement SearchBox = driver.findElement(By.xpath("//*[@id=\'small-searchterms\']"));
        SearchBox.sendKeys("Samsung");

        WebElement SearchBtn = driver.findElement(By.xpath("//*[@id=\'small-search-box-form\']/button"));
        SearchBtn.click();
        Thread.sleep(1000);

        WebElement AddToCart = driver.findElement(By.xpath("//button[normalize-space()='Add to cart']"));
        AddToCart.click();
        Thread.sleep(5000);

        WebElement ShoppingCart = driver.findElement(By.xpath("//*[@id=\'topcartlink\']/a/span[1]"));
        ShoppingCart.click();
        Thread.sleep(1000);

        WebElement RemoveCart = driver.findElement(By.xpath("//*[@id=\'shopping-cart-form\']/div[1]/table/tbody/tr/td[7]/button"));
        RemoveCart.click();
        Thread.sleep(1000);

        WebElement LogOut = driver.findElement(By.xpath("//a[normalize-space()='Log out']"));
        LogOut.click();
        Thread.sleep(1000);
        driver.close();

    }

}
