package com.revature.registration;

import com.revature.TestRunner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Register {


    @Given("user is on Login page")
    public void userIsOnLoginPage() {
        TestRunner.driver.get("localhost:8080");

        Assert.assertEquals("http://localhost:8080/", TestRunner.driver.getCurrentUrl());
    }

    @When("user clicks register")
    public void userClicks() {
        TestRunner.logger.info("Register button clicked");
        TestRunner.signup.clickRegister();
    }

    @Then("they are sent to the Register page")
    public void theyAreSentToTheLoginPage() {
        Assert.assertEquals("http://localhost:8080/register", TestRunner.driver.getCurrentUrl());

    }

    @When("user provides username {string}")
    public void userProvidesUsername(String arg0) {

        try {
            TestRunner.signup.insertText(arg0, "username");

        } catch (Exception e) {
            Assert.fail(e.getMessage());

        }
    }

    @And("user provides password {string}")
    public void userProvidesPassword(String arg0) {

        try {
            TestRunner.signup.insertText(arg0, "password");

        } catch (Exception e) {
            Assert.fail(e.getMessage());

        }
    }

    @And("user submits their inputs")
    public void userSubmitsTheirInputs() {
        TestRunner.signup.submit();
    }

    @Then("user receives browser alert {string}")
    public void userReceivesBrowserAlert(String arg0) {
        String response = TestRunner.driver.switchTo().alert().getText();

        TestRunner.driver.switchTo().alert().accept();

        Assert.assertEquals(arg0, response);
    }

    @And("user is sent back to the Login page")
    public void userIsSentBackToTheLoginPage() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Planetarium Login"));

        Assert.assertEquals("http://localhost:8080/", TestRunner.driver.getCurrentUrl());
    }

    @And("user stays on the Registration page")
    public void userStaysOnTheRegistrationPage() {
        Assert.assertEquals("http://localhost:8080/register", TestRunner.driver.getCurrentUrl());
    }
}
