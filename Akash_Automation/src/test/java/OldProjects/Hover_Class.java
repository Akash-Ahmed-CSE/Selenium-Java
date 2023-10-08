package OldProjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Hover_Class {
    public static String browser = "chrome";
    public static String URL = "https://demoqa.com/menu";
    public static WebDriver driver;
    @Test
    public void Hover() throws InterruptedException{
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
        driver.get(URL);
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);
        WebElement MainMenuElement = driver.findElement(By.xpath("//a[normalize-space()='Main Item 2']"));
        actions.clickAndHold(MainMenuElement).build().perform();
        Thread.sleep(3000);

        //driver.findElement(By.xpath("//body/div[@id='app']/div[@class='body-height']/div[@class='container playgound-body']/div[@class='row']/div[@class='col-12 mt-4 col-md-6']/div[@class='nav-menu-container']/ul[@id='nav']/li/ul/li[2]/a[1]")).click();

        WebElement SubMenuElement = driver.findElement(By.xpath("//a[normalize-space()='SUB SUB LIST »']"));
        actions.clickAndHold(SubMenuElement).build().perform();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[normalize-space()='Sub Sub Item 1']")).click();
        Thread.sleep(3000);

        driver.close();
    }
}
