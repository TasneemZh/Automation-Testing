package browsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BrowserTest {
	@Parameters({ "browser-type" })
	@Test(enabled = false)
	public void testBrowserActions(String browser) {
		OpenBrowser browserType = new OpenBrowser();
		WebDriver webDriver = browserType.createDriver(browser);
		webDriver.get("https://www.google.com/");
		try {
			Thread.sleep(3000);
			webDriver.manage().window().maximize();
			Thread.sleep(3000);
			webDriver.get("https://www.bing.com/");
			Thread.sleep(3000);
			webDriver.navigate().back();
			Thread.sleep(3000);
			webDriver.navigate().forward();
			Thread.sleep(3000);
			webDriver.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Parameters({ "browser-type" })
	@Test(enabled = true)
	public void testSearchEngine(String browser) {
		OpenBrowser browserType = new OpenBrowser();
		WebDriver webDriver = browserType.createDriver(browser);
		webDriver.get("https://www.bing.com/");
		WebElement element = webDriver.findElement(By.id("sb_form_q"));
		element.sendKeys("Selenium");
		element = webDriver.findElement(By.xpath("//*[@id=\"search_icon\"]"));
		element.click();
		element = webDriver.findElement(By.xpath("//*[@id=\"b_results\"]/li[1]/div[1]/h2/a"));
		String title = element.getAttribute("innerHTML");
		Assert.assertEquals(title, "Selenium");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		webDriver.close();
	}
}
