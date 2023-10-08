package OldProjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class moveToElement_Class {

    //public static String browser = "chrome";

    public static WebDriver driver;
    @Test
    public void Hover() throws InterruptedException{

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        Actions actions = new Actions(driver);
        driver.get("https://www.w3schools.com/sql/");

        WebElement catElement = driver.findElement(By.cssSelector("body > div:nth-child(11) > div:nth-child(1) > div:nth-child(1) > h2:nth-child(32)"));
        actions.moveToElement(catElement).perform();
        //actions.scrollByAmount(600,1600);

        WebElement Click1 = driver.findElement(By.xpath("//a[normalize-space()='SQL Keyword Reference']"));
        WebElement Click2 = driver.findElement(By.xpath("//a[normalize-space()='MYSQL Functions']"));

        actions.keyDown(Keys.CONTROL).click(Click1).click(Click2).build().perform();

        Thread.sleep(3000);
        driver.close();
    }
}
