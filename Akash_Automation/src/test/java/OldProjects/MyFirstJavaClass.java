package OldProjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class MyFirstJavaClass {
    @Test
    public void myFirstTest() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        // create a chromeDriver object named driver.
        WebDriver driver = new ChromeDriver();
        // go to google web page
        driver.get("https://opensource-demo.orangehrmlive.com/");
        // Maximize the browser window
        driver.manage().window().maximize();
        // wait for 5 seconds
        Thread.sleep(3000);
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"));
        // close the browser all active window

        String actual_URL = driver.getCurrentUrl();
        System.out.printf("Link: " + actual_URL);
        String desired_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        if(actual_URL.equals(desired_URL))
        {
            System.out.printf("Pass");
        }
        else {
            System.out.printf("Fail");
        }
        String actual_title = driver.getTitle();
        System.out.printf(actual_title);


        driver.quit();
    }

}
