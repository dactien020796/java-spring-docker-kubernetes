package com.example.experiment.cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
// Specify where are the feature files
@SelectClasspathResource("features")
// Generate HTML/Json report
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty," +
        "html:target/SystemTestReports/report.html," +
        "json:target/SystemTestReports/json/report.json")
// Glue to map step with stepDefinition.java (specify the package name of Java class)
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.experiment.cucumber.steps")
// Run BDD with tag filter
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@UAT or @SmokeTest or @RegularTest")
public class MenuManagementTest {
}
