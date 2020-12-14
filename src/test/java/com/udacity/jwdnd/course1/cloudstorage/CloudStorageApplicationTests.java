package com.udacity.jwdnd.course1.cloudstorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    public int port;

    public static WebDriver driver;

    public String baseURL;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = baseURL = "http://localhost:" + port;
    }

    @Test
    public void testUserSignupLogsInAndLogsOut() {
        String username = "cloudstorage";
        String password = "whatabadpassword";

        driver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("James", "Hill", username, password);

        assertTrue(signupPage.isSuccessMessageDisplayed());

        driver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        assertTrue(loginPage.loginBarIsDisplayed());

        driver.get(baseURL + "/home");

        HomePage homePage = new HomePage(driver);
        homePage.logout();

        assertEquals(driver.getCurrentUrl(), baseURL + "/login");

    }

}
