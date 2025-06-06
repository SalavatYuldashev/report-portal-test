package ui;

import base.BaseTest;
import config.TestData;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.logging.Logger;


@Epic("UI Тесты")
@Feature("Виджеты")
public class AddWidgetTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(AddWidgetTest.class.getName());

    HomePage homePage;
    LoginPage loginPage;
    DashboardsPage dashboardsPage;
    SpecificDashboardPage specificDashboardPage;
    AddWidgetWizardPage addWidgetWizardPage;

    @Test(description = "Создание нового виджета и проверка его отображения на дашборде")
    @Story("Успешное создание виджета")
    @Severity(SeverityLevel.BLOCKER)
    public void addWidgetTest() {
        // Arrange: Подготовка тестовых данных и открытие начальной страницы
        String expectedWidgetName = TestData.generateNewName(TestData.WIDGET_NAME);
        driver.get(TestData.BASE_URL);

        // Act: Выполнение последовательности действий пользователя для создания виджета
        loginPage = new LoginPage(driver);
        homePage = loginPage.loginAs(TestData.DEFAULT_LOGIN, TestData.DEFAULT_PASSWORD);
        Assert.assertTrue(homePage.isAt(), "После входа должна открыться домашняя страница.");
        dashboardsPage = homePage.clickDashboardsButton();
        Assert.assertTrue(dashboardsPage.isAt(), "Должна открыться страница со списком дашбордов.");
        specificDashboardPage = dashboardsPage.clickOnSpecificDashboardButton();
        Assert.assertTrue(specificDashboardPage.isAt(), "Должна открыться страница конкретного дашборда.");
        addWidgetWizardPage = specificDashboardPage.clickAddNewWidgetButton();
        Assert.assertTrue(addWidgetWizardPage.isAt(), "Должен открыться мастер создания виджета.");
        addWidgetWizardPage.selectWidgetType();
        addWidgetWizardPage.clickNextStepButton();
        addWidgetWizardPage.clickSelectFilterRadioButton();
        addWidgetWizardPage.clickNextStepButton();
        addWidgetWizardPage.enterWidgetName(expectedWidgetName);
        SpecificDashboardPage specificDashboardPage2 = addWidgetWizardPage.clickAddWidgetButton();

        // Assert: Проверка того, что виджет появился на дашборде
        Assert.assertTrue(specificDashboardPage2.isAt(), "После добавления виджета должна открыться страница дашборда.");
        Assert.assertTrue(specificDashboardPage2.isWidgetPresent(expectedWidgetName), "Созданный виджет должен присутствовать на дашборде.");

    }
}
