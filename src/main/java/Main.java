import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Logger logger = Logger.getLogger(Main.class.getName());

        driver.get("https://demo.reportportal.io/");
        logger.info("Установлено соединение с " + driver.getTitle());
        System.out.println("Title: " + driver.getTitle());

        //WebElement loginButtonElement = driver.findElement(By.xpath("//button[@title='Login']"));
        WebElement loginButtonElement = driver.findElement(By.xpath("//button[text()='Login']\n"));
                loginButtonElement.click();



        driver.quit();

    }

}
