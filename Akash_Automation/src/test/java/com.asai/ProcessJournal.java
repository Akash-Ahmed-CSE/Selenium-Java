package com.asai;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProcessJournal {

    public static WebDriver driver;

    public static void Security() throws InterruptedException{
        String Branch = "123";
        String baseUrl = "https://192.168.97.56:1107//Programs/P_ProcessJournal.aspx?";

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //Navigate to AMBS PH entity
        driver.get(baseUrl);
        driver.manage().window().maximize();

        //Give permission to Advance Security
        WebElement advance = driver.findElement(By.xpath("//*[@id=\'details-button\']"));
        advance.click();
        WebElement permission = driver.findElement(By.xpath("//*[@id=\'proceed-link\']"));
        permission.click();
        Thread.sleep(3000);

        //Login As admin
        driver.findElement(By.id("login_UserName")).sendKeys(new String[]{"admin"});
        driver.findElement(By.id("login_Password")).sendKeys(new String[]{"Test@123"});
        driver.findElement(By.id("login_LoginImageButton")).click();
        Thread.sleep(2000);

        var wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //var fluentWait = new FluentWait<>(driver);

        //Select Branch
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_ddlBranchId")));
        Select ddlBranchId = new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlBranchId")));
        wait.until(webDriver -> ddlBranchId.getOptions().size() > 1);
        ddlBranchId.selectByValue(Branch);

        //WebElement branch = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlBranchId']/option[@value='" + Branch + "']"));
        //branch.click();
        Thread.sleep(1000);


        driver.findElement(By.xpath("//a[@href=\"javascript:commonService.doPostBack('REFRESH');\"]")).click();

        //Check Message
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#ctl00_lblMessage")));
        WebElement message = driver.findElement(By.cssSelector("#ctl00_lblMessage"));
        String New_message = message.getText();
        //System.out.println(New_message);
        //Thread.sleep(2000);

        if (New_message.startsWith("Error: User") || New_message.startsWith("No journal pending for processing")) {
            //Select Branch

            Thread.sleep(1000);
            driver.quit();
        } else {

            //Click Authorize ALL
            driver.findElement(By.xpath("//a[normalize-space()='Authorize All']")).click();
            System.out.println("Authorize All");
            Thread.sleep(2000);

            //Click Process
            driver.findElement(By.xpath("//a[@href=\"javascript:commonService.doPostBack('ADD');\"]")).click();
            System.out.println("Process");
            Thread.sleep(2000);
            driver.quit();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // load test on group transaction
//        loadGroupTransaction();

        // load test on loan disbursement
        Security();
    }
}
