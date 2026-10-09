package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.time.Duration;

public class PasswordResetPage {

	private WebDriver driver;
	String title= "Reset Password";
	private By headerTitleLocator = By.className("orangehrm-forgot-password-title");

	public PasswordResetPage(WebDriver driver) 	
	{
		this.driver = driver;
	}

	public void AssertPageLoaded()
	{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("requestPasswordResetCode"));

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitleLocator));
        String actualTitle = header.getText();
        Assert.assertEquals(actualTitle,title, 
        	    "Password Reset Page did not load! Expected header: " + title + ", but got '" + actualTitle + "'");
    }
}