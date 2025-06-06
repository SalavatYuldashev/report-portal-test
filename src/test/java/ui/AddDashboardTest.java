package ui;

import config.TestData;
import base.UIBaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import java.util.logging.Logger;
import io.qameta.allure.*;

@Epic("UI Тесты")
@Feature("Дашборды")
public class AddDashboardTest extends UIBaseTest {
    private static final Logger logger = Logger.getLogger(AddDashboardTest.class.getName());

    HomePage homePage;
    LoginPage loginPage;
    DashboardsPage dashboardsPage;
    AddNewDashboardModalPage addNewDashboardModalPage;
    SpecificDashboardPage specificDashboardPage;


    @Test(description = "Создание нового дашборда и проверка его отображения")
    @Story("Успешное создание дашборда")
    @Severity(SeverityLevel.BLOCKER)
    public void addDashboardTest() {
        // Arrange: Готовим тестовые данные (имя дашборда) и открываем базовый URL.
        String expectedDashboardName = TestData.generateNewName(TestData.UI_DASHBOARD_NAME);
        driver.get(TestData.BASE_URL);

        // Act: Проходим полный путь пользователя: логин, переход на страницу дашбордов, открытие модального окна и создание нового дашборда.
        loginPage = new LoginPage(driver);
        homePage = loginPage.loginAs(TestData.DEFAULT_LOGIN, TestData.DEFAULT_PASSWORD);
        Assert.assertTrue(homePage.isAt(), "После входа должна открыться домашняя страница.");
        dashboardsPage = homePage.clickDashboardsButton();
        Assert.assertTrue(dashboardsPage.isAt(), "Должна открыться страница со списком дашбордов.");
        addNewDashboardModalPage = dashboardsPage.clickOnAddNewDashboardButton();
        Assert.assertTrue(addNewDashboardModalPage.isAt(), "Должно открыться модальное окно для добавления дашборда.");
        addNewDashboardModalPage.inputDashboardName(expectedDashboardName);
        addNewDashboardModalPage.inputDescription(TestData.DASHBOARD_DESCRIPTION);
        specificDashboardPage = addNewDashboardModalPage.clickAddNewDashboardButton();

        // Assert: Проверяем, что после создания мы находимся на странице нового дашборда и его имя соответствует ожидаемому.
        Assert.assertTrue(specificDashboardPage.isAt(), "После сохранения должна открыться страница созданного дашборда.");
        Assert.assertEquals(specificDashboardPage.getCurrentDashboardName().toLowerCase(),
                expectedDashboardName.toLowerCase(), "Имя на странице дашборда должно соответствовать заданному.");
    }
}
