package config;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TestData {
    private TestData() {}

    public static final String DEFAULT_LOGIN = "default";
    public static final String DEFAULT_PASSWORD = "1q2w3e";

    public static final String BASE_URL = "https://demo.reportportal.io/";

    public static final String UI_DASHBOARD_NAME = "UIDashboardTest";
    public static final String API_DASHBOARD_NAME = "APIDashboardTest";
    public static final String DASHBOARD_DESCRIPTION = "Это тестовое описание.";
    public static final String WIDGET_NAME = "WidgetTest";

    public static String generateNewName(String prefix) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);
        return prefix + " " + timestamp;
    }
}