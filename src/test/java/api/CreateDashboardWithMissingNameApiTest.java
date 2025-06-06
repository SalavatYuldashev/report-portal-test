package api;

import base.ApiBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;

@Epic("API Тесты")
@Feature("Dashboard")
public class CreateDashboardWithMissingNameApiTest extends ApiBaseTest {
    final String requestBody = "{\"description\": \"Dashboard without a name\"}";
    final String projectName = ConfigReader.getProperty("project.name");

    @Test(description = "API: Неуспешное создание дашборда без обязательного поля 'name'")
    @Story("Создание дашборда (Негативный сценарий)")
    public void createDashboardWithMissingName() {

        given()
                .spec(spec)
                .body(requestBody)
                .when()
                .post("/{projectName}/dashboard", projectName)
                .then()
                .statusCode(400);
    }
}
