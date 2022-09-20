package address;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.SignIn;
import pages.home.NavigationBar;

public class TestViewAddress {
	private String term;
	private String expResult;
	private WebDriver webDriver;
	private NavigationBar navBar;

	public TestViewAddress(String term, String expResult, WebDriver webDriver, NavigationBar navBar) {
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
	public void validateViewAddress() {

	}

	@AfterSuite
	public void signOut() {
		NavigationBar navBar = new NavigationBar(this.webDriver);
		navBar.interacteWithNavItem("Sign out");
	}
}