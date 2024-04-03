package org.pageobjects;

import org.CommonComponents.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends CommonUtils {

    WebDriver driver;

    public CheckOutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[placeholder]")
    WebElement country;

    @FindBy(css = ".action__submit")
    WebElement actionSubmit;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;

    By element = By.cssSelector(".ta-results");

    public void setSelectCountry(String countryName)
    {
        Actions act = new Actions(driver);

        act.sendKeys(country,countryName).build().perform();

        waitForElementToAppear(element);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder()
    {
        actionSubmit.click();
        ConfirmationPage cnfrmpage  = new ConfirmationPage(driver);
        return cnfrmpage;

    }
}
