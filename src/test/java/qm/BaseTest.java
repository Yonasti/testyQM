package qm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public abstract class BaseTest {
    private WebDriver driver;

    @BeforeTest
    @Parameters("browserType")
    public void setup(String browserType) {
        System.out.println("Chosen browser: " + browserType);
        if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
            this.driver.manage().window().maximize();


        } else if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
        }
    }

    @AfterTest
    public void teardown() {
        this.driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
