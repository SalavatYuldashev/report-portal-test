package ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddNewDashboardModalPage;
import pages.DashboardsPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.logging.Logger;

public class AddWidgetTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(AddWidgetTest.class.getName());

    HomePage homePage;
    LoginPage loginPage;
    DashboardsPage dashboardsPage;
    AddNewDashboardModalPage addNewDashboardModalPage;


    @Test(description = " Проверка создания виджета")
    public void addWidgetTest() {
        driver.get("https://demo.reportportal.io/");
        loginPage = new LoginPage(driver);
        homePage = loginPage.loginAs("default", "1q2w3e");
        Assert.assertTrue(homePage.isAt(), "Неверная страница.");
        dashboardsPage = homePage.clickDashboardsButton();
        Assert.assertTrue(dashboardsPage.isAt(), "Неверная страница.");
        addNewDashboardModalPage = dashboardsPage.clickOnAddNewDashboardButton();
        Assert.assertTrue(addNewDashboardModalPage.isAt(), "Неверная страница.");

    }
}
