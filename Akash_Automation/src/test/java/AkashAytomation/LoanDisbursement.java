package AkashAytomation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.Duration;
import java.util.Base64;
import java.util.Objects;

public class LoanDisbursement {
    private static Logger logger = LoggerFactory.getLogger(LoanDisbursement.class);
    private final WebDriver driver;
    private JavascriptExecutor js;
    private final String baseUrl = "http://192.168.100.78//Programs/P_LoanAccountAdd.aspx";



    public LoanDisbursement() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;

    }

    public void start(String user, String password, String branchId, String loanOfficerId, String groupId, String memberId, String disburseDate, String principalAmount, String laf) throws InterruptedException {
        // browse url
        driver.get(baseUrl);
//        //Give permission to Advance Security
//        WebElement advance = driver.findElement(By.xpath("//*[@id=\'details-button\']"));
//        advance.click();
//        WebElement permission = driver.findElement(By.xpath("//*[@id=\'proceed-link\']"));
//        permission.click();
//        // Thread.sleep(3000);

        //
        driver.findElement(By.id("login_UserName")).sendKeys(new String[]{user});
        driver.findElement(By.id("login_Password")).sendKeys(new String[]{password});
        driver.findElement(By.id("login_LoginImageButton")).click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        try {
            logger.info("Starting loan disbursement");
            // Check if Branch loaded
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ddlBranchId")));
            Select ddlBranchId = new Select(driver.findElement(By.id("ddlBranchId")));
            wait.until(webDriver -> ddlBranchId.getOptions().size() >= 1);
            ddlBranchId.selectByValue(branchId);
            Thread.sleep(500);

            // Select Loan Officer
            logger.info("Setting Loan Officer ID...");
            By lo = By.id("ddlP_LoanOfficerId");
            wait.until(ExpectedConditions.presenceOfElementLocated(lo));
            try {
                new WebDriverWait(driver, Duration.ofMillis(100)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(lo))));
            } catch (Exception lx) {
                logger.error(lx.getMessage());
            }
            Select ddlPLoanOfficerId = new Select(driver.findElement(lo));
            wait.until(webDriver -> ddlPLoanOfficerId.getOptions().size() >= 1);
            ddlPLoanOfficerId.selectByValue(loanOfficerId);
            Thread.sleep(500);

            // Select Group
            logger.info("Setting Group...");
            By group = By.id("ddlP_GroupId");
            wait.until(ExpectedConditions.presenceOfElementLocated(group));
            try {
                new WebDriverWait(driver, Duration.ofMillis(100)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(group))));
            } catch (Exception gx) {
                logger.error(gx.getMessage());
            }
            Select ddlPGroupId = new Select(driver.findElement(group));
            wait.until(webDriver -> ddlPGroupId.getOptions().size() >= 1);
            ddlPGroupId.selectByValue(groupId);
            Thread.sleep(500);

            // Select Member
            logger.info("Setting Member...");
            By member = By.id("ddlP_MemberId");
            wait.until(ExpectedConditions.presenceOfElementLocated(member));
            try {
                new WebDriverWait(driver, Duration.ofMillis(100)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(member))));
            } catch (Exception gx) {
                logger.error(gx.getMessage());
            }
            Select ddlPMemberId = new Select(driver.findElement(member));
            wait.until(webDriver -> ddlPMemberId.getOptions().size() >= 1);
            ddlPMemberId.selectByValue(memberId);
            Thread.sleep(500);


            // Disburse Date
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_txtDisbursedDate");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
//                    ele.sendKeys(new String[]{disburseDate});
                    js.executeScript("ctl00_ContentPlaceHolder1_txtDisbursedDate.setAttribute('value', '" + disburseDate + "');", new String[]{""});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                logger.error("Error when set disburse date!", e);
                js.executeScript("ctl00_ContentPlaceHolder1_txtDisbursedDate.setAttribute('value', '" + disburseDate + "');", new String[]{""});
            }


            // select scheme
            logger.info("Setting Scheme...");
            By scheme = By.id("ctl00_ContentPlaceHolder1_ddlP_SchemeId");
            wait.until(ExpectedConditions.presenceOfElementLocated(scheme));
            try {
                new WebDriverWait(driver, Duration.ofMillis(100)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(scheme))));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            Select ddlPSchemeId = new Select(driver.findElement(scheme));
            wait.until(webDriver -> ddlPSchemeId.getOptions().size() >= 1);
            ddlPSchemeId.selectByIndex(1); // todo:
            Thread.sleep(500);

            try {
                By accountId = By.id("ctl00_ContentPlaceHolder1_ddlA_AccountId");
                wait.until(ExpectedConditions.presenceOfElementLocated(accountId));
                Select ddlAccountId = new Select(driver.findElement(accountId));
                wait.until(webDriver -> ddlAccountId.getOptions().size() >= 1);
                ddlAccountId.selectByIndex(1);
            } catch (Exception e) {
                logger.error("Error when set account id!", e);
            }

            // Principal Amount
            try {
                Thread.sleep(2000);
                By txt = By.id("txtPrincipalAmount");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                driver.findElement(txt).sendKeys(principalAmount);
                driver.findElement(txt).sendKeys(Keys.TAB);
                var txtTotDis = driver.findElement(By.id("ctl00_ContentPlaceHolder1_txtTotalDisbursedAmount"));
                wait.until(webDriver -> txtTotDis.getAttribute("value").length() != 0);
                Thread.sleep(2000);
            } catch (Exception e) {
                try {
                    logger.warn("Setting principal amount via JS!");
                    js.executeScript("txtPrincipalAmount.setAttribute('value', '" + principalAmount + "');", new String[]{""});
                } catch (Exception jx) {
                    logger.error("Error when set principal amount!", jx);
                }
            }

            //Installment-----
            logger.info("Setting Installment...");
            By Installment = By.id("ctl00_ContentPlaceHolder1_ddlP_InstallmentType");
            wait.until(ExpectedConditions.presenceOfElementLocated(Installment));
            try {
                new WebDriverWait(driver, Duration.ofMillis(500)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(Installment))));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            Select ddlPInstallmentId = new Select(driver.findElement(Installment));
            wait.until(webDriver -> ddlPInstallmentId.getOptions().size() >= 1);
            ddlPInstallmentId.selectByIndex(1); // todo:
            Thread.sleep(2500);

            try {
                By GracePeriod = By.id("ctl00_ContentPlaceHolder1_ddlP_GracePeriod");
                wait.until(ExpectedConditions.presenceOfElementLocated(GracePeriod));
            } catch (Exception e) {
                logger.error("Error ddlGracePeriod id!", e);
            }


            // LAF
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_txtLafNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{laf});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                logger.error("Error when set laf!", e);
                try {
                    js.executeScript("ctl00_ContentPlaceHolder1_txtLafNumber.setAttribute('value', '" + laf + "');", new String[]{""});
                } catch (Exception jx) {
                    logger.error("Error when set laf via JS!", jx);
                }
            }


            // OR Number
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_txtOrNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                js.executeScript("ctl00_ContentPlaceHolder1_txtOrNumber.setAttribute('value', '1');", new String[]{""});
            } catch (Exception e) {
                e.printStackTrace();
            }

            // AR Number
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_txtArNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                js.executeScript("ctl00_ContentPlaceHolder1_txtArNumber.setAttribute('value', '20000');", new String[]{""});
            } catch (Exception e) {
                e.printStackTrace();
            }

//            // Check Number
//            try {
//                By txtCheckNo = By.id("ctl00_ContentPlaceHolder1_txtChequeNumber");
//                wait.until(ExpectedConditions.presenceOfElementLocated(txtCheckNo));
//                var ele = driver.findElement(txtCheckNo);
//                if (ele.isEnabled()) {
//                    ele.sendKeys(new String[]{"123"});
//                    ele.sendKeys(Keys.TAB);
//                }
//            } catch (Exception e) {
//                logger.error("Error when set txtChequeNo!", e);
//                try {
//                    js.executeScript("ctl00_ContentPlaceHolder1_txtChequeNumber.setAttribute('value', '123');", new String[]{""});
//                } catch (Exception jx) {
//                    logger.error("Error when set laf via JS!", jx);
//                }
//            }


            String base64Photo = "";

            try {
                BufferedImage photo = ImageIO.read(new File("D:\\nature.jpg"));
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                ImageIO.write(photo, "jpg", bytes);
                base64Photo = Base64.getEncoder().encodeToString(bytes.toByteArray());

                By txt = By.id("ctl00_ContentPlaceHolder1_hdnImage");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                js.executeScript("ctl00_ContentPlaceHolder1_hdnImage.setAttribute('value', '" + base64Photo + "');", new String[]{""});
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Basic Life Insurance Fund
            try {
                logger.info("Setting OR for BLIP...");
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl04_txtOtherFeeOrNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                logger.error("Error when set BLIP OR number!", e);
                try {
                    js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl04_txtOtherFeeOrNumber.setAttribute('value', '1');", new String[]{""});
                } catch (Exception jx) {
                    logger.error("Error JS!", e);
                }
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl04_txtOtherFeeArNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                try {
                    js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl04_txtOtherFeeArNumber.setAttribute('value', '1');", new String[]{""});
                } catch (Exception jx) {
                    logger.error("Error JS!", e);
                }
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl04_txtOtherFeePolicyNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));

                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                try {
                    js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl04_txtOtherFeePolicyNumber.setAttribute('value', '1');", new String[]{""});
                } catch (Exception jx) {
                    logger.error("Error JS!", jx);
                }
            }

            // Credit Life Insurance
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl05_txtOtherFeeOrNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl05_txtOtherFeeOrNumber.setAttribute('value', '1');", new String[]{""});
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
//                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl05_txtOtherFeeArNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl05_txtOtherFeeArNumber.setAttribute('value', '1');", new String[]{""});
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
//                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl05_txtOtherFeePolicyNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl05_txtOtherFeePolicyNumber.setAttribute('value', '1');", new String[]{""});
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
//                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Hospitalization Care Assistance (HCA) - Premium
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl06_txtOtherFeeOrNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl06_txtOtherFeeOrNumber.setAttribute('value', '1');", new String[]{""});
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl06_txtOtherFeeArNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl06_txtOtherFeeArNumber.setAttribute('value', '1');", new String[]{""});
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl06_txtOtherFeePolicyNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl06_txtOtherFeePolicyNumber.setAttribute('value', '1');", new String[]{""});
            }

            // Retirement Savings Fund
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl07_txtOtherFeeOrNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl07_txtOtherFeeOrNumber.setAttribute('value', '1');", new String[]{""});
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl07_txtOtherFeeArNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl07_txtOtherFeeArNumber.setAttribute('value', '1');", new String[]{""});
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl07_txtOtherFeePolicyNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl07_txtOtherFeePolicyNumber.setAttribute('value', '1');", new String[]{""});
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Verification Fee
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl08_txtOtherFeeOrNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl08_txtOtherFeeOrNumber.setAttribute('value', '1');", new String[]{""});
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl08_txtOtherFeeArNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl08_txtOtherFeeArNumber.setAttribute('value', '1');", new String[]{""});
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_gvOtherFees_ctl08_txtOtherFeePolicyNumber");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
//                js.executeScript("ctl00_ContentPlaceHolder1_gvOtherFees_ctl08_txtOtherFeePolicyNumber.setAttribute('value', '1');", new String[]{""});

                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
                    ele.sendKeys(new String[]{"1"});
                    ele.sendKeys(Keys.TAB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Other fees




            // monthly income
            try {
                By txt = By.id("ctl00_ContentPlaceHolder1_txtMonthlyIncomeAmount");
                wait.until(ExpectedConditions.presenceOfElementLocated(txt));
                var ele = driver.findElement(txt);
                if (ele.isEnabled()) {
//                    ele.sendKeys(new String[]{"20000"});
//                    ele.sendKeys(Keys.TAB);
                    js.executeScript("ctl00_ContentPlaceHolder1_txtMonthlyIncomeAmount.setAttribute('value', '20000');", new String[]{""});
                }
            } catch (Exception e) {
                logger.error("Error when set monthly income!", e);
                try {
                    js.executeScript("ctl00_ContentPlaceHolder1_txtMonthlyIncomeAmount.setAttribute('value', '20000');", new String[]{""});
                } catch (Exception jx) {
                    logger.error("Error when set monthly income amount!", jx);
                }
            }



            try {
                logger.info("Disbursing loan for member: " + memberId);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_mnuPageToolbarn1")));
                var save = driver.findElement(By.id("ctl00_mnuPageToolbarn1"));
                save.click();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (
                Exception ex) {
            ex.printStackTrace();
        }


        try {
            logger.info("Analyzing loan disbursement for member: " + memberId);
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            logger.info("MSG : " + alertText);

            if (alertText.equals("Record Saved Successfully.")) {
                alert.accept();
//                WDS.sampleResult.setResponseMessage(alertText + "(ID:" + WDS.vars.get("MemberID") + ")");
//                WDS.sampleResult.setSuccessful(true);
            } else {
//                WDS.sampleResult.setResponseMessage(msg + "(ID:" + WDS.vars.get("MemberID") + ")");
//                WDS.sampleResult.setSuccessful(false);
//                break;
            }
        } catch (
                Exception ex) {
            try {
                var msg = driver.findElement(By.id("ctl00_lblMessage")).getText();
                logger.error("MSG : " + msg);

                if (msg.startsWith("Error: Duplicate LAF Number ")) {
//                    WDS.sampleResult.setResponseMessage(msg + "(ID:" + WDS.vars.get("MemberID") + ")");
//                    WDS.sampleResult.setSuccessful(false);
                    logger.error("Error when disburse Member ID: " + memberId);
//                    break;
                } else {
//                    WDS.sampleResult.setResponseMessage(msg + "(ID:" + WDS.vars.get("MemberID") + ")");
//                    WDS.sampleResult.setSuccessful(false);
                    System.out.println("Error when disburse Member ID: " + memberId);
//                    break;
                }
            } catch (Exception e) {
                logger.error("Error when disburse loan!", e);
            }
        }
    }
    private static void loadLoanDisbursement() throws InterruptedException {
        LoanDisbursement ld = new LoanDisbursement();
        ld.start("12090",
                "Test@123",
                "241",
                "29538",
                "10481",
                "2948609",
                "23/08/2023",
                "10000",
                "3231");
    }
    public static void main(String[] args) throws InterruptedException {
        // load test on group transaction
//        loadGroupTransaction();

        // load test on loan disbursement
        loadLoanDisbursement();
    }

}
