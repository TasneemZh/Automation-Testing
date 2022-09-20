package textValidation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import browsers.OpenBrowser;
import login.TestLogin;

public class TestContent {
	WebDriver driver;
	String hyperlink;

	public void removeAd(WebDriver driver) {
		try {
			WebElement frame1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
			driver.switchTo().frame(frame1);
			WebElement frame2 = driver.findElement(By.id("ad_iframe"));
			driver.switchTo().frame(frame2);
			driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
			driver.switchTo().defaultContent();
		} catch (Exception e) {

		}
	}

	@BeforeSuite
	public void login() throws InterruptedException {
		OpenBrowser browser = new OpenBrowser();
		this.driver = browser.createDriver("chrome");
		TestLogin login = new TestLogin();
		login.testLogin(this.driver);
	}

	@Test(dataProvider = "getBtnText")
	public void testTexts(String btnText) throws InterruptedException {
		if (!btnText.equals("Log out")) {
			System.out.println(btnText);
			WebElement button = this.driver.findElement(By.linkText(btnText));
			this.hyperlink = button.getAttribute("href");
			button.click();

			Thread.sleep(3000);
			if (driver.getCurrentUrl().contains("#")) {
				this.removeAd(this.driver);
				Thread.sleep(3000);
			}

			Assert.assertTrue(this.driver.getTitle().contains(btnText) && this.driver.getCurrentUrl().contains(hyperlink));
		}
	}

	@DataProvider
	public Object[][] getBtnText() {
		List<WebElement> buttons = this.driver.findElements(By.xpath("//div[3]/div/ul/li/a"));
		Object[][] btnTexts = new Object[buttons.size()][1];
		int i = 0;
		for (WebElement btn : buttons) {
			btnTexts[i][0] = btn.getText();
			i++;
		}
		return btnTexts;
	}

	@AfterSuite
	public void closeBrowser() {
		this.driver.close();
	}
}
