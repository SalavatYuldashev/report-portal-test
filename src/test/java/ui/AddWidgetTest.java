package ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import pages.AddNewDashboardPage;
import pages.DashboardsPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.logging.Logger;

public class AddWidgetTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(AddWidgetTest.class.getName());

    HomePage homePage;
    LoginPage loginPage;
    DashboardsPage dashboardsPage;
    AddNewDashboardPage addNewDashboardPage;


    @Test(description = " Проверка создания виджета")
    public void addWidgetTest() {
        driver.get("https://demo.reportportal.io/");
        loginPage = new LoginPage(driver);
        homePage = loginPage.loginAs("default", "1q2w3e");
        Assert.assertTrue(homePage.isAt(), "Неверная страница.");
        dashboardsPage = homePage.clickDashboardsButton();
        Assert.assertTrue(dashboardsPage.isAt(), "Неверная страница.");
        addNewDashboardPage = dashboardsPage.clickOnAddNewDashboardButton();
        Assert.assertTrue(addNewDashboardPage.isAt(), "Неверная страница.");

    }
}
