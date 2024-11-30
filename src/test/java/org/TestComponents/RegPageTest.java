package org.TestComponents;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.BuilderPattern.Register;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pageobjects.RegisterPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegPageTest extends BaseTest{

    WebDriver driver;

    @BeforeTest
    public void setUp() throws IOException {
        driver = intializeDriver();
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
        driver.manage().window().maximize();
    }

    @Test
    public void userRegTest()
    {
        Register register = new Register.RegisterBuilder()
                 .setFirstname("Mayur")
                 .setLastname("Mangal")
                 .setEmail("mayur.mangal@outlook.com")
                 .setTelephone("8447082275")
                 .setPassword("Ma@20485")
                 .setConfirmpassword("Ma@20485").build();

        RegisterPage rp = new RegisterPage(driver);
        rp.userRegister(register);
    }


}
