package ui;

import config.TestData;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.util.logging.Logger;


public class AddWidgetTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(AddWidgetTest.class.getName());

    HomePage homePage;
    LoginPage loginPage;
    DashboardsPage dashboardsPage;
    AddNewDashboardModalPage addNewDashboardModalPage;
    SpecificDashboardPage specificDashboardPage;
    AddWidgetWizardPage addWidgetWizardPage;

    @Test(description = "Проверка работы процесса создания виджета")
    public void addWidgetTest() {
        String expectedWidgetName = TestData.generateNewName(TestData.WIDGET_NAME);
        driver.get(TestData.BASE_URL);
        loginPage = new LoginPage(driver);
        homePage = loginPage.loginAs(TestData.DEFAULT_LOGIN, TestData.DEFAULT_PASSWORD);
        Assert.assertTrue(homePage.isAt(), "Неверная страница.");
        dashboardsPage = homePage.clickDashboardsButton();
        Assert.assertTrue(dashboardsPage.isAt(), "Неверная страница.");
        specificDashboardPage = dashboardsPage.clickOnSpecificDashboardButton();
        Assert.assertTrue(specificDashboardPage.isAt(), "Неверная страница.");
        addWidgetWizardPage = specificDashboardPage.clickAddNewWidgetButton();
        Assert.assertTrue(addWidgetWizardPage.isAt(), "Неверная страница");
        addWidgetWizardPage.selectWidgetType();
        addWidgetWizardPage.clickNextStepButton();
        addWidgetWizardPage.clickSelectFilterRadioButton();
        addWidgetWizardPage.clickNextStepButton();
        addWidgetWizardPage.enterWidgetName(expectedWidgetName);
        SpecificDashboardPage specificDashboardPage2 = addWidgetWizardPage.clickAddWidgetButton();
        Assert.assertTrue(specificDashboardPage2.isAt(), "Неверная страница");
        Assert.assertTrue(specificDashboardPage2.isWidgetPresent(expectedWidgetName),"Виджет не найден");

    }
}
