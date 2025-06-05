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

public class DashboardsPage {
    private static final Logger logger = Logger.getLogger(DashboardsPage.class.getName());
    WebDriver driver;
    WebDriverWait wait;

    private final By dashboardsCheckerBy = By.xpath("//button[.//span[normalize-space()='Add New Dashboard']]");

    public DashboardsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardsCheckerBy));
            logger.log(Level.INFO, "Загружена страница \"Dashboards\".");
        } catch (Exception e) {
            logger.log(Level.INFO, "Открыта неверная страница. Текущая страница " + driver.getCurrentUrl() + e);
            throw new IllegalStateException("Открыта неверная страница. Текущая страница " + driver.getCurrentUrl() + e);
        }
    }

    public boolean isAt() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardsCheckerBy)).isDisplayed();
        } catch (Exception e) {
            logger.warning("Неверная страница! Это страница: " + driver.getCurrentUrl() + e);
            return false;
        }

    }

    public AddNewDashboardModalPage clickOnAddNewDashboardButton() {
        WebElement addNewDashboardButton;
        try {
            addNewDashboardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardsCheckerBy));
            addNewDashboardButton.click();
            logger.log(Level.INFO, "Была нажата кнопка \"Add New Dashboard\".");
        } catch (TimeoutException e) {
            logger.info("Кнопка \"Add New Dashboard\" не найдена на странпице.");
            throw new RuntimeException(e);
        }
        return new AddNewDashboardModalPage(driver);
    }
}
