package ui;

import base.BaseTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Logger;

public class AddWidgetTest {
    Logger logger = Logger.getLogger(BaseTest.class.getName());

    @Test
    public void AddWidget() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.reportportal.io/");
        logger.info("Установлено соединение с " + driver.getTitle());
        System.out.println("Title: " + driver.getTitle());

        WebElement nameField = driver.findElement(By.xpath("//input[@name='login']"));
        nameField.clear();
        nameField.sendKeys("default");

        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.clear();
        passwordField.sendKeys("1q2w3e");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));


        WebElement loginButtonElement = driver.findElement(By.xpath("//button[text()='Login']"));
        loginButtonElement.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.//span[text()='Dashboards']]")));

        WebElement dashboardsButtonElement = driver.findElement(By.xpath("//a[.//span[text()='Dashboards']]"));
        dashboardsButtonElement.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Add New Dashboard']")));

        WebElement addNewDashboardsButton = driver.findElement(By.xpath("//button[normalize-space()='Add New Dashboard']"));
        addNewDashboardsButton.click();


        logger.info("Успешный вход выполнен.");

        driver.quit();
    }
}
