package SetUp;

import PageObjects.PO1_LOGIN;
import PageObjects.PO2_ADD_TO_CARD;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class SuperClass
{
    // Step 1: create a public global variable for web driver
    public WebDriver driver;

    // Step 2: create a global variable for web driver wait
    public WebDriverWait wait;

    // Step 3: create a global variable for page objects
    public PO1_LOGIN authPage;
    public PO2_ADD_TO_CARD productPage;



    @BeforeMethod
    public void opening_method()
    {

        // Step 1: Create an Object from the web driver interface
        driver = new ChromeDriver();

        // Step 2: Manage conditional synchronisation "implicit wait"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Step 3: Manage conditional synchronisation "explicit wait"
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 4: Manage window Maximize
        driver.manage().window().maximize();

        // Step 5: Launch the EUNX website
        driver.get("https://www.demoblaze.com/");

        // Step 6: Create page objects
        authPage = new PO1_LOGIN(driver);
        productPage = new PO2_ADD_TO_CARD(driver);

    }

    @AfterMethod
    public void quit_driver() throws InterruptedException {

        // Step 1: Manage unconditional synchronization
        Thread.sleep(2000);

        //Step 2: Manage
        if (driver != null) {
            driver.quit();
        }
    }
}
