package org.pageobjects;

import org.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class errorValidationTest {

    private BaseTest baseTest = BaseTest.getInstance();

    @Test
    public void errorValidation() throws IOException {
        LandingPage landingpage = baseTest.launchApplication();
        landingpage.launchApplication("mmangal@test.com", "Ma@20485121");
        Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
    }

    @Test
    public void productPageErrorValidation() throws IOException {
        String productName = "ZARA COAT 3";

        LandingPage landingpage = baseTest.launchApplication();
        ProductCatalogue productpage = landingpage.launchApplication("mayur.mangal@test.com", "Ma@20485");
        productpage.addProductToCart(productName);
        CartPage cartpage = productpage.clickonCart();
        Assert.assertFalse(cartpage.verifyProductOnDisplay("ZARA COAT 3121"));
    }
}
