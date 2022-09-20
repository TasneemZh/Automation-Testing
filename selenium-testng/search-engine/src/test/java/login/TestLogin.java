package login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import browsers.OpenBrowser;

public class TestLogin {
  @Test
  public void testLogin() {
	  OpenBrowser browser = new OpenBrowser();
		WebDriver driver = browser.createDriver("chrome");
		driver.get("https://demo.guru99.com/V1/index.php");
		try {
			Thread.sleep(3000);
			driver.manage().window().maximize();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }
}
