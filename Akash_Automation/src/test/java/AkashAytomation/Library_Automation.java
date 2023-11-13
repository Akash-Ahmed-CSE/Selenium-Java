package AkashAytomation;
import org.openqa.selenium.JavascriptExecutor;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
public class Library_Automation {
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
        //Navigate to the website
        driver.get("https://akash-ahmed-cse.github.io/Websites.github.io/Library/index.html");
        //Maximizing
        driver.manage().window().maximize();
        // Scroll down slowly using Actions class
        slowScrollDown(driver);

        // Wait for a moment to see the effect
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Scroll back to the top slowly
        slowScrollUp(driver);

        // Wait for a moment to see the effect
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement BuyNow = driver.findElement(By.xpath("//button[normalize-space()='Buy Now']"));
        BuyNow.click();

        driver.findElement(By.xpath("//input[@placeholder='First name']")).sendKeys("Akash");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@placeholder='Last name']")).sendKeys("Ahmed");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@placeholder='Age']")).sendKeys("25");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys("01743304573");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@placeholder='E-mail']")).sendKeys("akashahmed.diu@gmail.com");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Test@123");
        Thread.sleep(1500);


        driver.quit();
    }

    private static void slowScrollDown(WebDriver driver) {
        Actions actions = new Actions(driver);

        WebElement element = driver.findElement(By.tagName("body"));

        for (int i = 0; i < 6; i++) {
            actions.moveToElement(element).sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).pause(1000).build().perform();
        }
    }

    private static void slowScrollUp(WebDriver driver) {
        Actions actions = new Actions(driver);

        WebElement element = driver.findElement(By.tagName("body"));

        for (int i = 0; i < 6; i++) {
            actions.moveToElement(element).sendKeys(org.openqa.selenium.Keys.PAGE_UP).pause(500).build().perform();
        }
    }
}

