package address;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.SignIn;
import pages.home.NavigationBar;

public class TestEditAddress {
	private String term;
	private String expResult;
	private WebDriver webDriver;
	private NavigationBar navBar;

	public TestEditAddress(String term, String expResult, WebDriver webDriver, NavigationBar navBar) {
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
	public void validateUpdatedAddress() {

	}

//	  public void testLogin() {
//		  OpenBrowser browser = new OpenBrowser();
//			WebDriver driver = browser.createDriver("chrome");
//			driver.get("https://demo.guru99.com/V1/index.php");
//			try {
//				Thread.sleep(3000);
//				driver.manage().window().maximize();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//	  }

	@AfterSuite
	public void signOut() {
		NavigationBar navBar = new NavigationBar(this.webDriver);
		navBar.interacteWithNavItem("Sign out");
	}
}