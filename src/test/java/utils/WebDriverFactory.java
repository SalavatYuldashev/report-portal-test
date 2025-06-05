package utils;

import base.BaseTest;
import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;
import java.util.logging.Logger;


public class WebDriverFactory {

    private static final Logger logger = Logger.getLogger(WebDriverFactory.class.getName());


    public static WebDriver createDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                logger.info("Драйвер для браузера Chrome установлен");
                ChromeOptions options = getChromeOptions();
                logger.info("Применены опции для браузера Chrome");
                return new ChromeDriver(options);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                logger.info("Драйвер для браузера Firefox установлен");
                return new FirefoxDriver();
            case EDGE:
                WebDriverManager.edgedriver().setup();
                logger.info("Драйвер для браузера Edge установлен");
                return new EdgeDriver();
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browserType);
        }
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--disable-credentials-enable-service");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-features=AutofillServerCommunication,PasswordImport");
        options.addArguments("--password-store=basic"); // ←
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("prefs", Map.of(
                "profile.password_manager_enabled", false, // Отключает менеджер паролей Chrome
                "credentials_enable_service", false, // Отключает службу учетных данных
                "profile.password_manager_leak_detection_enabled", false // Отключает обнаружение утечек паролей
        ));
        return options;
    }
}

