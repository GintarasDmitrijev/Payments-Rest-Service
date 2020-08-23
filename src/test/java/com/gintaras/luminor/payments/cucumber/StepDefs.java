package com.gintaras.luminor.payments.cucumber;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Steps definitions for Cucumber tests.
 *
 * @created 22/08/2020 - 12:37 PM
 * @author gintaras
 */
public class StepDefs extends SpringIntegrationTest  {

    @When("^the client calls /payments$")
    public void the_client_issues_GET_payments() throws Throwable{
        executeGet("http://localhost:8080/payments");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : "+
                latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @When("^the client calls /payments/1$")
    public void the_client_issues_GET_one_payment() throws Throwable{
        executeGet("http://localhost:8080/payments/1");
    }

    @When("^the client calls /payments/4/cancel$")
    public void the_client_issues_PUT_cancel_one_payment() throws Throwable{
        executePut("http://localhost:8080/payments/4/cancel");
    }
}
