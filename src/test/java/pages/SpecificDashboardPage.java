package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpecificDashboardPage {
    private static final Logger logger = Logger.getLogger(SpecificDashboardPage.class.getName());
    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By pageCheckerElementBy =
            By.xpath("//button[.//span[normalize-space()='Add new widget']]\n");

    // Локатор для имени дашборда в "хлебных крошках"
    private final By dashboardNameInBreadcrumbsBy =
            By.xpath("//ul[contains(@class, 'page-breadcrumbs')]//li[last()]/span");

    public SpecificDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageCheckerElementBy));
            logger.info("SpecificDashboardPage загружена.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Это не страница SpecificDashboardPage. Текущий URL: " + driver.getCurrentUrl(), e);
            throw new IllegalStateException("Это не страница SpecificDashboardPage. Текущий URL: " + driver.getCurrentUrl(), e);
        }
    }

    public boolean isAt() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageCheckerElementBy)).isDisplayed();
        } catch (Exception e) {
            logger.warning("Ключевой элемент для SpecificDashboardPage не найден.");
            return false;
        }
    }

    public String getCurrentDashboardName() {
        try {
            WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardNameInBreadcrumbsBy));
            String name = nameElement.getText();
            logger.info("Получено имя открытого дашборда: " + name);
            return name.trim();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось получить имя дашборда.", e);
            throw new IllegalStateException("Не удалось получить имя дашборда.", e);
        }
    }

    public AddWidgetWizardPage clickAddNewWidgetButton() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(pageCheckerElementBy));
            button.click();
            logger.info("Нажата кнопка '+ Add new widget'.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось нажать кнопку '+ Add new widget'.", e);
            throw new RuntimeException("Не удалось нажать кнопку '+ Add new widget'.", e);
        }
        return new AddWidgetWizardPage(driver);
    }

    public boolean isWidgetPresent(String widgetNamePrefix) {

        By specificWidgetByName = By.xpath(
                String.format("//div[contains(@class, 'widgetHeader__widget-name-block') " +
                        "and starts-with(normalize-space(), '%s')]", widgetNamePrefix));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(specificWidgetByName));
            logger.info("Виджет с префиксом '" + widgetNamePrefix + "' найден на странице.");
            return true;
        } catch (TimeoutException e) {
            logger.warning("Виджет с префиксом '" + widgetNamePrefix + "' НЕ найден на странице.");
            return false;

        }
    }
}