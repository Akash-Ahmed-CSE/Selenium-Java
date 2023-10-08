package AkashAytomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class PH_Member_Optimized {

    //Function to generate RandomPhoneNumber
    public static String generateRandomPhoneNumber() {
        Random random = new Random();

        // Generate a random 9-digit number
        int randomPart = random.nextInt(900_000_000) + 100_000_000; // 100,000,000 to 999,999,999

        // Combine "09" as a prefix with the random number
        String phoneNumber = "09" + randomPart;

        return phoneNumber;
    }

    //Function to Generate Random KYC
    public static String generateKYC() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int randomPart = random.nextInt(900) + 100; // Generate a random integer between 100 and 999
            sb.append(randomPart);

            if (i < 2) {
                sb.append("-"); // Add a hyphen between groups of three numbers
            }
        }

        return sb.toString();
    }

    public static WebDriver driver;
    @Test
    public void Security() throws InterruptedException{


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Navigate to AMBS GH entity
        //driver.get("http://192.168.100.77");
        driver.get("https://192.168.97.56:1107/");
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

        var wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //var fluentWait = new FluentWait<>(driver);

        Thread.sleep(2000);

        //Click on Member
        WebElement Member = driver.findElement(By.xpath("//a[@id='ext-element-13']//span[contains(text(),'Member')]"));
        Member.click();
        Thread.sleep(1000);


        //Switch to Iframe
        driver.switchTo().frame("Programs/P_MemberList.aspx?propertyname=P_MEMBER LIST_IFrame");
        //Click on Member Add
        driver.findElement(By.xpath("//*[@id=\'ctl00_mnuPageToolbarn1\']/table/tbody/tr/td/a")).click();
        driver.switchTo().parentFrame();
        Thread.sleep(1000);

        //Switch to Iframe(1);
        driver.switchTo().frame("Programs/P_MemberAdd.aspx_IFrame");


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
        ddlPLoanOfficerId.selectByValue("30679");




//        //Select Group
//        WebElement Group = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlP_GroupId']/option[@value='114297']"));
//        Group.click();
//        Thread.sleep(1000);

        // Select Group
        By group = By.id("ctl00_ContentPlaceHolder1_ddlP_GroupId");
        wait.until(ExpectedConditions.presenceOfElementLocated(group));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(group))));
        Select ddlPGroupId = new Select(driver.findElement(group));
        wait.until(webDriver -> ddlPGroupId.getOptions().size() > 1);
        ddlPGroupId.selectByValue("114297");

        //Select KYC ID Type
        WebElement KYC = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlNationalIdType']/option[@value='1']"));
        KYC.click();
        Thread.sleep(1000);



//        By KYC = By.id("ctl00_ContentPlaceHolder1_ddlNationalIdType");
//        wait.until(ExpectedConditions.presenceOfElementLocated(KYC));
//        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(group))));
//        Select KYCId = new Select(driver.findElement(group));
//        wait.until(webDriver -> KYCId.getOptions().size() > 1);
//        KYCId.selectByIndex(1);



        //Input KYC ID
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtNationalIdNumber']")).sendKeys(String.valueOf(generateKYC()));
        Thread.sleep(2000);

        //Input First Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtFirstName']")).sendKeys("AAAAA");
        Thread.sleep(1000);

        //Input First Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtFirstName']")).sendKeys("AAAAA");
        Thread.sleep(1000);


        //Input Middle Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtMiddleName']")).sendKeys("Ahmed");
        Thread.sleep(1000);

        //Input Last Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtLastName']")).sendKeys("Test");
        Thread.sleep(1000);

        //Input Maiden Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtMaidenName']")).sendKeys("Test");
        Thread.sleep(1000);

        //Select DOB
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtDateOfBirth']")).sendKeys("11/09/1995");
        Thread.sleep(1000);


        //Select Proof Of Age
        driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_cblProofOfAge_0")).click();
        Thread.sleep(1000);

        //Select SEX
        driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlSex']/option[@value='1']")).click();
        Thread.sleep(1000);

        //Select DOB
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtDateOfBirth']")).sendKeys("11/09/1995");
        Thread.sleep(1000);

        // Select Marital Status
        WebElement Marital_Statu = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlMaritalStatus']/option[@value='2']"));
        Marital_Statu.click();
        Thread.sleep(1000);

        //Input Mother Maiden First Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtMotherMaidenFirst']")).sendKeys("First");
        Thread.sleep(1000);

        //Input Mother Maiden Middle Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtMotherMaidenMiddle']")).sendKeys("Middle");
        Thread.sleep(1000);

        //Input Mother Maiden Last Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtMotherMaidenLast']")).sendKeys("Middle");
        Thread.sleep(1000);

        //Input Secondary Co-Maker Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtSCOName']")).sendKeys("Middle");
        Thread.sleep(1000);

        //Input Secondary Co-Maker Address
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtSCOAddress']")).sendKeys("Middle");
        Thread.sleep(1000);

        //Input Secondary Co-Maker Address
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtSCOMobile']")).sendKeys("09898767431");
        Thread.sleep(1000);

        //Input Guarantor Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtGuarantorName']")).sendKeys("GGGG");
        Thread.sleep(1000);

        //Input Guarantor Date Of Birth
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtGuarantorDateOfBirth']")).sendKeys("12/09/1995");
        Thread.sleep(1000);

        //Select Guarantor Relation
        WebElement Guarantor_Relation = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlGuarantorRelation']/option[@value='Father']"));
        Guarantor_Relation.click();
        Thread.sleep(1000);

        //Input Guarantor Contact Number
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtGuarantorContactNumber']")).sendKeys("09898767432");
        Thread.sleep(1000);

        //Input Guarantor Address
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtGuarantorAddress']")).sendKeys("ABC");
        Thread.sleep(1000);

        //Input Guarantor KYC ID:
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtGuarantorNationalIdNumber']")).sendKeys("521-3213-312");
        Thread.sleep(1000);


        //Input Additional Guarantor Name
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtAdditionalGuarantorName']")).sendKeys("GGGG");
        Thread.sleep(1000);

        //Input Additional Guarantor Date Of Birth
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtAdditionalGuarantorDateOfBirth']")).sendKeys("12/09/1995");
        Thread.sleep(1000);

        //Select Additional Guarantor Relation
        WebElement Additional_Guarantor_Relation = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlAdditionalGuarantorRelation']/option[@value='Mother']"));
        Additional_Guarantor_Relation.click();
        Thread.sleep(2000);

        //Input Additional Guarantor Contact Number
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtAdditionalGuarantorContactNumber']")).sendKeys("09898761432");
        Thread.sleep(1000);

        //Input Additional Guarantor Address
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtAdditionalGuarantorAddress']")).sendKeys("ABC");
        Thread.sleep(1000);

        //Input Additional Guarantor KYC ID:
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtAdditionalGuarantorNationalIdNumber']")).sendKeys("521-3213-512");
        Thread.sleep(1000);

        //Select Religion Type
        WebElement Religion = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlReligion']/option[@value='1']"));
        Religion.click();
        Thread.sleep(1000);



        //Select Caste
        WebElement Caste = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlCaste']/option[@value='1']"));
        Caste.click();
        Thread.sleep(1000);


        //Select Location Type
        WebElement Location_Type = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlLocationType']/option[@value='1']"));
        Location_Type.click();
        Thread.sleep(1000);

        //Select Poverty Level
        WebElement Poverty_Level = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlPovertyLevel']/option[@value='1']"));
        Poverty_Level.click();
        Thread.sleep(1000);

        //Select Special Identification
        WebElement Special_Identification = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlSpecialIdentification']/option[@value='1']"));
        Special_Identification.click();
        Thread.sleep(2000);

        //Select Residence Typ
        WebElement Residence_Typ = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlResidenceType']/option[@value='Own']"));
        Residence_Typ.click();
        Thread.sleep(2000);

        //Select No. Of Room
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtNumberOfRooms']")).sendKeys("5");
        Thread.sleep(2000);


        //Select Present Proof Of Address
        driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_cblProofOfAddress_0")).click();
        Thread.sleep(1000);

        //Select Permanent Proof Of Address
        driver.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_cblPermanentProofOfAddress_0")).click();
        Thread.sleep(1000);

        //Select Present Govt. Region
        WebElement Present_Govt_Region = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlPresentGovtRegion']/option[@value='1']"));
        Present_Govt_Region.click();
        Thread.sleep(1000);

        //Input Present CellPhone
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtPresentContact']")).sendKeys(generateRandomPhoneNumber());
        Thread.sleep(1000);

        //Input Present House:
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtPresentHouse']")).sendKeys("dasds");
        Thread.sleep(1000);

        //Input Present Nearest Location:
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtPresentNearestloc']")).sendKeys("dasds");
        Thread.sleep(1000);

        //Input Present Post Code
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtPresentPostCode']")).sendKeys("3123");
        Thread.sleep(1000);

        //Input Present Phone
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtPresentPhone']")).sendKeys("09898887382");
        Thread.sleep(1000);

        //Select Permanent Govt. Region
        WebElement Permanent_Govt_Region = driver.findElement(By.xpath("//select[@id='ctl00_ContentPlaceHolder1_ddlPermanentGovtRegion']/option[@value='1']"));
        Permanent_Govt_Region.click();
        Thread.sleep(2000);

        //Input Permanent CellPhone
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtPermanentCellPhone']")).sendKeys(generateRandomPhoneNumber());
        Thread.sleep(1000);

        //Input Permanent House:
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_textPermanentHouse']")).sendKeys("dasds");
        Thread.sleep(1000);

        //Input Permanent Nearest Location
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtPermanentNearestLoc']")).sendKeys("dasds");
        Thread.sleep(1000);

        //Input Permanent Post Code
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtPermanentPostCode']")).sendKeys("3113");
        Thread.sleep(1000);

        //Input Permanent Phone
        driver.findElement(By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtPermanentPhone']")).sendKeys("09898187382");
        Thread.sleep(1000);

        //Click on Save and close button
        driver.findElement(By.xpath("//a[contains(text(),'Save & Close')]")).click();

        Thread.sleep(6000);


        driver.quit();

    }
}
