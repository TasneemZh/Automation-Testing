package pages.address;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressStructure {
	WebDriver driver;
	List<WebElement> rows;
	WebElement row;

	public AddressStructure(WebDriver driver) {
		this.driver = driver;
	}

	public int getNumberOfRows() {
		this.rows = driver.findElements(By.xpath("//table/tbody/tr"));
		return this.rows.size();
	}

	public String getSpecificAddressField(String addrField) {
		WebElement rowData;
		List<WebElement> addressFields = driver.findElements(By.xpath("/html/body/div/p"));
		for (int index = 1; index <= addressFields.size(); index++) {
			rowData = driver.findElement(By.xpath("/html/body/div/p[" + index + "]/span[2]"));
			String field = rowData.getAttribute("data-test");
			if (field.equals(addrField)) {
				return rowData.getText();
			}
		}
		return null;
	}
}
