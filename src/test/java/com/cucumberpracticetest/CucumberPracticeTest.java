package com.cucumberpracticetest;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("com/cucumberpracticetest")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@IncludeTags({"login","positive"})
//@IncludeTags("positive")
//@IncludeTags("secure")
//@ExcludeTags("positive")

public class CucumberPracticeTest {
}
