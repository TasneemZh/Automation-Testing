package address;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.SignIn;
import pages.home.NavigationBar;

public class TestDeleteAddress {
	private String term;
	private String expResult;
	private NavigationBar navBar;
	private WebDriver webDriver;

	public TestDeleteAddress(String term, String expResult, WebDriver webDriver, NavigationBar navBar) {
		this.term = term;
		this.expResult = expResult;
		this.webDriver = webDriver;
		this.navBar = navBar;
	}

	@BeforeSuite(enabled = true)
	public void signInAndLocalize() {
		SignIn signIn = new SignIn(this.webDriver);
		signIn.signUserIn("tasneemisfree2@gmail.com", "Palestine_2008");
		this.navBar.interacteWithNavItem("Addresses");
	}

	@Test
	public void validateDeletedAddress() {
	}

	@AfterSuite
	public void signOut() {
		this.navBar.interacteWithNavItem("Sign out");
	}
}