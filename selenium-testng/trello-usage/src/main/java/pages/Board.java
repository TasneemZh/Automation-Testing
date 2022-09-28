package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Board {
	WebDriver driver;

	public Board(WebDriver driver) {
		this.driver = driver;
	}

	public void createNewBoard() {
		WebElement createBtn = this.driver.findElement(By.xpath("//button[@data-test-id='header-create-menu-button']"));
		createBtn.click();
	}

	public void createBoardMenu() {
		WebElement menuBtn = this.driver.findElement(By.xpath("//button[@data-test-id='header-create-board-button']"));
		menuBtn.click();
	}

	public void nameBoard(String boardName) {
		WebElement boardInput = this.driver.findElement(By.xpath("//input[@data-test-id='create-board-title-input']"));
		boardInput.sendKeys(boardName);

		WebElement createBoardBtn = this.driver
				.findElement(By.xpath("//button[@data-test-id='create-board-submit-button']"));
		createBoardBtn.click();
	}

	public boolean checkCardExistence(String title) {
		return this.driver.findElement(By.linkText(title)).isDisplayed();
	}

	public void createCardWithTitle(String title) {
		if (CardDetails.cardsCnt > 0) {
			WebElement addCardBtn = this.driver.findElement(By.linkText("Add a card"));
			addCardBtn.click();
		} else {
			CardDetails.cardsCnt++;
		}
		WebElement titleInput = this.driver
				.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']"));
		titleInput.sendKeys(title);

		WebElement createCardBtn = this.driver.findElement(By.xpath("//input[@value='Add card']"));
		createCardBtn.click();
	}

	public void clickOnCard(String title) {
		List<WebElement> cardsBtn = this.driver.findElements(By.xpath("//span[@class='list-card-title js-card-name']"));
		for (int i = 0; i < cardsBtn.size(); i++) {
			if (cardsBtn.get(i).getText().equals(title)) {
				cardsBtn.get(i).click();
			}
		}
	}

	public void clickOnButtonByText(String btnText) {
		WebElement closeBoardBtn = this.driver.findElement(By.xpath("//button[@title='" + btnText + "']"));
		closeBoardBtn.click();
	}

	public void hoverOverMenu(String boardTitle) {
		WebElement boardMenu = this.driver.findElement(By.linkText(boardTitle));
		Actions action = new Actions(this.driver);

		action.moveToElement(boardMenu).perform();
	}

	public void openDropDownMenu() {
		WebElement dropDownMenuBtn = this.driver.findElement(By.xpath("//button[@aria-label='Board actions menu']"));
		dropDownMenuBtn.click();
	}

	public void deleteBoard() {
		WebElement deleteBtnLink = this.driver
				.findElement(By.xpath("//button[@data-test-id='close-board-delete-board-button']"));
		deleteBtnLink.click();

		WebElement deleteBtnConfirmation = this.driver
				.findElement(By.xpath("//button[@data-test-id='close-board-delete-board-confirm-button']"));
		deleteBtnConfirmation.click();
	}
}
