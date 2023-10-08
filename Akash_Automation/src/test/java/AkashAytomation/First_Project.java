package AkashAytomation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class First_Project {
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
        driver.get("https://www.daraz.com.bd/");
        driver.manage().window().maximize();
        WebElement Login = driver.findElement(By.xpath("//*[@id=\"anonLogin\"]/a"));
        Login.click();
        WebElement PhoneNumber = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/form/div/div[1]/div[1]/input"));
        PhoneNumber.sendKeys("01303647718");
        WebElement Password = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/form/div/div[1]/div[2]/input"));
        Password.sendKeys("Test123#");
        Thread.sleep(1500);



        Thread.sleep(3000);
        driver.close();
    }
}
