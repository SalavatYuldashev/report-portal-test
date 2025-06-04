package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.logging.Logger;


public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());


    @BeforeMethod
    public void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--disable-credentials-enable-service");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-features=AutofillServerCommunication,PasswordImport");
        options.addArguments("--password-store=basic"); // ←
        options.addArguments("disable-infobars");

        WebDriverManager.chromedriver().setup();
        logger.info("Chrome driver установлен");
        this.driver = new ChromeDriver(options);
        logger.info("Браузер Chrome открыт");
        driver.manage().window().maximize();
        logger.info("Размер окна браузера изменен на максимальный");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
        logger.info("Браузер закрыт");
    }


}
