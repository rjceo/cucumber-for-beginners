package com.cucumberpracticetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Common {
    public static WebDriver UseDriver(String strBrowser){
        WebDriver driver;

        if (strBrowser.trim().compareToIgnoreCase("edge") == 0){
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
        }

        return driver;
    }
}
