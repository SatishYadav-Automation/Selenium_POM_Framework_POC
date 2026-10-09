package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"Login"})
public class LoginTests extends BaseTest
{
	LoginPage login;
	
	@BeforeMethod(alwaysRun = true)
	public void OpenLoginPage()
	{
		NavigateTo("https://opensource-demo.orangehrmlive.com/");
		login = new LoginPage(getDriver());
		login.AssertPageLoaded();
	}

    @Test(groups = {"Smoke", "Positive"}, priority = 1)
    public void ValidLogin() 
    { 	
    	login.EnterUsername("Admin");
    	login.AssertUsername("Admin");
    	login.EnterPassword("admin123");
    	login.AssertPassword("admin123");
    	
        login.Login();
        new HomePage(getDriver()).AssertPageLoaded("Dashboard");
    }

    @Test(dataProvider = "invalidLoginData", groups = {"Regression", "Negative"})
    public void invalidLogin(String username, String password) {
        login.EnterUsername(username);
        login.EnterPassword(password);
        login.Login();
        login.AssertErrorMessage("Invalid credentials");
    }
    
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
            {"NotAdmin", "admin"},
            {"Admin", "wrongPass"},
            {}
        };
    }
}