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

public class AddNewDashboardModalPage {
    private static final Logger logger = Logger.getLogger(AddNewDashboardModalPage.class.getName());
    WebDriver driver;
    WebDriverWait wait;

    private final By addNewDashboardCheckerBy = By.cssSelector("button.bigButton__big-button--BmG4Q");
    private final By nameInput = By.xpath("//input[@placeholder='Enter dashboard name' and @type='text']");
    private final By descriptionInput = By.xpath("//textarea[@placeholder='Enter dashboard description']");
    private final By addNewDashboardButton = By.xpath("//*[@id='modal-root']//button[normalize-space()='Add']");

    public AddNewDashboardModalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addNewDashboardCheckerBy));
            logger.log(Level.INFO, "Модальное окно 'Add New Dashboard' успешно открыто и верифицировано.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Это не модальное окно 'Add New Dashboard'! Текущая страница: " + driver.getCurrentUrl(), e);
            throw new IllegalStateException("Это не модальное окно 'Add New Dashboard'! Текущая страница: " + driver.getCurrentUrl(), e);
        }
    }

    public boolean isAt() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(addNewDashboardCheckerBy)).isDisplayed();
        } catch (Exception e) {
            logger.warning("Проверка isAt() не удалась: это не модальное окно 'Add New Dashboard'.");
            return false;
        }

    }

    @Step("Ввод имени дашборда: '{name}'")
    public void inputDashboardName(String name) {
        try {
            WebElement dashboardName = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
            dashboardName.sendKeys(name);
            logger.log(Level.INFO, "Введено имя '" + name + "' для нового дашборда.");
        } catch (TimeoutException e) {
            logger.log(Level.SEVERE, "Поле 'Dashboard Name' не найдено.", e);
            throw new RuntimeException("Поле 'Dashboard Name' не найдено.", e);
        }
    }

    @Step("Ввод описания дашборда: '{description}'")
    public void inputDescription(String description) {
        try {
            WebElement dashboardDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionInput));
            dashboardDescription.sendKeys(description);
            logger.log(Level.INFO, "Введено описание '" + description + "' для нового дашборда.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Поле 'Dashboard description' не найдено.", e);
            throw new RuntimeException("Поле 'Dashboard description' не найдено.", e);
        }
    }

    @Step("Нажатие на кнопку 'Add' для сохранения дашборда")
    public SpecificDashboardPage clickAddNewDashboardButton() {
        try {
            WebElement addNewDashboard = wait.until(ExpectedConditions.elementToBeClickable(addNewDashboardButton));
            addNewDashboard.click();
            logger.log(Level.INFO, "Нажата кнопка 'Add' для создания нового дашборда.");
            return new SpecificDashboardPage(driver);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Кнопка 'Add' для добавления нового дашборда не найдена.", e);
            throw new RuntimeException("Кнопка 'Add' для добавления нового дашборда не найдена.", e);
        }
    }
}