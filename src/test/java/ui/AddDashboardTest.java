package ui;

import config.TestData;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import java.util.logging.Logger;


public class AddDashboardTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(AddDashboardTest.class.getName());

    HomePage homePage;
    LoginPage loginPage;
    DashboardsPage dashboardsPage;
    AddNewDashboardModalPage addNewDashboardModalPage;
    SpecificDashboardPage specificDashboardPage;

    @Test(description = " Проверка работы процесса создания дашборда")
    public void addDashboardTest() {
        String expectedDashboardName = TestData.generateNewName(TestData.DASHBOARD_NAME);
        driver.get(TestData.BASE_URL);
        loginPage = new LoginPage(driver);
        homePage = loginPage.loginAs(TestData.DEFAULT_LOGIN, TestData.DEFAULT_PASSWORD);
        Assert.assertTrue(homePage.isAt(), "Неверная страница.");
        dashboardsPage = homePage.clickDashboardsButton();
        Assert.assertTrue(dashboardsPage.isAt(), "Неверная страница.");
        addNewDashboardModalPage = dashboardsPage.clickOnAddNewDashboardButton();
        Assert.assertTrue(addNewDashboardModalPage.isAt(), "Неверная страница.");
        addNewDashboardModalPage.inputDashboardName(expectedDashboardName);
        addNewDashboardModalPage.inputDescription(TestData.DASHBOARD_DESCRIPTION);
        specificDashboardPage = addNewDashboardModalPage.clickAddNewDashboardButton();
        Assert.assertTrue(specificDashboardPage.isAt(), "Неверная страница");
        Assert.assertEquals(specificDashboardPage.getCurrentDashboardName().toLowerCase(),
                expectedDashboardName.toLowerCase(), "Имя дашборда не соответствует ожидаемому.");
    }
}
