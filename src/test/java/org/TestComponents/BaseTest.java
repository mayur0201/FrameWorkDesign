package org.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.LandingPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    private static volatile BaseTest instance;

    private static ThreadLocal<WebDriver> t1 = new ThreadLocal<>();
    public LandingPage landingpage;

    // Private constructor to prevent instantiation
    private BaseTest() {
    }

    // Singleton method to get the instance of BaseTest
    public static BaseTest getInstance() {
        if (instance == null) {
            synchronized (BaseTest.class) {
                if (instance == null) {
                    instance = new BaseTest();
                }
            }
        }
        return instance;
    }

    // Private method to initialize WebDriver
    private void initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/resources/globalData.properties");
        prop.load(fs);

        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("Chrome")) {
            t1.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("edge")) {
            t1.set(new EdgeDriver());
        }


        t1.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(t1.get(), Duration.ofSeconds(7));
    }

    public WebDriver getDriver() {
        if (t1.get() == null) {
            try {
                initializeDriver();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return t1.get();
    }

    public String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(new File(filePath).toPath()));
    }

    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        String jsnContent = readFileAsString(filePath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsnContent, new TypeReference<List<HashMap<String, String>>>() {
        });
    }

    @BeforeClass
    public LandingPage launchApplication() throws IOException {
        getInstance();
        landingpage = new LandingPage(getDriver());
        landingpage.goTo();
        return landingpage;
    }

    @AfterClass
    public void quitBrowser() {
        getDriver().close();
        t1.remove();
    }
}
