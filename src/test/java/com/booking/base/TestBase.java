package com.booking.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners(ChainTestListener.class)
public class TestBase {

    public WebDriver driver;
    public Properties properties = new Properties();
    String browser;
    String url;


    public void preCondition() throws IOException
    {
        try {
            FileInputStream file = new FileInputStream("src/main/java/com/booking/properties/config.properties");
            properties.load(file);
            browser = properties.getProperty("browserName");
            url = properties.getProperty("URL");
            System.out.println("Browser Name:" + browser);
            System.out.println("URL Name:" + url);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config file.");
        }


        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Invalid browser name: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to(url);
        String title = driver.getTitle();
        ChainTestListener.log("Website Title: " + title);

        Assert.assertEquals(title, "Find cheap flights & plane tickets | Booking.com");
    }

}
