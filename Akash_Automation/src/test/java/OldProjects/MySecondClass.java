package OldProjects;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

public class MySecondClass {
    public static String browser = "chrome";
    public static WebDriver driver;
@Test
    public void MyDarzOrder()throws InterruptedException {

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
            driver.get("https://www.daraz.com.bd/");
            driver.manage().window().maximize();
            WebElement Login = driver.findElement(By.xpath("//*[@id=\"anonLogin\"]/a"));
            Login.click();
            WebElement PhoneNumber = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/form/div/div[1]/div[1]/input"));
            PhoneNumber.sendKeys("");
            WebElement Password = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/form/div/div[1]/div[2]/input"));
            Password.sendKeys("");
            WebElement Login1 = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/form/div/div[2]/div[1]/button"));
            Login1.click();
            Thread.sleep(1500);

            WebElement Search1 = driver.findElement(By.xpath("//input[@id='q']"));
            Search1.click();
            Search1.sendKeys("Laptop");
            Thread.sleep(1500);
            Search1.submit();

            WebElement CheckBoxASUS = driver.findElement(By.partialLinkText("MSI MODERN 15 A5M AMD RYZEN 5500U"));
            CheckBoxASUS.click();

            WebElement qty = driver.findElement(By.xpath("//*[@id=\"module_quantity-input\"]/div/div/div/div/div[1]/a[1]/span/i"));
            qty.click();
            Thread.sleep(1500);
            qty.click();
            Thread.sleep(1500);
            WebElement AddToCart = driver.findElement(By.xpath("//*[@id=\"module_add_to_cart\"]/div/button[2]/span/span"));
            AddToCart.click();

            Thread.sleep(3000);
            driver.close();
        }
    }
