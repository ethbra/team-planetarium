package com.revature.pom;

import com.revature.TestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Home {

    @FindBy(xpath = "//input[@id = 'deleteInput']")
    private WebElement bodyName;

    @FindBy(xpath = "//input[@id = 'planetNameInput']")
    private WebElement planetName;

    @FindBy(xpath = "//input[@id = 'moonNameInput']")
    private WebElement moonName;

    @FindBy(xpath = "//input[@id = 'orbitedPlanetInput']")
    private WebElement planetID;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement browseFiles;

    //  Submit button is celestial body agnostic
    @FindBy(xpath = "//button[@class='submit-button']")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@id='deleteButton']")
    private WebElement deleteButton;

    @FindBy(xpath = "//h1")
    private WebElement heading;

    @FindBy(xpath = "//select")
    private WebElement selectBodyType;

    public Home() {
        PageFactory.initElements(TestRunner.driver, this);
    }

    public void fillDeletionText(String body) {
        bodyName.sendKeys(body);
    }

    public void fillPlanetText(String planetName) {
        this.planetName.sendKeys(planetName);
    }

    public void fillMoonText(String moonName) {
        this.moonName.sendKeys(moonName);
    }

    public void fillIdText(String id) {
        this.planetID.sendKeys(id);
    }

    public void enterFile(String file) {
        browseFiles.sendKeys(file);
    }

    public void selectType(String type) {
        Select planetType = new Select(TestRunner.driver.findElement(By.xpath("//select[@id = 'locationSelect']")));
        if (type.equals("moon"))
            planetType.selectByIndex(0);
        else planetType.selectByIndex(1);
    }

    public void submit() {
        submitButton.click();
    }

    public void delete() {
        deleteButton.click();
    }

    public int countBodies() {
        List<WebElement> images = TestRunner.driver.findElements(By.xpath("//img"));
        return images.size();
    }


    // interesting
    public boolean findBody(String arg0) {
        try {
            TestRunner.driver.findElement(By.xpath("//td[(text() = '" + arg0 + "')]"));
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
