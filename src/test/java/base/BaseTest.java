package base;

import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.WebDriverFactory;

import java.util.logging.Logger;


public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());


    @BeforeMethod
    @Parameters("browser")
    public void setUpDriver(@Optional("firefox") String browserName) {
        BrowserType browser = BrowserType.valueOf(browserName.toUpperCase());
        driver = WebDriverFactory.createDriver(browser);
        logger.info("Открыт браузер: " + browser);
        driver.manage().window().maximize();
        logger.info("Размер окна браузера изменен на максимальный");
    }

    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер завершил работу.");
        }

    }
}
