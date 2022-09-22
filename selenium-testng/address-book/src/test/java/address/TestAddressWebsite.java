package address;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.OpenBrowser;
import pages.SignIn;
import pages.address.AddressForm;
import pages.address.AddressStructure;
import pages.home.NavigationBar;

public class TestAddressWebsite {
	WebDriver webDriver;
	OpenBrowser browser;
	NavigationBar navBar;
	SignIn signIn;
	AddressStructure structure;

	public TestAddressWebsite() {
		this.browser = new OpenBrowser();
		this.webDriver = browser.createDriver("chrome");
	}

	@BeforeTest(enabled = true)
	public void openBrowserAndSignIn() throws InterruptedException {
		this.webDriver.get("http://a.testaddressbook.com/sign_in");
		Thread.sleep(3000);
		this.webDriver.manage().window().maximize();
		Thread.sleep(3000);
		this.signIn = new SignIn(this.webDriver);
		this.signIn.signUserIn("tasneemisfree2@gmail.com", "Palestine_2008");
		Thread.sleep(3000);
		this.navBar = new NavigationBar(this.webDriver);
		this.navBar.interacteWithNavItem("Addresses");
		Thread.sleep(3000);
		this.structure = new AddressStructure(this.webDriver);
	}

	@Test
	@Parameters({ "firstName", "lastName", "address1", "city", "state", "zipCode", "country" })
	public void testCreateAddress(String firstName, String lastName, String address1, String city, String state,
			String zipCode, String country) throws InterruptedException {
		int expResult = this.structure.getNumberOfRows() + 1;
		this.navBar.interacteWithNavItem("New Address");
		Thread.sleep(3000);

		AddressForm address = new AddressForm(this.webDriver);
		address.fillForm(firstName, lastName, address1, city, state, zipCode, country);
		Thread.sleep(3000);

		address.submitForm();
		Thread.sleep(3000);

		Map<String, String> map = new HashMap<String, String>();
		map.put("first_name", firstName);
		map.put("last_name", lastName);
		map.put("street_address", address1);
		map.put("city", city);
		map.put("state", state.substring(0, 1).toUpperCase());
		map.put("zip_code", zipCode);
		map.put("country", country);
//		map.put("birthday", birthday);

		for (Map.Entry<String, String> m : map.entrySet()) {
			String actValue = this.structure.getSpecificAddressField(m.getKey());

			if (m.getKey().equals("state")) {
				Assert.assertEquals(actValue.substring(0, 1), m.getValue());
			} else if (m.getKey().equals("country")) {
				String expCountry = m.getValue().toLowerCase();
				if (expCountry.contains(" ")) {
					String afterSpace = expCountry.substring(expCountry.indexOf(" ") + 1, expCountry.indexOf(" ") + 2);
					System.out.println("afterSpace: " + afterSpace);
					expCountry = expCountry.substring(0, 1) + afterSpace;
				}
				Assert.assertEquals(actValue, expCountry);
			} else {
				Assert.assertEquals(actValue, m.getValue());
			}
		}
		this.navBar.interacteWithNavItem("List");
		Thread.sleep(3000);

		Assert.assertEquals(this.structure.getNumberOfRows(), expResult);
	}

	@AfterTest
	public void signOutAndClose() throws InterruptedException {
		this.navBar.interacteWithNavItem("Sign out");
		Thread.sleep(3000);
		this.webDriver.close();
	}
}