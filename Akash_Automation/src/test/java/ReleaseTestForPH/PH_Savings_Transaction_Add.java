package ReleaseTestForPH;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class PH_Savings_Transaction_Add {

    public static WebDriver driver;
    @Test
    public void Security() throws InterruptedException{


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Navigate to AMBS PH entity
        //driver.get("http://192.168.100.77/Programs/P_SavingsTransactionAdd.aspx");
        driver.get("https://192.168.97.56:1107/Programs/P_SavingsTransactionAdd.aspx");
        driver.manage().window().maximize();

        //Give permission to Advance Security
        WebElement advance = driver.findElement(By.xpath("//*[@id=\'details-button\']"));
        advance.click();
        WebElement permission = driver.findElement(By.xpath("//*[@id=\'proceed-link\']"));
        permission.click();
        Thread.sleep(1000);

        //Login As admin
        driver.findElement(By.id("login_UserName")).sendKeys(new String[]{"admin"});
        driver.findElement(By.id("login_Password")).sendKeys(new String[]{"Test@123"});
        driver.findElement(By.id("login_LoginImageButton")).click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //var fluentWait = new FluentWait<>(driver);

        Thread.sleep(1000);


        // Select Branch
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_ddlBranchId")));
        Select ddlBranchId = new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlBranchId")));
        wait.until(webDriver -> ddlBranchId.getOptions().size() > 1);
        ddlBranchId.selectByValue("165");


        // Select Loan Officer
        By lo = By.id("ctl00_ContentPlaceHolder1_ddlP_LoanOfficerId");
        wait.until(ExpectedConditions.presenceOfElementLocated(lo));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(lo))));
        Select ddlPLoanOfficerId = new Select(driver.findElement(lo));
        wait.until(webDriver -> ddlPLoanOfficerId.getOptions().size() > 1);
        ddlPLoanOfficerId.selectByValue("28412");



        // Select Group
        By group = By.id("ctl00_ContentPlaceHolder1_ddlP_GroupId");
        wait.until(ExpectedConditions.presenceOfElementLocated(group));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(group))));
        Select ddlPGroupId = new Select(driver.findElement(group));
        wait.until(webDriver -> ddlPGroupId.getOptions().size() > 1);
        ddlPGroupId.selectByValue("113322");

        // Select member
        By member = By.id("ctl00_ContentPlaceHolder1_ddlP_MemberId");
        wait.until(ExpectedConditions.presenceOfElementLocated(member));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(member))));
        Select ddlPMemberId = new Select(driver.findElement(member));
        wait.until(webDriver -> ddlPMemberId.getOptions().size() > 1);
        ddlPMemberId.selectByValue("2767085");




        // Select Process
        By process = By.id("ctl00_ContentPlaceHolder1_ddlProcess");
        wait.until(ExpectedConditions.presenceOfElementLocated(process));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(process))));
        Select ddlPProcessId = new Select(driver.findElement(process));
        wait.until(webDriver -> ddlPProcessId.getOptions().size() > 1);
        ddlPProcessId.selectByValue("1");


        // Select Type
        By type = By.id("ctl00_ContentPlaceHolder1_ddlType");
        wait.until(ExpectedConditions.presenceOfElementLocated(type));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(type))));
        Select ddlPtypeId = new Select(driver.findElement(type));
        wait.until(webDriver -> ddlPtypeId.getOptions().size() > 1);
        ddlPtypeId.selectByValue("8");

        Thread.sleep(1000);
        //Date
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtDate")).clear();
        Thread.sleep(2000);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtDate")).sendKeys(new String[]{"22/05/2023"});
        Thread.sleep(1000);


        //input Amount
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtAmount")).sendKeys(new String[]{"100"});
        Thread.sleep(1000);
        driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtDescription")).sendKeys(new String[]{"Test"});
        Thread.sleep(1000);


        //Click on Save and close button
        driver.findElement(By.xpath("//a[contains(text(),'Save & Close')]")).click();



        Thread.sleep(6000);

        driver.quit();

    }
}
