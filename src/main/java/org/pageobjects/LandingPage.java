package org.pageobjects;

import org.CommonComponents.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends CommonUtils {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement email;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement login;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorTest;

    public ProductCatalogue launchApplication(String useremail, String password) {
        email.sendKeys(useremail);
        userPassword.sendKeys(password);
        login.click();
        ProductCatalogue productpage = new ProductCatalogue(driver);
        return productpage;

    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorTest);
        return errorTest.getText();
    }


}
