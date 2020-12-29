package com.udacity.jwdnd.course1.cloudstorage.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage {

    @FindBy(css="#return-home")
    private WebElement returnHome;

    public ResultsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void clickContinueButton() {
        returnHome.click();
    }
}
