package com.pavelg.tests;

import com.pavelg.tests.utils.Config;
import com.pavelg.tests.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import com.pavelg.listener.TestListener;
import java.net.MalformedURLException;
import java.net.URL;

@Listeners({TestListener.class})
public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeTest
    public void setUp(ITestContext ctx) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.SELENIUM_GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if (Config.get(Constants.BROWSER).equalsIgnoreCase(Constants.FIREFOX)) {
            capabilities = new FirefoxOptions();
        }

        String urlFormat = Config.get(Constants.SELENIUM_GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.SELENIUM_GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);

        logger.info("Grid url: {}", url);

        return new RemoteWebDriver(new URL(url), capabilities);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
            this.driver = null;
        }
    }
}
