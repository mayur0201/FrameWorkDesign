package org.pageobjects;

import org.CommonComponents.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends CommonUtils {

    WebDriver driver ;
    public CartPage(WebDriver driver)

    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//*[@class='cartSection']/h3")
    List<WebElement> productTitles;

    @FindBy(css=".totalRow button")
    WebElement checkoutEle;



    public Boolean verifyProductOnDisplay(String productName)
    {
        Boolean flag = productTitles.stream().anyMatch(cartProduct -> cartProduct
                .getText().equalsIgnoreCase(productName));

        return flag;

    }

    public CheckOutPage goToCheckoutPage()
    {
        checkoutEle.click();
        CheckOutPage checkoutPage = new CheckOutPage(driver);
        return checkoutPage;

    }




}
