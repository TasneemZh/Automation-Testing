package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationBar {
	WebDriver driver;
	WebElement navItemBtn;

	public NavigationBar(WebDriver driver) {
		this.driver = driver;
	}

	public void interacteWithNavItem(String itemText) {
		this.navItemBtn = driver.findElement(By.linkText(itemText));
		this.navItemBtn.click();
	}
}
