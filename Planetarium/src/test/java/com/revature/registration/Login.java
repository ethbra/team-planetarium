package com.revature.registration;

import com.revature.TestRunner;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class Login {

    @Then("user is sent to Home page")
    public void userIsSentToHomePage() {
        Assert.assertEquals("http://localhost:8080/planetarium", TestRunner.driver.getCurrentUrl());
    }

    @And("user stays on Login page")
    public void userStaysOnLoginPage() {
        Assert.assertEquals("http://localhost:8080/", TestRunner.driver.getCurrentUrl());
    }
}
