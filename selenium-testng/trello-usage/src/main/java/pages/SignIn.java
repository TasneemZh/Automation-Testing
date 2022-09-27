package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignIn {
	WebDriver driver;
	
	public SignIn(WebDriver driver) {
		this.driver = driver;
	}
	
	public void loginEmailPart(String email) {
		WebElement emailField = this.driver.findElement(By.id("user"));
		emailField.sendKeys(email);
				
		WebElement continueBtn = this.driver.findElement(By.id("login"));
		continueBtn.click();
	}
	
	public void loginPasswordPart(String password) {
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement passwordField = this.driver.findElement(By.id("password"));
		passwordField.sendKeys(password);
		
		WebElement signInBtn = this.driver.findElement(By.id("login-submit"));
		signInBtn.click();
	}
}
