package org.pageobjects;

import org.BuilderPattern.Register;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;
    private final By firstname = By.id("input-firstname");
    private final By lastname = By.id("input-lastname");
    private final By email = By.id("input-email");
    private final By telephone = By.id("input-telephone");
    private final By password = By.id("input-password");
    private final By confirmpassword = By.id("input-confirm");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void userRegister(Register register) {
     driver.findElement(firstname).sendKeys(register.getFirstname());
     driver.findElement(lastname).sendKeys(register.getLastname());
     driver.findElement(email).sendKeys(register.getEmail());
     driver.findElement(telephone).sendKeys(register.getTelephone());
     driver.findElement(password).sendKeys(register.getPassword());
     driver.findElement(confirmpassword).sendKeys(register.getConfirmpassword());
    }

}
