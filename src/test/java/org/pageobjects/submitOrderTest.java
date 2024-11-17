package org.pageobjects;


import org.TestComponents.BaseTest;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;

public class submitOrderTest extends BaseTest {

    @Test(dataProvider="getData")
    public void submitOrder(HashMap<String,String> input) throws IOException {
//        String productName = "ZARA COAT 3";
//        String countryName = "India";


        ProductCatalogue productpage = landingpage.launchApplication(input.get("email"), input.get("password"));


//        List<WebElement> productlist = productpage.getProductList();
        productpage.addProductToCart(input.get("product"));
        CartPage cartpage = productpage.clickonCart();


        Assert.assertTrue(cartpage.verifyProductOnDisplay(input.get("product")));
        CheckOutPage checkoutPage = cartpage.goToCheckoutPage();

        checkoutPage.setSelectCountry(input.get("country"));
        ConfirmationPage cnfrmpage = checkoutPage.submitOrder();

        String confirmText = cnfrmpage.getConfirmationText();
        Assert.assertTrue(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


    }


    @DataProvider

    public Object[] [] getData() throws IOException {
        List<HashMap<String ,String>> data= getJsonData(System.getProperty("user.dir") + "/src/test/java/org/Testdata/testData.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}
