package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.time.Duration;

public class LoginPage {
    
	WebDriver driver;
    WebDriverWait wait;

    private By usernameLocator = By.name("username");
    private By passwordLocator =  By.cssSelector("input[type='password']");
    private By loginButtonLocator = By.className("orangehrm-login-button");
    private By errorMessageLocator = By.cssSelector(".oxd-alert-content-text");
    private By forgotPasswordLocator = By.xpath("//p[normalize-space(.)='Forgot your password?']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void AssertPageLoaded()
	{
        WebElement loginTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Login']")));
        Assert.assertNotNull(loginTitle, "Login Page did not loaded.");
    }
    
    private WebElement GetUsernameField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
    }

    public void ClearUsernameField() {
        GetUsernameField().clear();
    }

    public void EnterUsername(String username) {
    	GetUsernameField().sendKeys(username);
    }

    public void AssertUsername(String expectedUsername) {
        WebElement field = GetUsernameField();

        // Wait up to 2 seconds for the entered value to match expected
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        shortWait.until(driver -> expectedUsername.equals(field.getAttribute("value")));

        String actual = field.getAttribute("value");
        Assert.assertEquals(actual, expectedUsername, "Username not entered correctly!");
    }

    private WebElement GetPasswordField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));
    }

    public void ClearPasswordField() {
    	GetPasswordField().clear();
    }

    public void EnterPassword(String password) {
    	GetPasswordField().sendKeys(password);
    }

    public void AssertPassword(String expectedPassword) {
        WebElement field = GetPasswordField();

        // Wait up to 2 seconds for the entered value to match expected
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        shortWait.until(driver -> expectedPassword.equals(field.getAttribute("value")));

        String actual = field.getAttribute("value");
        Assert.assertEquals(actual, expectedPassword, "Password not entered correctly!");
    }
    
    public void Login(){
        driver.findElement(loginButtonLocator).click();
    }

    private String GetErrorMessage() 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorElement.getText();
    }

    public void AssertErrorMessage(String expectedMessage) {
        String actualMessage = GetErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Error message did not match!");
    }


    public void ForgotPassword(){
        driver.findElement(forgotPasswordLocator).click();
    }
}
