package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class BaseTest {

    // Thread-safe WebDriver for parallel tests
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUp() 
    {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);
        
        driver.set(new ChromeDriver(options));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        if (getDriver() != null) 
        {
            getDriver().quit();
            driver.remove();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
    
    public static void NavigateTo(String url) {
    	getDriver().get(url);
    }
}