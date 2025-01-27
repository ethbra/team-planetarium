## Outline
### Covered Topics
- [Selenium](https://github.com/EricTrainingRev/241209-JWA/blob/fe8940dad3b6c366269a915444d6426bd8b75a59/Study%20Guides/Selenium%20WebDriver%20Study%20Guide.md#selenium-webdriver-study-guide)
- [E2E Testing](https://github.com/EricTrainingRev/241209-JWA/blob/fe8940dad3b6c366269a915444d6426bd8b75a59/Study%20Guides/BDD%20-%20Gherkin%20%26%20Cucumber%20Study%20Guide.md#end-to-end-testing)
- [Gherkin/Cucumber](https://github.com/EricTrainingRev/241209-JWA/blob/fe8940dad3b6c366269a915444d6426bd8b75a59/Study%20Guides/BDD%20-%20Gherkin%20%26%20Cucumber%20Study%20Guide.md#cucumber)
### Documents
- [Selenium WebDriver Study Guide](https://github.com/EricTrainingRev/241209-JWA/blob/fe8940dad3b6c366269a915444d6426bd8b75a59/Study%20Guides/Test%20Team%20Organization%20Study%20Guide.md)
- [BDD - Gherkin & Cucumber Study Guide](https://github.com/EricTrainingRev/241209-JWA/blob/6df1b82cfd521d7b951d1ec4894605f69e229e57/Study%20Guides/BDD%20-%20Gherkin%20%26%20Cucumber%20Study%20Guide.md)
### Review Topics
- Locator Strategies
- WebDriver methods
## Locator Strategies
Selenium utilizes **By** to determine how to locate elements on a web page. The **By** class has a method associated with each of the following **locator strategies**:

| Locator           | Description                                                                                                                                   |
| ----------------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| class             | Locates elements whose class name contains the search value (compound class names are not permitted)                                          |
| css               | Locates elements matching a CSS selector                                                                                                      |
| id                | Locates elements whose ID attribute matches the search value                                                                                  |
| name              | Locates elements whose NAME attribute matches the search value                                                                                |
| link text         | Locates anchor elements whose visible text matches the search value                                                                           |
| partial link text | Locates anchor elements whose visible text contains the search value. If multiple elements are matching, only the first one will be selected. |
| tag name          | Locates elements whose tag name matches the search value                                                                                      |
| xpath             | Locates elements matching an XPath expression                                                                                                 |
## WebDriver Methods
The WebDriver allows developers to automate typical interactions that users would do within the webpage. This will cover a few challenging implementations in Selenium
### Navigate
Typically, users might just use `driver.get("url")`, but this doesn't have persistent memoization between pages. Interactions more akin to real-world use come from the `navigate()` method:
- `navigate().back()` 
- `navigate().forward()`
- `navigate().refresh()`
- `navigate().to("url")`
### Wait
Latency is an unavoidable part of development, so we must be able to test an application with the expectation that some actions take longer than others. We use **Waits** to tell the driver to suspend further actions until a criterion is met.
- **Implicit waits** are the dumbest, and simply keep calling whatever method, handling a `NotFoundException` until a time constraint is met
	```
	driver.manage().timeouts().wait(Duration.ofSeconds(1));
	```

- **Explicit waits** trigger the wait until a certain `ExpectedConditions` is met. These are the recommended type of wait, and allows for more precise waiting to avoid slowing down the application
	```
	WebElement myElement = new WebDriverWait(driver, Duration.ofSeconds(5))
	.until(ExpectedConditions.elementToBeVisible(By.id("someId")));
	```
- **Fluent waits** give advanced configuration options for determining how long timeouts last, how often to poll the `ExpectedConditions`, and so on.
	```
	new FluentWait<WebDriver>(driver)
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofSecond(2))
	.until(ExpectedConditions(someWebElement));
	```
### Screenshot
Screenshots allow you get get an image of what the web browser looks like when you may need it, instead of doing something like adding a `sleep()`. 
```
	File fileData = ((TakesScreenshot)driver)
	  .getScreenshotAs(OutputType.FILE);
```
### Actions
The **Actions** API is a useful class for creating browser interactions when the typical driver methods aren't sufficient. An example could be moving the cursor on a slider in the webpage. Doing this can only be accomplished through the **Actions** API.
This example shows many of the common methods in the API. The usage of the class involves making a series of method calls chained with the constructor (as shown below).
```
	new Actions(Driver)
	  .moveToLocation(sliderTopLeftX, midFarLeftY)
	  .clickAndHold()
	  .moveByOfffset(xOffset, 0)
	  .release()
	  .perform()
```
