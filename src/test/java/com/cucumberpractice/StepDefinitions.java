package com.cucumberpractice;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class StepDefinitions {

    WebDriver driver;

    @Before
    public void beforeScenario() {
        System.setProperty("webdriver.chrome.com", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void afterScenario() {
        //driver.close();
        driver.quit();
    }

    @Given("^login page \"(.*?)\" is open$")
    public void loginPage(String strURL) {
        driver.get(strURL);
    }

    @When("^user enters \"(.*?)\" as the Username$")
    public void userEntersAsTheUsername(String strUsername) {
        driver.findElement(By.id("username")).sendKeys(strUsername.trim());
    }

    @And("^user enters \"(.*?)\" as the Password$")
    public void userEntersAsThePassword(String strPassword) {
        driver.findElement(By.id("password")).sendKeys(strPassword.trim());
    }

    @And("clicks on the login button")
    public void clicksOnTheLoginButton() throws InterruptedException {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
    }

    @Then("^page is redirected to \"(.*?)\"$")
    public void pageIsRedirectedTo(String strExpectedURL) {
        String strActualURL = driver.getCurrentUrl().toLowerCase().trim();
        if (strActualURL.compareTo(strExpectedURL.trim().toLowerCase()) != 0) {
            Assert.fail(
                    "Expected: " + strExpectedURL
                            + "\n Actual: " + strActualURL
            );
        }
    }

    @And("^message \"(.*?)\" is displayed$")
    public void messageIsDisplayed(String strExpectedMesg) {
        String[] strActualMesg = driver.findElement(By.id("flash")).getText().trim().split("\n");

        if (strActualMesg[0].compareTo(strExpectedMesg.trim()) != 0) {
            Assert.fail(
                    "Expected: " + strExpectedMesg
                            + "\n Actual: " + strActualMesg[0]
            );
        }
    }
}
