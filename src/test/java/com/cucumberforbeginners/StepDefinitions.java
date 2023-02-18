package com.cucumberforbeginners;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class StepDefinitions {

    @Given("an example scenario")
    public void anExampleScenario() {
        System.setProperty("webdriver.chrome.com", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @When("all step definitions are implemented")
    public void allStepDefinitionsAreImplemented() {

    }

    @Then("the scenario passes")
    public void theScenarioPasses() {
        Assert.fail();
    }

}
