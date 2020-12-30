package tech.danjones.cloudstorage.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    private WebDriverWait wait;
    private JavascriptExecutor js;

    public static WebDriver driver;

    public String baseURL;
    private final SignupPage signupPage = new SignupPage(driver);
    private final LoginPage loginPage = new LoginPage(driver);

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        chrome.setJavascriptEnabled(true);
        options.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(options);
    }

    public void waitAndClickElement(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, 2);
        js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", element);
    }

    public void sendTextTo(String text, WebElement element) {
        js.executeScript("arguments[0].click();", element);
        js.executeScript("arguments[0].value='" + text + "';", element);
    }

    public void waitUntil(ExpectedCondition<?> result) {
        wait.until(result);
    }

    public void click(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public void signUp(String username, String password) {
        driver.get(baseURL + "/signup");
        signupPage.signup("James", "Hill", username, password);
    }

    public void login(String username, String password) {
        driver.get(baseURL + "/login");
        loginPage.login(username, password);
    }
}
