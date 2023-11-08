package com.cucumberpracticetest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StepDefinitions {

    WebDriver driver;
    Common common = new Common();

    @Before
    public void beforeScenario() {
        try{
            driver = common.UseDriver("firefox");
            driver.manage().window().maximize();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            if (driver != null)
                driver.quit();
        }
    }

    @After
    public void afterScenario() {
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
        common.clickElement(driver, By.xpath("//button[@type='submit']"));
        Thread.sleep(common.WaitMS);
    }

    @Then("^page is redirected to \"(.*?)\"$")
    public void pageIsRedirectedTo(String strExpectedURL) {
        VerifyURL(driver.getCurrentUrl().trim(), strExpectedURL);
    }

    @And("^page remains at \"(.*?)\"$")
    public void pageRemainsAt(String strExpectedURL) {
        VerifyURL(driver.getCurrentUrl().trim(), strExpectedURL);
    }

    public void VerifyURL(String prevURL, String nextURL) {
        if (prevURL.compareToIgnoreCase(nextURL.trim()) != 0) {
            Assertions.fail(
                    "Expected: " + nextURL
                            + "\n Actual: " + prevURL
            );
        }
    }


    @When("^element xpath \"(.*?)\" is clicked$")
    public void elementXpathIsClicked(String strxpath) throws InterruptedException {
        WebElement we = driver.findElement(By.xpath(strxpath.trim()));
        we.click();
        Thread.sleep(common.WaitMS);
    }

    @When("clicks on the logout button")
    public void clicksOnTheLogoutButton() throws InterruptedException{
        common.clickElement(driver, By.xpath("//a[@class='button secondary radius'][@href='/logout']"));
        Thread.sleep(common.WaitMS);
    }

    @Then("the label {string} is displayed")
    public void theLabelIsDisplayed(String strLabel) throws InterruptedException{
        String strPar = "//div/label[contains(text(),'" + strLabel + "')]";
        if (!common.isElementDisplayed(driver, By.xpath(strPar)))
            Assertions.fail(strLabel.concat(": label is not as expected OR label is not displayed"));
        Thread.sleep(common.WaitMS);
    }

    @And("the empty textbox below the {string} is displayed")
    public void theEmptyTextboxBelowTheUsernameIsDisplayed(String strLabel) throws InterruptedException{
        String strPar = "//div/label[contains(text(),'" + strLabel + "')]/following-sibling::input";
        if (!common.isElementDisplayed(driver, By.xpath(strPar)))
            Assertions.fail("Textbox ".concat(strLabel).concat(" is not displayed."));

        if (!driver.findElement(By.xpath(strPar)).getText().isEmpty())
           Assertions.fail("Textbox ".concat(strLabel).concat(" is not empty."));

        Thread.sleep(common.WaitMS);
    }

    @Then("the login button is displayed")
    public void theLoginButtonIsDisplayed() throws InterruptedException{
        String strPath = "//button[@type='submit']";
        if (!common.isElementDisplayed(driver, By.xpath(strPath)))
            Assertions.fail(strPath.concat(" is not displayed."));
        Thread.sleep(common.WaitMS);
    }

    @Then("message {string} is displayed")
    public void MessageIsDisplayed(String strError) throws InterruptedException{
        String strPar = "//div[@id='flash'][contains(text(),'" + strError + "')]";
        if (!common.isElementDisplayed(driver, By.xpath(strPar)))
            Assertions.fail(strError.concat(" is not displayed."));

        /*
        * The driver returns a message with a new line (\n)
        * in between the actual message and an 'x' character.
        * */
        String[] strActual = driver.findElement(By.xpath(strPar)).getText().trim().split("\n");
        if (!strActual[0].equals(strError))
            Assertions.fail("Error message is not as expected.");

        Thread.sleep(common.WaitMS);
    }

    @And("the logout button is displayed")
    public void theLogoutButtonIsDisplayed() throws InterruptedException{
        String strPath = "//div[@class='example']/h2/following-sibling::h4/following-sibling::a[@class='button secondary radius']/i[contains(text(),'Logout')]";
        if(!common.isElementDisplayed(driver, By.xpath(strPath)))
            Assertions.fail(strPath.concat(" is not displayed."));
        Thread.sleep(common.WaitMS);
    }

    @And("the header {string} is displayed")
    public void theHeaderIsDisplayed(String strHeader) throws InterruptedException{
        String strPath="//div[@class='example']/h2[contains(text(),'" + strHeader + "')]";
        if(!common.isElementDisplayed(driver, By.xpath(strPath)))
            Assertions.fail(strPath.concat(" is not displayed."));

        Thread.sleep(common.WaitMS);
    }

    @And("the sub-header {string} is displayed")
    public void theSubHeaderIsDisplayed(String strSubHeader) throws InterruptedException {
        String strPath="//div[@class='example']/h2/following-sibling::h4[contains(text(),'" + strSubHeader + "')]";
        if(!common.isElementDisplayed(driver, By.xpath(strPath)))
            Assertions.fail(strPath.concat(" is not displayed."));

        Thread.sleep(common.WaitMS);
    }
}
