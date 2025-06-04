package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.logging.Logger;


public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());


    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("Chrome driver установлен");
        this.driver = new ChromeDriver();
        logger.info("Браузер Chrome открыт");
        driver.manage().window().maximize();
        logger.info("Размер окна браузера изменен на максимальный");
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
        logger.info("Браузер закрыт");
    }


}
