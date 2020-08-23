package com.gintaras.luminor.payments.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Cucumber tests runner.
 *
 * @created 22/08/2020 - 12:28 PM
 * @author gintaras
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberTest {
}
