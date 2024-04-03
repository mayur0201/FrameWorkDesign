package org.pageobjects;

import org.CommonComponents.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends CommonUtils {

    WebDriver driver ;
    public ProductCatalogue(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".mb-3")
    List<WebElement> productList;

    @FindBy(css=".ng-animating")
    WebElement animation;


    By product = By.cssSelector(".mb-3");
    By addCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    public List<WebElement> getProductList()
    {
        waitForElementToAppear(product);
        return productList;
    }

    public WebElement getProductByName(String productName)
    {

       //Fethcjing the product name using Java Streams
        WebElement prod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).
                getText().equalsIgnoreCase(productName) ).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productname)
    {
        WebElement productToadd = getProductByName(productname);
        productToadd.findElement(addCart).click();
        waitForElementToAppear(toastMessage);
        checkforInvisibilityofElement(animation);
    }




}
