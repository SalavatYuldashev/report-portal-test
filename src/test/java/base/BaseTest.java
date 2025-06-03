package base;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {


    public void initialize() {
        WebDriverManager.chromedriver().setup();

    }

}
