package io.github.dominys.dizvoter.component.tests;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
    value = "pretty, html:target/reports/cucumber.html, json:target/reports/report.json, " +
        "junit:target/reports/junit.xml")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME,
    value = "io.github.dominys.dizvoter.component.tests")
public class RunnerForIntegrationTestIT {
}
