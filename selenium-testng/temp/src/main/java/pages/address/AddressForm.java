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
	WebElement usCountry;
	WebElement caCountry;
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
		this.usCountry = this.driver.findElement(By.id("address_country_us"));
		this.caCountry = this.driver.findElement(By.id("address_country_canada"));
		this.birthday = this.driver.findElement(By.id("address_birthday"));
		this.createAddressBtn = this.driver.findElement(By.xpath("//input[@value='Create Address']"));
	}

	public void fillForm(String firstName, String lastName, String address1, String city, String state, String zipCode,
			String country, String birthday) {
		this.firstName.sendKeys(firstName);
		this.lastName.sendKeys(lastName);
		this.address1.sendKeys(address1);
		this.city.sendKeys(city);
		this.state.selectByVisibleText(state);
		this.zipCode.sendKeys(zipCode);
		if (this.usCountry.getText() == country) {
			this.usCountry.click();
		} else if (this.caCountry.getText() == country) {
			this.caCountry.click();
		}
		this.birthday.sendKeys(birthday.replaceAll("[-\\./_]", ""));
	}

	public void submitForm() {
		this.createAddressBtn.click();
	}
}
