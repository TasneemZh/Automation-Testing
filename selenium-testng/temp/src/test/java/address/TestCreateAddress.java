package address;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.address.AddressForm;

public class TestCreateAddress extends Prerequisites {
	private int expResult;

	public TestCreateAddress(WebDriver webDriver) {
		super(webDriver);
	}

//	@BeforeTest(alwaysRun = true)
//	public void getExpectedResult() throws InterruptedException {
//		this.expResult = super.executePrerequisites();
//	}

	@Test
	public void testNewAddress(String firstName, String lastName, String address1, String city, String state,
			String zipCode, String country, String birthday) throws InterruptedException {
		this.expResult = super.executePrerequisites();
		super.getNavBar().interacteWithNavItem("New Address");
		Thread.sleep(3000);
		
		AddressForm address = new AddressForm(super.getWebDriver());
		address.fillForm(firstName, lastName, address1, city, state, zipCode, country, birthday);
		Thread.sleep(3000);

		address.submitForm();
		Thread.sleep(3000);

		Map<String, String> map = new HashMap<String, String>();
		map.put("first_name", firstName);
		map.put("last_name", lastName);
		map.put("street_address", address1);
		map.put("city", city);
		map.put("state", state);
		map.put("zip_code", zipCode);
		map.put("country", country);
		map.put("birthday", birthday);

		for (Map.Entry<String, String> m : map.entrySet()) {
			String actValue = super.getAddressStructure().getSpecificAddressField(m.getKey());
			Assert.assertEquals(actValue, m.getValue());
		}
		super.getNavBar().interacteWithNavItem("List");
		Thread.sleep(3000);

		Assert.assertEquals(super.getAddressStructure().getNumberOfRows() + 1, expResult);
	}

	@AfterTest
	public void signOut() {
		super.signOut();
	}
}