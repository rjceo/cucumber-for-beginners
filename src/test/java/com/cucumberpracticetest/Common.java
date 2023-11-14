package com.cucumberpracticetest;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;
import java.util.UUID;

public class Common {
    private  String screenShot;
    private  String screenShotExt;

    public Common() {
        try {
            InputStream fs = Files.newInputStream(Paths.get("config/test.properties"));
            Properties prop = new Properties();
            prop.load(fs);

            screenShot = prop.getProperty("screenshot.directory").trim();
            screenShotExt = prop.getProperty("screenshot.extension").trim();

            if (screenShot.charAt(screenShot.length()-1) != '/'){
                screenShot += "/";
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public WebDriver UseDriver(String strBrowser){
        WebDriver driver;

/*
          System.setProperty("webdriver.edge.com", "src/main/resources/msedgedriver.exe");
          System.setProperty("webdriver.chrome.com", "src/main/resources/chromedriver.exe");
          System.setProperty("webdriver.gecko.com", "src/main/resources/geckodriver.exe");
*/

        if (strBrowser.trim().compareToIgnoreCase("edge") == 0)
            driver = new EdgeDriver();
        else if (strBrowser.trim().compareToIgnoreCase("chrome") == 0)
            driver = new ChromeDriver();
        else //firefox
            driver = new FirefoxDriver();

        return driver;
    }

    public void clickElement(WebDriver wb, By xp){
        WebDriverWait wait = new WebDriverWait(wb, Duration.ofSeconds(10));

        if (wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(wb.findElement(xp)),
                ExpectedConditions.elementToBeClickable(xp)))){
            wb.findElement(xp).click();
        }
        else
            Assertions.fail("Element is not visible and/or clickable.");
    }
    public Boolean isElementDisplayed(WebDriver wb, By xp){
        WebElement we = wb.findElement(xp);

        if (we != null) {
            return we.isDisplayed();
        } else {
            return false;
        }
    }

    public void takeScreenshot(WebDriver wb){
        TakesScreenshot ts = (TakesScreenshot) wb;

        try{
            FileHandler.copy(ts.getScreenshotAs(OutputType.FILE)
                    , new File(screenShot + UUID.randomUUID() + screenShotExt));
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }
}
