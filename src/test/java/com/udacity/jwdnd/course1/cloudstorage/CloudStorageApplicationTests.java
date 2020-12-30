package com.udacity.jwdnd.course1.cloudstorage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.ResultsPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests extends Utils {

    @LocalServerPort
    public int port;

    private final SignupPage signupPage = new SignupPage(driver);
    private final LoginPage loginPage = new LoginPage(driver);
    private final HomePage homePage = new HomePage(driver);
    private final ResultsPage resultsPage = new ResultsPage(driver);

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @Test
    public void testUserSignupLogsInAndLogsOut() {
        signUp("user1", "password");
        assertTrue(signupPage.isSuccessMessageDisplayed());

        login("user1", "password");
        assertTrue(loginPage.loginBarIsDisplayed());

        driver.get(baseURL + "/home");
        homePage.logout();
        assertEquals(driver.getCurrentUrl(), baseURL + "/login");
    }

    @Test
    public void testErrorMessageIfDuplicateUserSignsUp() {
        signUp("user2", "password");
        signUp("user2", "password");

        assertEquals(signupPage.getErrorMessage(), "The username already exists.");
    }

    @Test
    public void testAttemptLoginWithInvalidUser() {
        String username = "doesnt";
        String password = "exist";

        login(username, password);
        assertEquals(loginPage.getErrorMessage(), "Invalid username or password");
    }

    @Test
    public void createEditDeleteNote() {
        signUp("user", "password");
        login("user", "password");

        waitAndClickElement(driver, homePage.noteTab);
        waitAndClickElement(driver, homePage.addNoteButton);
        waitAndClickElement(driver, homePage.modalNoteTitle);

        sendTextTo("My Note Title", homePage.modalNoteTitle);
        waitAndClickElement(driver, homePage.modalNoteDescription);

        sendTextTo("My Note Description", homePage.modalNoteDescription);
        click(homePage.modalNoteSubmit);

        waitUntil(ExpectedConditions.titleContains("Result"));
        assertThat(driver.getTitle(), is("Result"));

        driver.get(baseURL + "/home");

        waitAndClickElement(driver, homePage.noteTab);
        waitUntil(ExpectedConditions.elementToBeClickable(homePage.addNoteButton));

        assertThat(homePage.noteTitleField.getText(), is("My Note Title"));
        assertThat(homePage.noteDescriptionField.getText(), is("My Note Description"));

        waitAndClickElement(driver, homePage.editNoteButton);
        sendTextTo("Edited Note Title", homePage.modalNoteTitle);
        waitAndClickElement(driver, homePage.modalNoteDescription);

        sendTextTo("Edited Note Description", homePage.modalNoteDescription);
        click(homePage.modalNoteSubmit);
        waitUntil(ExpectedConditions.titleContains("Result"));
        assertThat(driver.getTitle(), is("Result"));

        driver.get(baseURL + "/home");

        waitAndClickElement(driver, homePage.noteTab);
        waitUntil(ExpectedConditions.elementToBeClickable(homePage.addNoteButton));

        assertThat(homePage.noteTitleField.getText(), is("Edited Note Title"));
        assertThat(homePage.noteDescriptionField.getText(), is("Edited Note Description"));

        waitAndClickElement(driver, homePage.deleteNoteButton);

        waitUntil(ExpectedConditions.titleContains("Result"));
        assertThat(driver.getTitle(), is("Result"));

    }

    @Test
    public void createEditDeleteACredential() {
        signUp("user", "password");
        login("user", "password");

        waitAndClickElement(driver, homePage.credentialTab);
        waitAndClickElement(driver, homePage.addCredentialButton);

        waitAndClickElement(driver, homePage.modalCredentialUrl);
        sendTextTo("www.udacity.com", homePage.modalCredentialUrl);

        waitAndClickElement(driver, homePage.modalCredentialUsername);
        sendTextTo("jonnyives", homePage.modalCredentialUsername);

        waitAndClickElement(driver, homePage.modalCredentialPassword);
        sendTextTo("apple123", homePage.modalCredentialPassword);

        click(homePage.modalCredentialSubmit);

        waitUntil(ExpectedConditions.titleContains("Result"));
        assertThat(driver.getTitle(), is("Result"));

        driver.get(baseURL + "/home");

        waitAndClickElement(driver, homePage.credentialTab);
        waitUntil(ExpectedConditions.elementToBeClickable(homePage.addCredentialButton));

        assertThat(homePage.credentialUrlField.getText(), is("www.udacity.com"));
        assertThat(homePage.credentialUsernameField.getText(), is("jonnyives"));
        assertThat(homePage.credentialPasswordField.getText(), is(not("apple123")));

        waitAndClickElement(driver, homePage.editCredentialButton);

        waitAndClickElement(driver, homePage.modalCredentialUrl);
        sendTextTo("www.other.com", homePage.modalCredentialUrl);

        waitAndClickElement(driver, homePage.modalCredentialUsername);
        sendTextTo("TimCook", homePage.modalCredentialUsername);

        waitAndClickElement(driver, homePage.modalCredentialPassword);
        sendTextTo("password123", homePage.modalCredentialPassword);

        click(homePage.modalCredentialSubmit);
        waitUntil(ExpectedConditions.titleContains("Result"));
        assertThat(driver.getTitle(), is("Result"));

        driver.get(baseURL + "/home");

        waitAndClickElement(driver, homePage.credentialTab);
        waitUntil(ExpectedConditions.elementToBeClickable(homePage.addCredentialButton));

        assertThat(homePage.credentialUrlField.getText(), is("www.other.com"));
        assertThat(homePage.credentialUsernameField.getText(), is("TimCook"));
        assertThat(homePage.credentialPasswordField.getText(), is(not("password123")));

        waitAndClickElement(driver, homePage.deleteCredentialButton);

        waitUntil(ExpectedConditions.titleContains("Result"));
        assertThat(driver.getTitle(), is("Result"));

    }

}
