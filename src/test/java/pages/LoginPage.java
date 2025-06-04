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

public class LoginPage {
    private static final Logger logger = Logger.getLogger(LoginPage.class.getName());
    protected WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameInputBy = By.name("login");
    private final By passwordInputBy = By.name("password");
    private final By loginButtonBy = By.xpath("//button[@type='submit']");
    private final By loginPageCheckerBy = By.xpath("//span[normalize-space()='Welcome, login to your account']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageCheckerBy));
            logger.log(Level.INFO, "Страница логирования открыта.");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Открыта неверная страница! Текущая страница " + driver.getCurrentUrl(), e);
            throw new IllegalStateException("Открыта неверная страница! Текущая страница " + driver.getCurrentUrl());
        }
    }

    public void enterUsername(String username) {
        WebElement usernameInput;
        try {
            usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInputBy));
            usernameInput.clear();
            usernameInput.sendKeys(username);
            logger.log(Level.INFO, "Введен логин: " + username);
        } catch (TimeoutException e) {
            throw new RuntimeException("Поле \"Login\" не найдено на странице!" + e);
        }
    }

    public void enterPassword(String password) {
        try {
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputBy));
            passwordInput.clear();
            passwordInput.sendKeys(password);
            logger.log(Level.INFO, "Введен пароль.");
        } catch (TimeoutException e) {
            throw new RuntimeException("Поле \"Password\" не найдено на странице!" + e);
        }
    }

    public void clickLoginButton() {
        try {
            WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButtonBy));
            loginButton.click();
            logger.log(Level.INFO, "Была нажата кнопка \"Login\".");
        } catch (TimeoutException e) {
            throw new RuntimeException("Кнопка \"Login\" не найдеа на странице!" + e);
        }
    }

    public HomePage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        logger.log(Level.INFO, "Выполнен вход в систему. Под именем " + username );
        return new HomePage(driver);
    }
}
