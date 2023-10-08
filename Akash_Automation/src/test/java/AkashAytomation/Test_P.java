package AkashAytomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Test_P {


    public static WebDriver driver;

    @Test
    public void Security() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Navigate to AMBS GH entity
        driver.get("https://192.168.97.56:1107/");
        driver.manage().window().maximize();
        //Give permission to Advance Security
        WebElement advance = driver.findElement(By.xpath("//*[@id=\'details-button\']"));
        advance.click();
        WebElement permission = driver.findElement(By.xpath("//*[@id=\'proceed-link\']"));
        permission.click();
        Thread.sleep(3000);
        //Login As admin
        WebElement Login = driver.findElement(By.xpath("//*[@id=\'login_UserName\']"));
        Login.sendKeys("admin");
        WebElement Pass = driver.findElement(By.xpath("//*[@id=\'login_Password\']"));
        Pass.sendKeys("Test@123");

        WebElement LoginClick = driver.findElement(By.xpath("//*[@id=\'login_LoginImageButton\']"));
        LoginClick.click();
        Thread.sleep(2000);

        //Click on Process Journal
        WebElement ProcessJournal = driver.findElement(By.linkText("Process Programs Journal"));
        ProcessJournal.click();
        Thread.sleep(2000);

        //Switch to Iframe
        driver.switchTo().frame("Programs/P_ProcessJournal.aspx?propertyname=P_PROCESSJOURNAL_IFrame");

        //Select Branch
        WebElement branch = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlBranchId']/option[@value='113']"));
        branch.click();
        Thread.sleep(1000);

        //REFRESH
        driver.findElement(By.xpath("//a[@href=\"javascript:commonService.doPostBack('REFRESH');\"]")).click();



        //Click Authorize ALL
        driver.findElement(By.xpath("//a[normalize-space()='Authorize All']")).click();
        Thread.sleep(2000);


        Thread.sleep(1000);
        driver.quit();

    }
}