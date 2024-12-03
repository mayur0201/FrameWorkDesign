package org.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.pageobjects.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import java.util.HashMap;
import java.util.List;


public class BaseTest {


    public LandingPage landingpage;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public WebDriver initializeDriver() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setAcceptInsecureCerts(true);

        URL url = new URL("http://localhost:4444/wd/hub");
        WebDriver driverInstance = new RemoteWebDriver(url, options);

        setDriver(driverInstance); // Set the driver for this thread

        return driverInstance;
    }

    public static String readFileAsString(String filePath) throws IOException {

        // Returning the content of the JSON file as a String
        return new String(Files.readAllBytes(new File(filePath).toPath()));
    }

    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {

        // Read the content of the JSON file into a String
        String jsnContent= readFileAsString(filePath);

        // Parse the JSON content into a List of HashMaps
        ObjectMapper mapper  = new ObjectMapper();
        List<HashMap<String, String>> lst=mapper.readValue(jsnContent, new TypeReference<List<HashMap<String,String>>>(){});
        return lst;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        WebDriver driver = initializeDriver();  // Ensure a new driver is initialized before each test
        landingpage = new LandingPage(driver);
        landingpage.goTo();
        return landingpage;


    }

    @AfterMethod
    public void quitBrowser() {
        driver.get().quit();
    }
}
