package address;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import browsers.OpenBrowser;

public class TestAddressWebsite {
	private WebDriver webDriver;
	private OpenBrowser browser;

	public TestAddressWebsite() {
		 this.browser = new OpenBrowser();
	}

	@BeforeSuite(enabled = true)
	public void openTab() {
		this.webDriver = browser.createDriver("chrome");
		this.webDriver.get("http://a.testaddressbook.com/sign_in");
		try {
			Thread.sleep(2000);
			this.webDriver.manage().window().maximize();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddress() throws Exception {
		TestCreateAddress createAddr = new TestCreateAddress(this.webDriver);
		createAddr.testNewAddress("Tasneem", "Zahdeh", "123 David Road", "New York", "Alabama", "123123",
				"United States", "9/22/2022");
	}

	@AfterSuite
	public void closeTab() {
		this.webDriver.close();
	}
}