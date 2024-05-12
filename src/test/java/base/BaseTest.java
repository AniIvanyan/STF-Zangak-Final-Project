package base;

import aua.stf.pom.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public abstract class BaseTest {

    public static final String PATH = "src/main/resources/";
    public static final String PNG_EXTENSION = ".png";

    protected static WebDriver driver;
    protected static WebDriverWait webDriverWait;

    @BeforeMethod
    public void setupSuite() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        URL hubUrl = new URL("http://localhost:4444/wd/hub");
        driver = new RemoteWebDriver(hubUrl, capabilities);
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.navigate().to(BasePage.BASE_URL);
    }

    @AfterMethod
    public void teardownSuite() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot screenshotMaker = (TakesScreenshot) driver;
            Path screenshotFile = screenshotMaker.getScreenshotAs(OutputType.FILE).toPath();
            Path destination = Paths.get(PATH + result.getStartMillis() + "-" + "screenshot" + PNG_EXTENSION);
            try {
                Files.move(screenshotFile, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
