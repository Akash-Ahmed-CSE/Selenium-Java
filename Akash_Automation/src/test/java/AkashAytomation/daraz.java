package AkashAytomation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;



public class daraz {
    public static String browser = "chrome";
    public static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
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
        //Navigate to the website
        driver.get("https://www.daraz.com.bd/");
        System.out.println("Navigate to the website");
        //Maximizing
        driver.manage().window().maximize();
        System.out.println("Window Maximizing");

        //Login
        WebElement Login = driver.findElement(By.xpath("//*[@id=\"anonLogin\"]/a"));
        Login.click();
        System.out.println("Clicked on Login Button");
        WebElement PhoneNumber = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/form/div/div[1]/div[1]/input"));
        PhoneNumber.sendKeys("01303647718");
        WebElement Password = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/form/div/div[1]/div[2]/input"));
        Password.sendKeys("Test123#");
        Thread.sleep(1500);

        //Clicked on Login Button
        WebElement Loginbtn = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/form/div/div[2]/div[1]/button"));
        Loginbtn.click();
        System.out.println("Login button Clocked");
        Thread.sleep(1500);

        //Serching for a Laptop
        WebElement Search1 = driver.findElement(By.xpath("//input[@id='q']"));
        Search1.click();
        Thread.sleep(1500);
        Search1.sendKeys("Laptop");
        System.out.println("Search for a Laptop");
        Thread.sleep(1500);
        Search1.submit();

        //Click on an item
        WebElement CheckBoxASUS = driver.findElement(By.linkText("HP 250 G9 12th gen i5 (1235U) 8GB Ram 256GB NVMe PCIe SSD 15.6 Full HD Display Black Color laptop Free DOS 02 Year Warranty Keyboard Layout Italian #6F1Z9EA"));
        CheckBoxASUS.click();
        System.out.println("Click on an item");
        Thread.sleep(1500);

        //Select Quantity
        WebElement qty = driver.findElement(By.xpath("//*[@id=\"module_quantity-input\"]/div/div/div/div/div[1]/a[1]/span"));
        qty.click();
        System.out.println("Select Quantity");
        Thread.sleep(1500);

        //Add to Cart
        WebElement AddToCart = driver.findElement(By.xpath("(//button[contains(@class,'pdp-button pdp-button_type_text pdp-button_theme_orange pdp-button_size_xl')])[1]"));
        AddToCart.click();
        System.out.println("Add to Cart");
        driver.navigate().refresh();

        //View Cart
        WebElement Cart = driver.findElement(By.xpath("//*[@id=\"topActionHeader\"]/div[1]/div[2]/div/div[3]"));
        Cart.click();
        System.out.println("View Cart");
        Thread.sleep(1500);

        //Delete Cart
        WebElement Delete = driver.findElement(By.xpath("(//span[@class='automation-btn-delete'])[1]"));
        Delete.click();
        System.out.println("Delete Cart");
        Thread.sleep(1500);

        //Confirm to Remove
        WebElement Remove = driver.findElement(By.xpath("//button[normalize-space()='REMOVE']"));
        Remove.click();
        System.out.println("Confirm to Remove");
        Thread.sleep(1500);

        //Click on Name
        WebElement name = driver.findElement(By.xpath("//*[@id=\"myAccountTrigger\"]"));
        name.click();
        System.out.println("Click on Name");
        Thread.sleep(1500);

        //Click on Logout Button
        WebElement Logout = driver.findElement(By.xpath("//a[normalize-space()='Logout']"));
        System.out.println("Click on Logout Button");
        Logout.click();

        //Project Closed
        Thread.sleep(1500);
        driver.close();
        System.out.println("Project Closed Successfully!");
    }
}