package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class ApiBaseTest {

    protected static RequestSpecification spec;

    @BeforeClass
    public static void setUp() {
        // Устанавливаем базовый URI для всех API-запросов
        RestAssured.baseURI = ConfigReader.getProperty("base.api.url");

        // Создаем спецификацию запроса, которая будет использоваться во всех тестах
        spec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + ConfigReader.getProperty("api.key"))
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured()) // Добавляем фильтр для логирования в Allure
                .build();
    }
}