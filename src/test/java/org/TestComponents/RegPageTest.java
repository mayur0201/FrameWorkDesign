package org.TestComponents;

import org.BuilderPattern.Register;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pageobjects.RegisterPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegPageTest {

    WebDriver driver;

    @BeforeTest
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
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

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
