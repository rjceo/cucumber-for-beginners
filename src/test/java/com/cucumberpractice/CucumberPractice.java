package com.cucumberpractice;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        //tags = "@negative or @positive"
    tags = "@securepage"
)

public class CucumberPractice {
}
