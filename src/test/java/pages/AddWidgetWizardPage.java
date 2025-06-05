package pages;

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
            logger.info("Мастер 'ADD NEW WIDGET' загружен и верифицирован.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Мастер 'ADD NEW WIDGET' не загрузился. Current URL: " + driver.getCurrentUrl(), e);
            throw new IllegalStateException("Мастер 'ADD NEW WIDGET' не загрузился. Current URL: " + driver.getCurrentUrl(), e);
        }
    }

    public boolean isAt() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(addWidgetCheckerBy)).isDisplayed();
        } catch (Exception e) {
            logger.warning("Ключевой элемент для мастера 'ADD NEW WIDGET' не найден.");
            return false;
        }
    }

    public void selectWidgetType() {
        try {
            WebElement widgetTypeElement = wait.until(ExpectedConditions.elementToBeClickable(widgetTypeRadioButton));
            widgetTypeElement.click();
            logger.info("Выбран тип виджета: " + widgetTypeRadioButton);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось выбрать тип виджета: " + widgetTypeRadioButton, e);
            throw new RuntimeException("Не удалось выбрать тип виджета: " + widgetTypeRadioButton, e);
        }
    }


    public void clickNextStepButton() {
        try {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextStepButtonBy));
            nextButton.click();
            logger.info("Нажата кнопка 'Next step >'.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось нажать кнопку 'Next step >'.", e);
            throw new RuntimeException("Не удалось нажать кнопку 'Next step >'.", e);
        }
    }

    public void clickSelectFilterRadioButton() {
        try {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(selectFilterRadioButton));
            nextButton.click();
            logger.info("Выбран фильтр для создания виджета.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось выбрать фильтр.", e);
            throw new RuntimeException("Не удалось выбрать фильтр.", e);
        }
    }

    public void enterWidgetName(String widgetName) {
        try {
            WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(widgetNameInputBy));
            nameInput.clear();
            nameInput.sendKeys(widgetName);
            logger.info("Введено имя виджета: " + widgetName);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось ввести имя виджета: " + widgetName, e);
            throw new RuntimeException("Не удалось ввести имя виджета: " + widgetName, e);
        }
    }


    public SpecificDashboardPage clickAddWidgetButton() {
        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(addWidgetButtonBy));
            saveButton.click();
            logger.info("Нажата кнопка сохранения виджета.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Не удалось нажать кнопку сохранения виджета.", e);
            throw new RuntimeException("Не удалось нажать кнопку сохранения виджета.", e);
        }
        return new SpecificDashboardPage(driver);
    }
}