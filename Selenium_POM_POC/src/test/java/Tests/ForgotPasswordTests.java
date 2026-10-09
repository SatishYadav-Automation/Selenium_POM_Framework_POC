package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.PasswordResetPage;
import org.testng.annotations.Test;

public class ForgotPasswordTests extends BaseTest {

    @Test(groups = {"Sanity"})
    public void verifyForgotPassword() {
    	NavigateTo("https://opensource-demo.orangehrmlive.com/");
    	LoginPage login = new LoginPage(getDriver());
		login.AssertPageLoaded();
        login.ForgotPassword();
        new PasswordResetPage(getDriver()).AssertPageLoaded();
    }
}