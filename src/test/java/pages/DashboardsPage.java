package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardsPage {
    private static final Logger logger = Logger.getLogger(DashboardsPage.class.getName());
    WebDriver driver;
    WebDriverWait wait;

    private final By dashboardsCheckerBy = By.xpath("//button[.//span[normalize-space()='Add New Dashboard']]");
    private final By specificDashboardButton = By.xpath("  //a[contains(@class, 'dashboardTable__name')]");

    public DashboardsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardsCheckerBy));
            logger.log(Level.INFO, "Страница 'Dashboards' успешно открыта и верифицирована.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Это не страница 'Dashboards'! Текущая страница: " + driver.getCurrentUrl(), e);
            throw new IllegalStateException("Это не страница 'Dashboards'! Текущая страница: " + driver.getCurrentUrl(), e);
        }
    }

    public boolean isAt() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardsCheckerBy)).isDisplayed();
        } catch (Exception e) {
            logger.warning("Проверка isAt() не удалась: это не страница 'Dashboards'.");
            return false;
        }
    }

    @Step("Нажатие на кнопку 'Add New Dashboard'")
    public AddNewDashboardModalPage addNewDashboard() {
        WebElement addNewDashboardButton;
        try {
            addNewDashboardButton = wait.until(ExpectedConditions.elementToBeClickable(dashboardsCheckerBy));
            addNewDashboardButton.click();
            logger.log(Level.INFO, "Нажата кнопка 'Add New Dashboard'.");
        } catch (TimeoutException e) {
            logger.log(Level.SEVERE, "Кнопка 'Add New Dashboard' не найдена на странице.", e);
            throw new RuntimeException("Кнопка 'Add New Dashboard' не найдена на странице.", e);
        }
        return new AddNewDashboardModalPage(driver);
    }

    @Step("Открытие первого дашборда в списке")
    public DashboardPage openDashboard() {
        WebElement selectedDashboardButton;
        try {
            selectedDashboardButton = wait.until(ExpectedConditions.elementToBeClickable(specificDashboardButton));
            selectedDashboardButton.click();
            logger.log(Level.INFO, "Выполнен переход на страницу первого дашборда в списке.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось найти или нажать на дашборд в списке.", e);
            throw new RuntimeException("Не удалось найти или нажать на дашборд в списке.", e);
        }
        return new DashboardPage(driver);
    }
}
