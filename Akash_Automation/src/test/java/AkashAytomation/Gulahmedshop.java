package AkashAytomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;

public class Gulahmedshop {
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
        //Navigate to the website
        driver.get("https://www.gulahmedshop.com/");
        //Maximizing
        driver.manage().window().maximize();
        WebElement Login = driver.findElement(By.xpath("//div[@id='authorization-trigger']"));
        Login.click();
        WebElement Email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        Email.sendKeys("akashahmed.diu@gmail.com");
        WebElement Password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        Password.sendKeys("Test123#");
        Thread.sleep(1500);

        WebElement Loginbtn = driver.findElement(By.xpath("//button[@name='send']//span[contains(text(),'Login')]"));
        Loginbtn.click();
        Thread.sleep(1500);
        WebElement Search1 = driver.findElement(By.xpath("//input[@id='search']"));
        Search1.sendKeys("Tshirt");
        Search1.submit();

        WebElement SelectItem = driver.findElement(By.xpath("//*[@id=\"category-products-grid\"]/ol/li[1]/div/div[1]/div[1]/a"));
        SelectItem.click();
        Thread.sleep(4000);
        WebElement addtocart = driver.findElement(By.xpath("//div[@class='fieldset']//div[@class='actions']"));


        //Creating object of an Actions class
        Actions action = new Actions(driver);
        //Performing the mouse hover action on the target element.
        action.moveToElement(addtocart).perform();
        addtocart.click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//span[normalize-space()='View Cart'])[1]")).click();

        Thread.sleep(1500);

        driver.close();
    }
}
