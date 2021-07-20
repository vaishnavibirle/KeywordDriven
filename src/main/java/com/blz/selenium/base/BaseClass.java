package com.blz.selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Description("Called chrome browser" +
        "used chromeOptions for handling popup" +
        "Created properties method for credentials")

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;

    public static WebDriver init_driver(String browser) throws InterruptedException {
        if(browser.equals("chrome")){
            //handle browser level show notification pop window
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            Thread.sleep(400);
        }
        return driver;
    }

    public static Properties init_properties() throws FileNotFoundException {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\irg\\IdeaProjects\\KeyWord_Driven_Framework\\src\\main\\java\\com\\blz\\selenium\\config\\config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


}
