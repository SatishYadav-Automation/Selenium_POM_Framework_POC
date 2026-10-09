package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.time.Duration;

public class HomePage {

	private WebDriver driver;
	private By headerTitleLocator = By.className("oxd-topbar-header-breadcrumb-module");

	public HomePage(WebDriver driver) 	
	{
		this.driver = driver;
	}

	public void AssertPageLoaded(String expectedTitle)
	{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerTitleLocator));
        String actualTitle = header.getText();
        Assert.assertEquals(actualTitle, expectedTitle, 
        	    "Page did not load correctly! Expected header: " + expectedTitle + ", but got '" + actualTitle + "'");
    }
}