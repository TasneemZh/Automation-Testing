package pages.address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressTable {
	WebDriver driver;
	List<WebElement> rows;
	WebElement row;

	public AddressTable(WebDriver driver) {
		this.driver = driver;
	}

	public int getNumberOfRows() {
		this.rows = driver.findElements(By.xpath("//table/tbody/tr"));
		return this.rows.size();
	}

	public Map<String, String> getRowData(int rowIndex) {
		Map<String, String> map = new HashMap<String, String>();
		List<WebElement> tableHeader = this.driver.findElements(By.xpath("//table/thead/tr/th"));
		List<WebElement> rowData = this.driver.findElements(By.xpath("//table/tbody/tr[" + (rowIndex + 1) + "]/td"));
		for (int index = 0; index < 4; index++) {
			map.put(tableHeader.get(index).getText(), rowData.get(index).getText());
		}
		return map;
	}

	public void interacteWithAddressActions(String itemText, int rowIndex) {
		List<WebElement> addressesView = driver.findElements(By.linkText(itemText));
		addressesView.get(rowIndex).click();
	}
}
