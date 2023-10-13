package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;


public class LoginStepDefinition {

    private static final Logger LOGGER = Logger.getLogger(LoginStepDefinition.class.getName());
    public WebDriver driver;

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "/Users/chandiniharidas/Downloads/chromedriver-mac-arm64/chromedriver");
        //  driver = new ChromeDriver ();
        System.setProperty ("webdriver.chrome.driver","/Users/chandiniharidas/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver ();
    }

    @Given("User navigates to Practice Website")
    public void userNavigatesToPracticeWebsite() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        LOGGER.info("Website opened");
    }

    @When("User Logs in with User name {string} and password {string}")
    public void userLogsInWithUserNameAndPassword(String userName, String password) {

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(userName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,\"password\")]"))).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Submit')]"))).click();
        LOGGER.info("Logging in with username: " + userName + " and password: " + password);
    }

    @Then("User is redirected to a successful login page")
    public void userIsRedirectedToSuccessfulLoginPage() {
        WebElement successMsg = driver.findElement(By.xpath("//h1[contains(text(),'Successfully')]"));
        Assert.assertTrue("Success message is not displayed", successMsg.isDisplayed());
        driver.findElement(By.xpath("//a[contains(text(),'Log out')]")).click();
        LOGGER.info("Verification 1");
    }

    @Then("Error message is displayed {string}")
    public void errorMessageIsDisplayed(String expectedErrorMessage) {
        WebElement ele = driver.findElement(By.xpath("//div[@id='error']"));
        String actualErrorMessage = ele.getText();
        Assert.assertEquals("Message not as expected ", actualErrorMessage, expectedErrorMessage);
        LOGGER.info("Verification 2");



    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
