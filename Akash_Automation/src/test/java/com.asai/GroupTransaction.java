package com.asai;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GroupTransaction {
    private final WebDriver driver;
    JavascriptExecutor js;
    private final String baseUrl = "http://192.168.100.77/Programs/P_GroupTransactionSheet.aspx";


    public GroupTransaction() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }

    void start(String user, String password, String branchId, String loanOfficerId) {
        driver.get(baseUrl);

        driver.findElement(By.id("login_UserName")).sendKeys(new String[]{user});
        driver.findElement(By.id("login_Password")).sendKeys(new String[]{password});
        driver.findElement(By.id("login_LoginImageButton")).click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        var fluentWait = new FluentWait<>(driver);

        int size = 2;
        for (int i = 1; i < size; i++) {

            // Select Branch
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceHolder1_ddlBranchId")));
            Select ddlBranchId = new Select(driver.findElement(By.id("ctl00_ContentPlaceHolder1_ddlBranchId")));
            wait.until(webDriver -> ddlBranchId.getOptions().size() > 1);
            ddlBranchId.selectByValue(branchId);

            // Select Loan Officer
            By lo = By.id("ctl00_ContentPlaceHolder1_ddlP_LoanOfficerId");
            wait.until(ExpectedConditions.presenceOfElementLocated(lo));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(lo))));
            Select ddlPLoanOfficerId = new Select(driver.findElement(lo));
            wait.until(webDriver -> ddlPLoanOfficerId.getOptions().size() > 1);
            ddlPLoanOfficerId.selectByValue(loanOfficerId);

            // Select Group
            By group = By.id("ctl00_ContentPlaceHolder1_ddlP_GroupId");
            wait.until(ExpectedConditions.presenceOfElementLocated(group));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(group))));
            Select ddlPGroupId = new Select(driver.findElement(group));
            wait.until(webDriver -> ddlPGroupId.getOptions().size() > 1);

            size = ddlPGroupId.getOptions().size();
            System.out.println("Total Options: " + size + " -> Index: " + i);
            ddlPGroupId.selectByIndex(i);

            // Try to load group transaction sheet
            By groupTrnSheet = By.id("ctl00_ContentPlaceHolder1_gvGroupTransactionSheet");
            wait.until(ExpectedConditions.presenceOfElementLocated(groupTrnSheet));
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_mnuPageToolbarn1")));
                var save = driver.findElement(By.id("ctl00_mnuPageToolbarn1"));
                save.click();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();

                if (alertText.equals("Record saved successfully.")) {
                    alert.accept();
                    System.out.println("MSG : " + alertText);
                }
            } catch (Exception ex) {
                ex.printStackTrace();

                try {
                    var msg = driver.findElement(By.id("ctl00_lblMessage")).getText();
                    System.out.println("MSG : " + msg);

                    if (msg.startsWith("Error: Loan collection amount can not be greater than outstanding amount ")) {

                        for (int j = 2; ; j++) {
                            var memberName = driver.findElement(By.id("ctl00_ContentPlaceHolder1_gvGroupTransactionSheet_ctl" + (j < 10 ? "0" : "") + j + "_lbView")).getText();

                            if (msg.contains(memberName)) {
                                var amount = msg.replace("Error: Loan collection amount can not be greater than outstanding amount ", "").split(" ")[0];
                                js.executeScript("ctl00_ContentPlaceHolder1_gvGroupTransactionSheet_ctl" + (j < 10 ? "0" : "") + j + "_txtRealizableAmount.setAttribute('value', '" + amount + "');", new String[]{""});

                                System.out.println(amount);

                                break;
                            }
                        }
                    } else if (msg.startsWith("Error: Minimum LCBU deposit is ")) {
                        for (int j = 2; ; j++) {
                            var memberName = driver.findElement(By.id("ctl00_ContentPlaceHolder1_gvGroupTransactionSheet_ctl" + (j < 10 ? "0" : "") + j + "_lbView")).getText();

                            if (msg.contains(memberName)) {
                                var amount = msg.replace("Error: Minimum LCBU deposit is ", "").split(" ")[0];
                                js.executeScript("ctl00_ContentPlaceHolder1_gvGroupTransactionSheet_ctl" + (j < 10 ? "0" : "") + j + "_txtSavingsMinimumDeposit.setAttribute('value', '" + amount + "');", new String[]{""});

                                System.out.println(amount);

                                break;
                            }
                        }
                    } else if (msg.startsWith("Error: Please enter OR number")) {
                        js.executeScript("ctl00_ContentPlaceHolder1_txtOrNumber.setAttribute('value', '1');", new String[]{""});
                        js.executeScript("ctl00_ContentPlaceHolder1_txtArNumber.setAttribute('value', '1');", new String[]{""});
                    } else {
//                        WDS.sampleResult.setResponseMessage(msg);
//                        WDS.sampleResult.setSuccessful(false);
                        break;
                    }
                } catch (Exception x) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x.printStackTrace();
                }
            }

            break;
        }

        driver.quit();
    }
}
