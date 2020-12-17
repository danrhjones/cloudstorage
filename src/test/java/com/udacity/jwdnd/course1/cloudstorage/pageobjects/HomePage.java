package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(css="#logout-button")
    private WebElement logoutButton;

    @FindBy(css="#add-note-button")
    private WebElement addNoteButton;

    @FindBy(css="#note-title")
    private WebElement modalNoteTitle;

    @FindBy(css="#note-description")
    private WebElement modalNoteDescription;

    @FindBy(css="#noteSubmit")
    private WebElement modalNoteSubmit;

    @FindBy(css="#nav-notes-tab")
    private WebElement noteTab;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        logoutButton.click();
    }

    public void addNote(String noteTitle, String noteDescription) {

        addNoteButton.click();
        modalNoteTitle.sendKeys(noteTitle);
        modalNoteDescription.sendKeys(noteTitle);
        modalNoteSubmit.submit();
    }

    public void openNoteTab() {
        noteTab.click();
    }
}
