package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigReader {

    private static final Logger logger = Logger.getLogger(ConfigReader.class.getName());
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка: Не удалось загрузить файл config.properties. " +
                    "Убедитесь, что он существует по пути src/test/resources/config.properties", e);
            throw new RuntimeException("Не удалось загрузить конфигурационный файл ", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}