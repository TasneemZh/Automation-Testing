package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	WebDriver driver;
	WebElement usernameField;
	WebElement passwordField;
	WebElement loginBtn;
	
	
	public Login(WebDriver driver) {
		this.driver = driver;
		this.usernameField = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input"));
		this.passwordField = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input"));
		this.loginBtn = driver.findElement(By.name("btnLogin"));
	}
	
	public void processLogin(String username, String password) {
		this.usernameField.sendKeys(username);
		this.passwordField.sendKeys(password);
		this.loginBtn.click();
	}
}
