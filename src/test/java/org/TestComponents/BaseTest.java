package org.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.LandingPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public LandingPage landingpage;

    // Thread-safe getter for WebDriver
    public WebDriver getDriver() {
        return driver.get();
    }

    // Thread-safe setter for WebDriver
    public void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Initialize WebDriver with common configurations
    public WebDriver initializeDriver() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setAcceptInsecureCerts(true);
        URL url = new URL("http://localhost:4444/wd/hub");
//          URL url = new URL("http://16.171.226.172:4444/");
        WebDriver driverInstance = new RemoteWebDriver(url, options);
        driverInstance.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverInstance.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofSeconds(7));

        setDriver(driverInstance); // Set the driver for this thread

        return driverInstance;
    }

    // Parse JSON data from a file into a List of HashMaps
    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        String jsonContent = Files.readString(new File(filePath).toPath());
        return new ObjectMapper().readValue(jsonContent, new TypeReference<>() {});
    }

    // Launch the application and return the LandingPage object
    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        WebDriver driver = initializeDriver();
        landingpage = new LandingPage(driver);
        landingpage.goTo();
        return landingpage;
    }

    // Quit the browser after each test
    @AfterMethod
    public void quitBrowser() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    // Take a screenshot and save it with a timestamp
    public void takeScreenshot(String testName) {
        try {
            File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            FileUtils.copyFile(srcFile, new File("screenshots/" + testName + "_" + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}