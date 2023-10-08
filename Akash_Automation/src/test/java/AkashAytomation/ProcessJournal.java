package AkashAytomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ProcessJournal {

    public static WebDriver driver;
    @Test
    public void Security() throws InterruptedException{
        int[] Branches = {330, 433, 445};
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

        for(int i = 0; i<=Branches.length-1; i++){
        //Select Branch
        WebElement branch = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlBranchId']/option[@value='" + Branches[i] + "']"));
        branch.click();
        Thread.sleep(1000);


        driver.findElement(By.xpath("//a[@href=\"javascript:commonService.doPostBack('REFRESH');\"]")).click();


            //Check Message
            WebElement message = driver.findElement(By.cssSelector("#ctl00_lblMessage"));
            String New_message = message.getText();
            //System.out.println(New_message);
            Thread.sleep(2000);

            if(New_message.startsWith("Error: User") || New_message.startsWith("No journal pending for processing")){
                //Select Branch
                WebElement Branch2 = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlBranchId']/option[@value='" + Branches[i+1] + "']"));
                Branch2.click();
                Thread.sleep(1000);

            }
            else{

                //Click Authorize ALL
                driver.findElement(By.xpath("//a[normalize-space()='Authorize All']")).click();
                System.out.println("Authorize All");
                Thread.sleep(2000);

                //Click Process
                driver.findElement(By.xpath("//a[@href=\"javascript:commonService.doPostBack('ADD');\"]")).click();
                System.out.println("Authorize All");
                Thread.sleep(2000);

            }
        }

        Thread.sleep(1000);
        driver.quit();

    }
}
