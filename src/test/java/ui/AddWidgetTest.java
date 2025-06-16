package ui;

import base.UIBaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.*;

import static config.TestData.*;
import static io.qameta.allure.SeverityLevel.*;
import static org.testng.Assert.*;

@Epic("UI Тесты")
@Feature("Виджеты")
public class AddWidgetTest extends UIBaseTest {
    private static final String newWidgetName = generateNewName(WIDGET_NAME);

    @Test(description = "Создание нового виджета и проверка его отображения на дашборде")
    @Story("Успешное создание виджета")
    @Severity(BLOCKER)
    public void addWidgetTest() {

        driver.get(BASE_URL);

        DashboardPage dashboard = new LoginPage(driver)
                .loginAs(DEFAULT_LOGIN, DEFAULT_PASSWORD)
                .goToDashboards()
                .openDashboard()
                .addNewWidget()
                .selectWidgetType()
                .next()
                .addFilter()
                .next()
                .enterWidgetName(newWidgetName)
                .add();

        assertTrue(dashboard
                        .isAt(),
                "После добавления виджета должна открыться страница дашборда.");
        assertTrue(dashboard
                        .isWidgetPresent(newWidgetName),
                "Созданный виджет должен присутствовать на дашборде.");
    }
}
