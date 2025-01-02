package com.revature.pom;

import com.revature.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
    This class works for both the Login and Registration pages
    The id's for interactions are uniform across either page, so logic is consolidated
    into one class
 */
public class Signup {

    @FindBy(id = "usernameInput")
    private WebElement userField;

    @FindBy(id = "passwordInput")
    private WebElement passwordField;

    @FindBy(linkText = "Create an Account")
    private WebElement registerLink;


    public Signup() {
        PageFactory.initElements(TestRunner.driver, this);
    }

    public void clickRegister() {
        registerLink.click();
    }

    public void insertText(String text, String field) throws Exception {
        switch (field) {
            case "username":
                userField.sendKeys(text);
                break;

            case "password":
                passwordField.sendKeys(text);
                break;

            default:
                throw new Exception("Invalid field");
        }
    }

    public void submit() {
        TestRunner.driver
                .findElement(By.xpath("//input[@type='submit']"))
                .click();
    }
}
