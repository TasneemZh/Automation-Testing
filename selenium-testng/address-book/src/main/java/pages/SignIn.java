package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignIn {
	WebDriver driver;
	WebElement emailField;
	WebElement passwordField;
	WebElement signInBtn;
	
	
	public SignIn(WebDriver driver) {
		this.driver = driver;
		this.emailField = driver.findElement(By.xpath("//input[@id=\"session_email\"]"));
		this.passwordField = driver.findElement(By.xpath("//input[@id=\"session_password\"]"));
		this.signInBtn = driver.findElement(By.xpath("//input[@value=\"Sign in\"]"));
	}
	
	public void signUserIn(String email, String password) {
		this.emailField.sendKeys(email);
		this.passwordField.sendKeys(password);
		this.signInBtn.click();
	}
}
