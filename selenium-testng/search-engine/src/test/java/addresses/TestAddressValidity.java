package addresses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestAddressValidity {
	private String name;
	private String city;
	private WebDriver webDriver;
	private List<String[]> validUrls;
	private List<String[]> exportToCsv;
	
	public TestAddressValidity(String name, String city, WebDriver webDriver, List<String[]>exportToCsv) {
		this.name = name;
		this.city = city;
		this.webDriver = webDriver;
		this.exportToCsv = exportToCsv; 
	}
	
	@BeforeSuite(enabled=true)
	public void openTab() {
		this.webDriver.get("https://www.bing.com/");
	}
	
	@Test
	public void CheckAddressValidity() {
		WebElement element = webDriver.findElement(By.id("sb_form_q"));
		element.clear();
		element.sendKeys(this.name + " " + this.city + " expedia");
		element = webDriver.findElement(By.xpath("//*[@id=\"search_icon\"]"));
		element.click();
		// should be find all elements on first page that are valid and insert
		element = webDriver.findElement(By.xpath("//*[@id=\"b_results\"]/li[1]/div[1]/h2/a")); // change it to get the url
		String resultUrl = element.getAttribute("innerHTML"); // change it accordingly
		String actualName = element.getAttribute("innerHTML"); // change it accordingly
		Assert.assertTrue(actualName.contains(this.name));
		String hotelInfo = "Hotel-Information";
		Assert.assertTrue(resultUrl.substring(resultUrl.length() - hotelInfo.length()) == hotelInfo);
		// resultUrl write on csvFile
		validUrls.get(0)[0] = resultUrl;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void closeTab() {
		webDriver.close();
		this.exportToCsv = validUrls;
	}
}
