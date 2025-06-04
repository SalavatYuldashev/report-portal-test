package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddNewDashboardPage {
    WebDriver driver;
    private final By addNewDashboardCheckElement = By.xpath("//button[contains(@class, 'dashboard-config-button') and text()='Show dashboard configuration']");

    public AddNewDashboardPage(WebDriver driver) {
        this.driver = driver;
    }
    public By getCheckElement(){
        return addNewDashboardCheckElement;
    }
    public boolean isAt(){
        return driver.findElement(getCheckElement()).isDisplayed();
    }
}
