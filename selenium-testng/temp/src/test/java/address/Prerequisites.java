package address;

import org.openqa.selenium.WebDriver;

import pages.SignIn;
import pages.address.AddressStructure;
import pages.home.NavigationBar;

public class Prerequisites {
	private WebDriver webDriver;
	private NavigationBar navBar;
	private SignIn signIn;
	private AddressStructure structure;

	public Prerequisites(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.navBar = new NavigationBar(this.webDriver);
		this.signIn = new SignIn(this.webDriver);
	}

	public int executePrerequisites() throws InterruptedException {
		this.signIn.signUserIn("tasneemisfree2@gmail.com", "Palestine_2008");
		Thread.sleep(4000);
		this.navBar.interacteWithNavItem("Addresses");
		Thread.sleep(3000);
		this.structure = new AddressStructure(this.webDriver);
		return this.structure.getNumberOfRows();
	}

	public WebDriver getWebDriver() {
		return this.webDriver;
	}

	public NavigationBar getNavBar() {
		return this.navBar;
	}

	public AddressStructure getAddressStructure() {
		return this.structure;
	}

	public void signOut() {
		this.navBar.interacteWithNavItem("Sign out");
	}
}
