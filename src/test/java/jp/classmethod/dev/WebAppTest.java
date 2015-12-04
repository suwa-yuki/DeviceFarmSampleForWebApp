package jp.classmethod.dev;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.io.File;

import static org.junit.Assert.*;

public class WebAppTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "iPhone 5s");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "9.1");
        capabilities.setCapability("browserName", "safari");
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void runTest() throws Exception {
        driver.get("http://dev.classmethod.jp");
        Thread.sleep(1000);
        WebElement idElement = driver.findElement(By.className("logo"));
        assertNotNull(idElement);
        assertEquals("Developers.IO", idElement.getText());
        takeScreenshot("Top Page");
    }

    public boolean takeScreenshot(final String name) {
       String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
       File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
