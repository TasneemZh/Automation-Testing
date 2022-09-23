package pages.address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, String> getRowData(int rowIndex) {
		Map<String, String> map = new HashMap<String, String>();
		List<WebElement> tableHeader = driver.findElements(By.xpath("//table/thead/tr/th"));
		List<WebElement> rowData = driver.findElements(By.xpath("//table/tbody/tr[" + rowIndex + "]/td"));
		for (int index = 0; index < rowData.size(); index++) {
			map.put(tableHeader.get(index).getText(), rowData.get(index).getText());
		}
		return map;
	}

	public void interacteWithNavItem(String itemText, int rowIndex) {
		List<WebElement> addressesView = driver.findElements(By.linkText(itemText));
		addressesView.get(rowIndex).click();
	}
}
