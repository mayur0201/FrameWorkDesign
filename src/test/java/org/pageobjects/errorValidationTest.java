package org.pageobjects;


import org.TestComponents.BaseTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class errorValidationTest extends BaseTest {

    @Test
    public void errorValidation() throws IOException {

        landingpage.launchApplication("mmangal@test.com", "Ma@20485121");

        Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

    }

    @Test
    public void productPageErrorValidation() {
        String productName = "ZARA COAT 3";

        ProductCatalogue productpage = landingpage.launchApplication("mayur.mangal@test.com", "Ma@20485");

        productpage.addProductToCart(productName);
        CartPage cartpage = productpage.clickonCart();
        Assert.assertFalse(cartpage.verifyProductOnDisplay("ZARA COAT 3121"));
    }
}
