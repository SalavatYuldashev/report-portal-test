package ui;

import base.UIBaseTest;
import org.testng.annotations.*;
import pages.*;

import io.qameta.allure.*;

import static config.TestData.*;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.testng.Assert.assertEquals;

@Epic("UI Тесты")
@Feature("Dashboards")
public class AddDashboardTest extends UIBaseTest {
    private final String newDashboardName = generateNewName(UI_DASHBOARD_NAME);

    @Test(description = "Создание нового дашборда и проверка его отображения")
    @Story("Успешное создание дашборда")
    @Severity(BLOCKER)
    public void addDashboardTest() {

        driver.get(BASE_URL);

        DashboardPage dashboard = new LoginPage(driver)
                .loginAs(DEFAULT_LOGIN, DEFAULT_PASSWORD)
                .goToDashboards()
                .addNewDashboard()
                .inputDashboardName(newDashboardName)
                .inputDashboardDescription(DASHBOARD_DESCRIPTION)
                .add();

        assertEquals(
                dashboard
                        .getCurrentDashboardName()
                        .toLowerCase(),
                newDashboardName
                        .toLowerCase(),
                "Имя на странице дашборда должно соответствовать заданному.");
    }
}
