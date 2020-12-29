package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

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

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        logoutButton.click();
    }

}
