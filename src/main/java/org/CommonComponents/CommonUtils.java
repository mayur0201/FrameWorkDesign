package org.CommonComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.CartPage;
import org.pageobjects.CheckOutPage;

import java.time.Duration;

public class CommonUtils {


    WebDriver driver;

    @FindBy(css="[routerlink*='cart']")
    WebElement cartButton;

    @FindBy(xpath = "//button[contains(text(), 'Sign Out')]")
    WebElement logout;

    public CommonUtils(WebDriver driver) {
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    public void waitForElementToAppear(By findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }



    public void checkforInvisibilityofElement(WebElement findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
        wait.until(ExpectedConditions.invisibilityOf(findBy));
    }

    public CartPage clickonCart()
    {
        cartButton.click();
        CartPage cartpage = new CartPage(driver);
        return cartpage;
    }

    public  CheckOutPage logout()
    {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        logout.click();
        return checkOutPage;
    }
}
