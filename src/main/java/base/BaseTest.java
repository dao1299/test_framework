package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public static final int TIME_OUT = 30;

	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return driverThreadLocal.get();
	}
	
    @BeforeMethod
    public void setUp() {
//    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Downloads\\chromedriver-win64 (2)\\chromedriver-win64\\chromedriver.exe");
//  	  	ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//        ChromeDriverService service = new ChromeDriverService.Builder()
//                .usingAnyFreePort()
//                .build();
//  	  	driver = new ChromeDriver(service, options);
    	WebDriverManager.chromedriver().setup();
    	WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
        getDriver().get("https://automationexercise.com/");
//        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
    	if (getDriver() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
        }
    }
}
