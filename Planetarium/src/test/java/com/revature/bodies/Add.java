package com.revature.bodies;

import com.revature.TestRunner;
import com.revature.util.Steps;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Add {

    private int numOfRows;

    @Given("user on Home page")
    public void userOnHomePage() {
        Assert.assertEquals("http://localhost:8080/planetarium", TestRunner.driver.getCurrentUrl());
    }

    @And("user is logged in")
    public void userIsLoggedIn() {
        /*
            Looks for "pre" element tag (what appears when load planetarium while logged out)
            If error results (no pre tag), then user logged in, so, success!

            This feels like bad practice but ¯\_(ツ)_/¯
         */
        try {
            WebElement res = TestRunner.driver.findElement(By.xpath("//pre"));

            Assert.fail("Planetarium didn't load");

        } catch (NoSuchElementException e) {
            TestRunner.logger.info("successful login");

        }
    }

    /**
     * Puts planet name in planet text field
     *
     * @param arg0 Planet name
     */
    @When("user enters planet {string}")
    public void userEntersPlanet(String arg0) {
        TestRunner.home.fillPlanetText(arg0);
    }

    @And("user enters file {string}")
    public void userEntersFile(String arg0) {
        if (arg0.isEmpty())
            return;

        arg0 = Steps.appendFile(arg0);

        TestRunner.home.enterFile(arg0);
    }

    @And("user clicks to submit")
    public void userClicksToSubmit() {

        // get the tables and count
        numOfRows = Steps.getTableCount();

        // press submit button
        TestRunner.home.submit();
    }


    // ===== Moons ===== //


    /**
     * Puts moon name in moon text field
     *
     * @param arg0 moon name
     */
    @When("user enters moon {string}")
    public void userEntersMoon(String arg0) {
        TestRunner.home.fillMoonText(arg0);
    }

    @And("user enters owner planet {string}")
    public void userEntersOwnerPlanet(String arg0) {
        TestRunner.home.fillIdText(arg0);
    }


    // Depends on alert being present for sad paths, and absent for happy paths
    // This is always the case, according to the wireframe
    @And("table reflects {string}")
    public void tableReflects(String arg0) {
        int numOfRowsNew = Steps.getTableCount();

        if (arg0.isEmpty()) {

            TestRunner.logger.warn("Rows should be different %n old: {}, new: {}", numOfRows, numOfRowsNew);

            Assert.assertNotEquals(numOfRows, numOfRowsNew);

        } else {
            TestRunner.logger.warn("Rows shouldn't be different %n old: {}, new: {}", numOfRows, numOfRowsNew);

            Assert.assertEquals(numOfRows, numOfRowsNew);

        }
    }

}
