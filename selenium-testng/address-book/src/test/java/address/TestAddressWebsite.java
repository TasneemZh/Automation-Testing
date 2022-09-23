package address;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

	@BeforeMethod
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
	}

	@DataProvider(name = "user-addresses")
	public Object[][] createAddressData() {
		return new Object[][] {
				{ "Tasneem", "Zahdeh", "123 David Road", "New York", "Alabama", "123123", "United States" },
				{ "Layan", "Khater", "45 Thomas Road", "New Jersey", "Arizona", "434343", "Canada" },
				{ "Sally", "Manasrah", "16 Goarge Street", "Chicago", "Indiana", "2332333", "Canada" },
				{ "Razan", "Saeed", "PO BOX 154", "San Francisco", "California", "181818", "United States" } };
	}

	@Test(dataProvider = "user-addresses", enabled = false)
	public void testCreateAddress(String firstName, String lastName, String address1, String city, String state,
			String zipCode, String country) throws InterruptedException {
		Thread.sleep(3000);
		this.structure = new AddressStructure(this.webDriver);
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

	@Test(enabled = false)
	public void testViewAddress() throws InterruptedException {
		Thread.sleep(3000);
		this.structure = new AddressStructure(this.webDriver);
		int numberOfRows = this.structure.getNumberOfRows();
		Map<String, String> map;
		for (int index = 0; index < numberOfRows; index++) {
			map = this.structure.getRowData(index);
			this.structure.interacteWithNavItem("Show", index);
			Thread.sleep(3000);
			for (Map.Entry<String, String> m : map.entrySet()) {
				String actValue = this.structure.getSpecificAddressField(m.getKey());
				Assert.assertEquals(actValue, m.getValue());
			}
			this.navBar.interacteWithNavItem("List");
			Thread.sleep(3000);
		}
	}

	@Test
	public void testEditAddress() throws InterruptedException {
		Thread.sleep(3000);
		this.structure = new AddressStructure(this.webDriver);
		int numberOfRows = this.structure.getNumberOfRows();
		if (numberOfRows < 0) {
			Assert.fail("No addresses to edit!");
		}
		Map<String, String> map = this.structure.getRowData(0);
		this.structure.interacteWithNavItem("Show", 0);
		Thread.sleep(3000);
		for (Map.Entry<String, String> m : map.entrySet()) {
			String actValue = this.structure.getSpecificAddressField(m.getKey());
			Assert.assertEquals(actValue, m.getValue());
		}
		this.navBar.interacteWithNavItem("Edit");
		// continue
	}

	@AfterMethod
	public void signOutAndClose() throws InterruptedException {
		this.navBar.interacteWithNavItem("Sign out");
		Thread.sleep(3000);
		this.webDriver.close();
	}
}