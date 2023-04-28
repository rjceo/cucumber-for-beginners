package com.cucumberpractice;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        //tags = "@negative or @positive"
    tags = "@loginpage and @positive"
)

public class CucumberPractice {
}
