package org.pageobjects;

import org.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class submitOrderTest {

    private BaseTest baseTest = BaseTest.getInstance();

    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String, String> input) throws IOException {
        LandingPage landingpage = baseTest.launchApplication();
        ProductCatalogue productpage = landingpage.launchApplication(input.get("email"), input.get("password"));
        productpage.addProductToCart(input.get("product"));
        CartPage cartpage = productpage.clickonCart();
        Assert.assertTrue(cartpage.verifyProductOnDisplay(input.get("product")));
        CheckOutPage checkoutPage = cartpage.goToCheckoutPage();
        checkoutPage.setSelectCountry(input.get("country"));
        ConfirmationPage cnfrmpage = checkoutPage.submitOrder();
        String confirmText = cnfrmpage.getConfirmationText();
        Assert.assertTrue(confirmText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        checkoutPage.logout();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = baseTest.getJsonData(System.getProperty("user.dir") + "/src/test/java/org/Testdata/testData.json");
        Object[][] testData = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i);
        }
        return testData;
    }
}
