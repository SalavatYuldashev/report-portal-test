package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddNewDashboardModalPage {
    private static final Logger logger = Logger.getLogger(AddNewDashboardModalPage.class.getName());
    WebDriver driver;
    WebDriverWait wait;

    private final By addNewDashboardCheckerBy = By.xpath("//input[@placeholder='Enter dashboard name']");

    private final By nameInput = By.xpath("//input[@placeholder='Enter dashboard name']");
    private final By descriptionInput = By.xpath("//input[@placeholder='Enter dashboard name']");
    private final By addButton = By.xpath("//button[normalize-space()='Add']");


    public AddNewDashboardModalPage(WebDriver driver) {
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