package com.revature.bodies;

import com.revature.TestRunner;
import com.revature.util.Steps;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class Delete {

    int initialRows;

    // TODO: When searching for the value of a term in the tables,
    //  just search with xpath text: "//td[text() = 'planet']"

    @When("user selects {string} option")
    public void userSelectsOption(String arg0) {
        switch (arg0) {
            case "planet":
                TestRunner.home.selectType("planet");
                break;
            case "moon":
                TestRunner.home.selectType("moon");
                break;
            default:
                Assert.fail("Invalid option: " + arg0);
        }
    }

    @And("user enters {string}")
    public void userEnters(String arg0) {
        TestRunner.home.fillDeletionText(arg0);
    }

    @And("user presses delete")
    public void userPressesDelete() {
        initialRows = Steps.getTableCount();
        TestRunner.home.delete();
    }

    @And("table reflects deletion by {string}")
    public void tableReflectsDeletionBy(String arg0) {
        if (arg0.isEmpty()) {
            Assert.assertNotEquals(initialRows, Steps.getTableCount());
        } else {
            Assert.assertEquals(initialRows, Steps.getTableCount());
        }
    }

    @Then("{string} is deleted")
    public void isDeleted(String arg0) {
        Assert.assertFalse(TestRunner.home.findBody(arg0));
    }
}
