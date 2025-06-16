package base;

import enums.BrowserType;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.WebDriverFactory;

import java.util.logging.Logger;

public class UIBaseTest {
    protected WebDriver driver;
    protected static final Logger logger = Logger.getLogger(UIBaseTest.class.getName());

    @BeforeMethod
    @Parameters("browser")
    @Step("Подготовка окружения: создание драйвера для браузера '{browserName}' и открытие окна")
    public void setUpDriver(@Optional("firefox") String browserName) {
        BrowserType browser = BrowserType.valueOf(browserName.toUpperCase());
        driver = WebDriverFactory.createDriver(browser);
        logger.info("Драйвер для браузера " + browser + " успешно создан.");
        driver.manage().window().maximize();
        logger.info("Окно браузера развернуто на весь экран.");
    }

    @AfterMethod
    @Step("Очистка окружения: завершение работы драйвера и закрытие браузера")
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер успешно завершил работу.");
        }
    }
}
