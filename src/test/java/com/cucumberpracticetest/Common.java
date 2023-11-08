package com.cucumberpracticetest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;

public class Common {
    public int WaitMS;
    private  String screenShot;
    private  String screenShotExt;

    public Common() {
        try {
            InputStream fs = Files.newInputStream(Paths.get("config/test.properties"));
            Properties prop = new Properties();
            prop.load(fs);

            WaitMS = Integer.parseInt(prop.getProperty("waitms").trim());
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

/*        if (strBrowser.trim().compareToIgnoreCase("edge") == 0){
            System.setProperty("webdriver.edge.com", "src/main/resources/msedgedriver.exe");
            EdgeOptions ops = new EdgeOptions();
            ops.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(ops);
        }
        else if (strBrowser.trim().compareToIgnoreCase("chrome") == 0) {
            System.setProperty("webdriver.chrome.com", "src/main/resources/chromedriver.exe");
            ChromeOptions ops2 = new ChromeOptions();
            ops2.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(ops2);
        }
        else { //Firefox
            System.setProperty("webdriver.gecko.com", "src/main/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }*/

        if (strBrowser.trim().compareToIgnoreCase("edge") == 0)
            driver = new EdgeDriver();
        else if (strBrowser.trim().compareToIgnoreCase("chrome") == 0)
            driver = new ChromeDriver();
        else //firefox
            driver = new FirefoxDriver();

        return driver;
    }

    public void clickElement(WebDriver wb, By xp){
        WebElement we = wb.findElement(xp);
        we.click();
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
