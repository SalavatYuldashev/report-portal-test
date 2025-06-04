package pages;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddNewDashboardPage {
    private static final Logger logger = Logger.getLogger(AddNewDashboardPage.class.getName());
    WebDriver driver;
    WebDriverWait wait;

    private final By addNewDashboardCheckerBy = By.xpath("//button[contains(@class, 'dashboard-config-button') " +
            "and text()='Show dashboard configuration']");

    public AddNewDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addNewDashboardCheckerBy));
            logger.log(Level.INFO, "Загружена страница \"AddDashboardPage\".");
        } catch (Exception e) {
            logger.log(Level.INFO, "Открыта неверная страница. Текущая страница " + driver.getCurrentUrl() + e);
            throw new IllegalStateException("Открыта неверная страница. Текущая страница " + driver.getCurrentUrl() + e);
        }
    }


    public boolean isAt() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(addNewDashboardCheckerBy)).isDisplayed();
        } catch (Exception e) {
            logger.warning("Неверная страница! Это страница: " + driver.getCurrentUrl() + e);
            return false;
        }

    }
}