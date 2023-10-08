package OldProjects;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
public class My_Learning_Of_Iframe {
    public static String browser = "chrome";
    public static WebDriver driver;
    @Test
    public void Iframe() throws InterruptedException{
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
        driver.get("https://jqueryui.com/checkboxradio");

        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("label[for='radio-1']")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("label[for='radio-2']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("label[for='checkbox-1']")).click();
        driver.switchTo().defaultContent();


        By iframeLocateBy = By.className("demo-frame");
        driver.switchTo().frame(driver.findElement(iframeLocateBy));
        driver.findElement(By.cssSelector("label[for='checkbox-2']")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//a[normalize-space()='API documentation'])[1]")).click();

        Thread.sleep(3000);
        driver.close();
    }
}
