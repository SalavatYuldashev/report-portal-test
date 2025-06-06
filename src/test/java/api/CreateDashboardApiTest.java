package api;

import base.ApiBaseTest;
import config.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("API Тесты")
@Feature("Dashboard")
public class CreateDashboardApiTest extends ApiBaseTest {

    @Test(description = "API: Успешное создание нового дашборда")
    @Story("Создание дашборда (Позитивный сценарий)")
    public void successfulDashboardCreationTest() {

        final String dashboardName = TestData.generateNewName(TestData.API_DASHBOARD_NAME);
        final String requestBody = String.format(
                "{\"name\": \"%s\", \"description\": \"Dashboard created via API\"}",
                dashboardName);
        final String projectName = ConfigReader.getProperty("project.name");
        int dashboardId;

        dashboardId = given()
                .spec(spec)
                .body(requestBody)
                .when()
                .post("/{projectName}/dashboard", projectName)
                .then()
                .statusCode(201)
                .extract().path("id");
        given()
                .spec(spec)
                .when()
                .get("/{projectName}/dashboard/{dashboardId}", projectName, dashboardId)
                .then()
                .statusCode(200)
                .body("name", equalTo(dashboardName));

        given()
                .spec(spec)
                .when()
                .delete("/{projectName}/dashboard/{dashboardId}", projectName, dashboardId)
                .then()
                .statusCode(200);
    }
}