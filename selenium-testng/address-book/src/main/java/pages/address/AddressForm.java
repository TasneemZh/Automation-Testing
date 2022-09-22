package pages.address;

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
}
