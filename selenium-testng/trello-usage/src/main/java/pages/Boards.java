package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Boards {
	WebDriver driver;

	public Boards(WebDriver driver) {
		this.driver = driver;
	}

	public void createNewBoard() {
		WebElement createBtn = this.driver.findElement(By.xpath("//button[@data-test-id='header-create-menu-button']"));
		createBtn.click();
	}

	public void createBoardMenu() {
		WebElement menuBtn = this.driver
				.findElement(By.xpath("//button[@data-test-id='header-create-board-button']"));
		menuBtn.click();
	}

	public void nameBoard(String boardName) {
		WebElement boardInput = this.driver.findElement(By.xpath("//input[@data-test-id='create-board-title-input']"));
		boardInput.sendKeys(boardName);

		WebElement createBoardBtn = this.driver
				.findElement(By.xpath("//button[@data-test-id='create-board-submit-button']"));
		createBoardBtn.click();
	}
}
