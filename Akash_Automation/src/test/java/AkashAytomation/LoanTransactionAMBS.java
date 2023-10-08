package AkashAytomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class LoanTransactionAMBS {

    public static WebDriver driver;
    @Test
    public void Security() throws InterruptedException{

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Navigate to AMBS GH entity
        driver.get("https://192.168.97.56:1105/Login.aspx");
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

        //Click on Loan Transaction
        WebElement Click_Loan_Transaction = driver.findElement(By.xpath("//*[@id=\'ext-element-15\']/span"));
        Click_Loan_Transaction.click();
        Thread.sleep(2000);
        //Switch to Iframe
        driver.switchTo().frame("Programs/P_LoanTransactionList.aspx?propertyname=P_LOANTRANSACTION LIST_IFrame");
        //Click on Loan Transaction Add
        driver.findElement(By.xpath("//*[@id=\'ctl00_mnuPageToolbarn1\']/table/tbody/tr/td/a")).click();
        driver.switchTo().parentFrame();
        Thread.sleep(2000);
        //Switch to Iframe(1);
        driver.switchTo().frame("Programs/P_LoanTransactionAdd.aspx_IFrame");

        //Select Branch
        WebElement Branch = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlBranchId']/option[@value='246']"));
        Branch.click();
        Thread.sleep(2000);
        //Select Loan Officer
        WebElement LoanOfficer = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlP_LoanOfficerId']/option[@value='316583']"));
        LoanOfficer.click();
        Thread.sleep(2000);


        //Select Group
        WebElement Group = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlP_GroupId']/option[@value='3159987']"));
        Group.click();
        Thread.sleep(2000);


        //Select Member
        WebElement Member = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlP_MemberId']/option[@value='33078106']"));
        Member.click();
        Thread.sleep(2000);

        //Select Process
        WebElement Process = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlProcess']/option[@value='1']"));
        Process.click();
        Thread.sleep(2000);

        //Select Type
        WebElement Type = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlType']/option[@value='4']"));
        Type.click();
        Thread.sleep(2000);

        //Give Amount
        WebElement Amount = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_txtAmount\"]"));
        Amount.sendKeys("22");

        //Give description
        WebElement description = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_txtDescription\"]"));
        description.sendKeys("Test@123");

        //Click on Save and close button
        driver.findElement(By.xpath("//a[contains(text(),'Save & Close')]")).click();


        Thread.sleep(1000);
        driver.quit();

    }
}
