package pages.address;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddressForm {
	WebDriver driver;
	WebElement firstName;
	WebElement lastName;
	WebElement address1;
	WebElement city;
	Select state;
	WebElement zipCode;
	WebElement usCountryRadio;
	WebElement usCountryLabel;
	WebElement caCountryRadio;
	WebElement caCountryLabel;
	WebElement birthday;
	WebElement createAddressBtn;

	public AddressForm(WebDriver driver) {
		this.driver = driver;
	}

	public void createAddressForm() {
		this.firstName = this.driver.findElement(By.id("address_first_name"));
		this.lastName = this.driver.findElement(By.id("address_last_name"));
		this.address1 = this.driver.findElement(By.id("address_street_address"));
		this.city = this.driver.findElement(By.id("address_city"));
		this.state = new Select(this.driver.findElement(By.id("address_state")));
		this.zipCode = this.driver.findElement(By.id("address_zip_code"));
		this.usCountryRadio = this.driver.findElement(By.id("address_country_us"));
		this.usCountryLabel = this.driver.findElement(By.xpath("//*[@id='new_address']/div[8]/label[1]"));
		this.caCountryRadio = this.driver.findElement(By.id("address_country_canada"));
		this.caCountryLabel = this.driver.findElement(By.xpath("//*[@id='new_address']/div[8]/label[2]"));
		// this.birthday = this.driver.findElement(By.id("address_birthday"));
		this.createAddressBtn = this.driver.findElement(By.xpath("//input[@value='Create Address']"));
	}

	public void fillForm(String firstName, String lastName, String address1, String city, String state, String zipCode,
			String country) {
		this.firstName.sendKeys(firstName);
		this.lastName.sendKeys(lastName);
		this.address1.sendKeys(address1);
		this.city.sendKeys(city);
		this.state.selectByVisibleText(state);
		this.zipCode.sendKeys(zipCode);
		if (this.usCountryLabel.getText().equals(country)) {
			this.usCountryRadio.click();
		} else if (this.caCountryLabel.getText().equals(country)) {
			this.caCountryRadio.click();
		}
//		this.birthday.sendKeys(birthday.replaceAll("[-\\./_]", ""));
	}

	public void submitForm() {
		this.createAddressBtn.click();
	}

	public String getSpecificAddressField(String addrField) {
		WebElement rowData;
		List<WebElement> addressFields = this.driver.findElements(By.xpath("/html/body/div/p"));
		for (int index = 1; index <= addressFields.size(); index++) {
			rowData = this.driver.findElement(By.xpath("/html/body/div/p[" + index + "]/span[2]"));
			String field = rowData.getAttribute("data-test");
			addrField = addrField.replaceAll(" ", "_").toLowerCase();
			if (field.equals(addrField)) {
				return rowData.getText();
			}
		}
		return null;
	}

	public void editAddressField(String addrFieldId, String newValue) {
		WebElement field = this.driver.findElement(By.id(addrFieldId));
		if (addrFieldId.equals("address_country_us") || addrFieldId.equals("address_country_canada")) {
			field.click();
		} else {
			field.clear();
			field.sendKeys(newValue);
		}
	}

	public void submitEditedForm() {
		WebElement editAddressBtn = this.driver.findElement(By.xpath("//input[@value='Update Address']"));
		editAddressBtn.click();
	}
}
