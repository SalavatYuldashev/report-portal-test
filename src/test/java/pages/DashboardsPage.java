package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardsPage {
    WebDriver driver;
    private final String DASHBOARDS_PAGE_URL = "https://www.dashboards.com/";
    private final By dashboardsCheckElement = By.xpath("//button[.//span[normalize-space()='Add New Dashboard']]");

    public DashboardsPage(WebDriver driver) {
        this.driver = driver;

    }
    public By getDashboardsCheckElement(){
        return dashboardsCheckElement;
    }

    public boolean isAt() {
        return driver.findElement(getDashboardsCheckElement()).isDisplayed();
    }

    public AddNewDashboardPage clickOnAddNewDashboardButton() {
        driver.findElement(getDashboardsCheckElement()).click();
        return new AddNewDashboardPage(driver);
    }
}
