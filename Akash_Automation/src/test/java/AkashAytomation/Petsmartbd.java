package AkashAytomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Petsmartbd {
    public static String browser = "chrome";
    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get("https://www.petsmartbd.com/");
        driver.manage().window().maximize();
        WebElement LoginPage = driver.findElement(By.xpath("/html/body/header/div[1]/div/div/div/div[2]/div/ul/li[2]/a"));
        LoginPage.click();
        WebElement Email = driver.findElement(By.xpath("//input[@id='customer_email']"));
        Email.sendKeys("akashahmed.diu@gmail.com");

        WebElement Password = driver.findElement(By.xpath("//input[@id='customer_password']"));
        Password.sendKeys("Test@1234567");
        Thread.sleep(1000);

        WebElement ClickLogin = driver.findElement(By.xpath("(//input[@name='commit'])[1]"));
        ClickLogin.click();
        Thread.sleep(1000);
        WebElement Search = driver.findElement(By.xpath("//*[@id=\"search\"]/a"));
        Search.click();
        Thread.sleep(1000);

        WebElement Searching = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        Searching.sendKeys("Whiskas Mackerel 1.2KG");
        Thread.sleep(1000);


    }

}
