package org.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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
//        URL url = new URL("http://13.53.92.0:4444/");
        WebDriver driverInstance = new RemoteWebDriver(url, options);
        driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverInstance.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofSeconds(7));

        setDriver(driverInstance); // Set the driver for this thread

        return driverInstance;
    }

    public static String readFileAsString(String filePath) throws IOException {
        // Returning the content of the JSON file as a String
        return new String(Files.readAllBytes(new File(filePath).toPath()));
    }

    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        // Read the content of the JSON file into a String
        String jsnContent = readFileAsString(filePath);

        // Parse the JSON content into a List of HashMaps
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> lst = mapper.readValue(jsnContent, new TypeReference<List<HashMap<String, String>>>() {
        });
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

    // Add the screenshot method
    public void takeScreenshot(String testName) {
        WebDriver driver = getDriver();
        if (driver == null) {
            throw new IllegalStateException("Driver is null. Cannot take screenshot.");
        }

        // Generate a timestamp for the screenshot
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // Take a screenshot and store it as a file format
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Store screenshot in desired location
            FileUtils.copyFile(srcFile, new File("screenshots/" + testName + "_" + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
