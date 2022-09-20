package browsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestSearchTerm {
	private String term;
	private String expResult;
	private WebDriver webDriver;
	
	public TestSearchTerm(String term, String expResult, WebDriver webDriver) {
		this.term = term;
		this.expResult = expResult;
		this.webDriver = webDriver;
	}
	
	@BeforeSuite(enabled=true)
	public void openTab() {
		this.webDriver.get("https://www.bing.com/");
	}
	
	@Test
	public void CheckFirstTitle() {
		WebElement element = webDriver.findElement(By.id("sb_form_q"));
		element.clear();
		element.sendKeys(this.term);
		element = webDriver.findElement(By.xpath("//*[@id=\"search_icon\"]"));
		element.click();
		element = webDriver.findElement(By.xpath("//*[@id=\"b_results\"]/li[1]/div[1]/h2/a"));
		String title = element.getAttribute("innerHTML");
		Assert.assertEquals(title, this.expResult);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void closeTab() {
		webDriver.close();	
	}
}
