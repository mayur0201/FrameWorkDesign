package org.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingpage;

    public WebDriver intializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/resources/globalData.properties");
        prop.load(fs);

        String browserName = prop.getProperty("browser");


        if (browserName.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        return driver;
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
        driver = intializeDriver();
        landingpage = new LandingPage(driver);
        landingpage.goTo();
        return landingpage;


    }

    @AfterMethod
    public void quitBrowser() {
        driver.close();
    }
}
