package OldProjects;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
public class AMBS_Security_transaction2 {
    public static String browser = "chrome";
    public static WebDriver driver;
    @Test
    public void Security() throws InterruptedException{
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
        driver.get("https://192.168.97.56:1105/Login.aspx");
        driver.manage().window().maximize();
        WebElement advance = driver.findElement(By.xpath("//*[@id=\'details-button\']"));
        advance.click();

        WebElement permission = driver.findElement(By.xpath("//*[@id=\'proceed-link\']"));
        permission.click();
        Thread.sleep(3000);
        WebElement Login = driver.findElement(By.xpath("//*[@id=\'login_UserName\']"));
        Login.sendKeys("admin");
        WebElement Pass = driver.findElement(By.xpath("//*[@id=\'login_Password\']"));
        Pass.sendKeys("Test@123");

        WebElement LoginClick = driver.findElement(By.xpath("//*[@id=\'login_LoginImageButton\']"));
        LoginClick.click();
        Thread.sleep(2000);

        //Click on Security Transaction
        WebElement Click_SecurityTransaction = driver.findElement(By.xpath("//*[@id=\'ext-element-15\']/span"));
        Click_SecurityTransaction.click();
        Thread.sleep(2000);
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//*[@id=\'ctl00_mnuPageToolbarn1\']/table/tbody/tr/td/a")).click();
        driver.switchTo().parentFrame();
        Thread.sleep(2000);

        driver.switchTo().frame(1);


        WebElement Branch = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlBranchId']/option[@value='246']"));
        Branch.click();
        Thread.sleep(2000);
        WebElement LoanOfficer = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlP_LoanOfficerId']/option[@value='316583']"));
        LoanOfficer.click();
        Thread.sleep(2000);



        WebElement Group = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlP_GroupId']/option[@value='3159987']"));
        Group.click();
        Thread.sleep(2000);



        WebElement Member = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlP_MemberId']/option[@value='33078106']"));
        Member.click();
        Thread.sleep(2000);


        WebElement Process = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlProcess']/option[@value='1']"));
        Process.click();
        Thread.sleep(2000);


        WebElement Type = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlType']/option[@value='4']"));
        Type.click();
        Thread.sleep(2000);


        WebElement Amount = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_txtAmount\"]"));
        Amount.sendKeys("22");


        WebElement description = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_txtDescription\"]"));
        description.sendKeys("Test@123");

        driver.switchTo().defaultContent();
        Thread.sleep(2000);

        WebElement SaveAndClose = driver.findElement(By.xpath("//iframe[@id='box-1203']"));
        SaveAndClose.click();
        Thread.sleep(4000);
        driver.close();
    }
}
