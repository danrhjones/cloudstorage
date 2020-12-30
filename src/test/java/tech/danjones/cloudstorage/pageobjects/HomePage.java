package tech.danjones.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Utils {

    @FindBy(css=".note-title")
    public WebElement noteTitleField;

    @FindBy(css=".note-description")
    public WebElement noteDescriptionField;

    @FindBy(css="#logout-button")
    private WebElement logoutButton;

    @FindBy(css="#add-note-button")
    public WebElement addNoteButton;

    @FindBy(css="#note-title")
    public WebElement modalNoteTitle;

    @FindBy(css="#note-description")
    public WebElement modalNoteDescription;

    @FindBy(css="#noteSubmit")
    public WebElement modalNoteSubmit;

    @FindBy(css="#nav-notes-tab")
    public WebElement noteTab;

    @FindBy(css=".edit-note")
    public WebElement editNoteButton;

    @FindBy(css=".delete-note")
    public WebElement deleteNoteButton;

    @FindBy(css="#nav-credentials-tab")
    public WebElement credentialTab;

    @FindBy(css="#add-credential-button")
    public WebElement addCredentialButton;

    @FindBy(css="#credential-url")
    public WebElement modalCredentialUrl;

    @FindBy(css="#credential-username")
    public WebElement modalCredentialUsername;

    @FindBy(css="#credential-password")
    public WebElement modalCredentialPassword;

    @FindBy(css="#credentialSubmit")
    public WebElement modalCredentialSubmit;

    @FindBy(css=".credential-url")
    public WebElement credentialUrlField;

    @FindBy(css=".credential-username")
    public WebElement credentialUsernameField;

    @FindBy(css=".credential-password-key")
    public WebElement credentialPasswordField;

    @FindBy(css=".delete-credential")
    public WebElement deleteCredentialButton;

    @FindBy(css=".edit-credential")
    public WebElement editCredentialButton;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        logoutButton.click();
    }

}
