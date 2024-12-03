package org.pageobjects;

import org.CommonComponents.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends CommonUtils {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h1[@class='hero-primary']")
    WebElement text;

    public String getConfirmationText() throws InterruptedException {
        Thread.sleep(5000);
        return text.getText();
    }
}
