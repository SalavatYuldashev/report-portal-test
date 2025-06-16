package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddWidgetWizardPage {
    private static final Logger logger = Logger.getLogger(AddWidgetWizardPage.class.getName());
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final By addWidgetCheckerBy = By.xpath("//div[contains(@class, 'widget-type-selector-heading') " +
            "and normalize-space()='Choose widget type from the list below']");

    private final By widgetTypeRadioButton = By.xpath("//div[contains(@class, 'widget-type-item-name') " +
            "and normalize-space()='Launches table']");

    private final By nextStepButtonBy = By.xpath("//button[.//span[normalize-space()='Next step']]");
    private final By widgetNameInputBy = By.xpath("    //input[@placeholder='Enter widget name']");
    private final By addWidgetButtonBy = By.xpath("//button[normalize-space()='Add']");
    private final By selectFilterRadioButton = By.xpath("//label[.//span[contains(@class, 'inputRadio__children-container')]]");

    public AddWidgetWizardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addWidgetCheckerBy));
            logger.info("Мастер 'Add New Widget' успешно загружен и верифицирован.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Мастер 'Add New Widget' не загрузился! Текущий URL: " + driver.getCurrentUrl(), e);
            throw new IllegalStateException("Мастер 'Add New Widget' не загрузился! Текущий URL: " + driver.getCurrentUrl(), e);
        }
    }

    public boolean isAt() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(addWidgetCheckerBy)).isDisplayed();
        } catch (Exception e) {
            logger.warning("Проверка isAt() не удалась: это не мастер создания виджетов.");
            return false;
        }
    }

    @Step("Шаг 1: Выбор типа виджета")
    public AddWidgetWizardPage selectWidgetType() {
        try {
            WebElement widgetTypeElement = wait.until(ExpectedConditions.elementToBeClickable(widgetTypeRadioButton));
            widgetTypeElement.click();
            logger.info("Выбран тип виджета 'Launches table'.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось выбрать тип виджета.", e);
            throw new RuntimeException("Не удалось выбрать тип виджета.", e);
        }
        return this;
    }

    @Step("Переход к следующему шагу")
    public AddWidgetWizardPage next() {
        try {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextStepButtonBy));
            nextButton.click();
            logger.info("Нажата кнопка 'Next step >'.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось нажать кнопку 'Next step >'.", e);
            throw new RuntimeException("Не удалось нажать кнопку 'Next step >'.", e);
        }
        return this;
    }

    @Step("Шаг 2: Выбор фильтра для виджета")
    public AddWidgetWizardPage addFilter() {
        try {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(selectFilterRadioButton));
            nextButton.click();
            logger.info("Выбран первый доступный фильтр для виджета.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось выбрать фильтр.", e);
            throw new RuntimeException("Не удалось выбрать фильтр.", e);
        }
        return this;
    }

    @Step("Шаг 3: Ввод имени виджета: '{widgetName}'")
    public AddWidgetWizardPage enterWidgetName(String widgetName) {
        try {
            WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(widgetNameInputBy));
            nameInput.clear();
            nameInput.sendKeys(widgetName);
            logger.info("Введено имя виджета: " + widgetName);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось ввести имя виджета: " + widgetName, e);
            throw new RuntimeException("Не удалось ввести имя виджета: " + widgetName, e);
        }
        return this;
    }

    @Step("Завершение создания виджета")
    public DashboardPage add() {
        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(addWidgetButtonBy));
            saveButton.click();
            logger.info("Нажата кнопка 'Add' для сохранения виджета.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось нажать кнопку 'Add' для сохранения виджета.", e);
            throw new RuntimeException("Не удалось нажать кнопку 'Add' для сохранения виджета.", e);
        }
        return new DashboardPage(driver);
    }
}