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

public class HomePage {
    private static final Logger logger = Logger.getLogger(HomePage.class.getName());
    protected WebDriver driver;
    private final WebDriverWait wait;
    private final By homePageCheckerBy = By.xpath("//a[.//span[text()='Dashboards']]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(homePageCheckerBy));
            logger.log(Level.INFO, "Домашняя страница успешно открыта и верифицирована.");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Это не домашняя страница! Текущая страница: " + driver.getCurrentUrl(), e);
            throw new IllegalStateException("Это не домашняя страница! Текущая страница: " + driver.getCurrentUrl(), e);
        }
    }

    public boolean isAt() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(homePageCheckerBy)).isDisplayed();
        } catch (Exception e) {
            logger.warning("Проверка isAt() не удалась: это не домашняя страница. Отсутствует элемент 'Dashboards'.");
            return false;
        }
    }

    @Step("Нажатие на кнопку 'Dashboards' в главном меню")
    public DashboardsPage goToDashboards() {
        WebElement dashboardsButton;
        try {
            dashboardsButton = wait.until(ExpectedConditions.elementToBeClickable(homePageCheckerBy));
            dashboardsButton.click();
            logger.log(Level.INFO, "Нажата кнопка 'Dashboards'.");
        } catch (TimeoutException e) {
            logger.log(Level.SEVERE, "Кнопка 'Dashboards' не найдена на странице!", e);
            throw new RuntimeException("Кнопка 'Dashboards' не найдена на странице!", e);
        }
        return new DashboardsPage(driver);
    }
}
