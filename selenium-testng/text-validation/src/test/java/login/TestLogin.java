package login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import browsers.OpenBrowser;
import pages.Login;

public class TestLogin {
  @Test
  public void testLogin(WebDriver driver) {
		driver.get("https://demo.guru99.com/V1/index.php");
		try {
			Thread.sleep(3000);
			driver.manage().window().maximize();
			Thread.sleep(3000);	
			Login login = new Login(driver);
			login.processLogin("mngr438269", "zegYzyh");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }
}
