package pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Card {
	WebDriver driver;
	int cardIndex;
	Actions actions;
	static int cardsCnt = 0;

	public Card(WebDriver driver) {
		this.driver = driver;
	}

	public void createCardWithTitle(String title) {
		System.out.println("Title: " + title);

		if (Card.cardsCnt > 0) {
			WebElement addCardBtn = this.driver.findElement(By.linkText("Add a card"));
			addCardBtn.click();
		} else {
			Card.cardsCnt++;
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
				this.cardIndex = i;
				cardsBtn.get(i).click();
			}
		}
	}

	public void writeCardDescription(String description) {
		WebElement descriptionInput = this.driver
				.findElement(By.xpath("//textarea[@placeholder='Add a more detailed description…']"));
		descriptionInput.sendKeys(description);

		WebElement saveBtn = this.driver.findElement(By.xpath("//input[@value='Save']"));
		saveBtn.click();
	}

	public void uploadFile(String filePath) {
		File file = new File(filePath);

		WebElement attachmentBtn = this.driver.findElement(By.xpath("//a[@title='Attachment']"));
		attachmentBtn.click();

		WebElement fileSelectionBtn = this.driver.findElement(By.xpath("//input[@type='file']"));
		fileSelectionBtn.sendKeys(file.getAbsolutePath());
	}

	public void downloadFile() {
		WebElement attachmentBtn = this.driver.findElement(By.xpath("//span[@class='icon-sm icon-external-link']/.."));
		attachmentBtn.click();
	}

	public void closeCard() {
		WebElement closeBtn = this.driver.findElement(By.xpath("//a[@aria-label='Close dialog']"));
		closeBtn.click();
	}

	public boolean checkCardExistence(String title) {
		return this.driver.findElement(By.linkText(title)).isDisplayed();
	}

	public String copyPasteDescription() {
		WebElement descriptionBtn = this.driver
				.findElement(By.xpath("//div[@class='description-content js-desc-content']/*/p"));
		WebElement commentSection = this.driver.findElement(By.xpath("//textarea[@placeholder='Write a comment…']"));

		this.actions = new Actions(driver);
		actions.click(descriptionBtn);
		actions.keyDown(Keys.CONTROL);
		actions.sendKeys("a");
		actions.keyUp(Keys.CONTROL);
		actions.build().perform();

		actions.keyDown(Keys.CONTROL);
		actions.sendKeys("c");
		actions.keyUp(Keys.CONTROL);
		actions.build().perform();

		actions.click(commentSection);
		actions.keyDown(Keys.CONTROL);
		actions.sendKeys("v");
		actions.keyUp(Keys.CONTROL);

		actions.build().perform();

		System.out.println(commentSection.getText());
		return commentSection.getText();
	}

	public void saveComment() {
		WebElement saveCommentBtn = this.driver.findElement(By.xpath("//div[@class='comment-box']/*/input"));
		saveCommentBtn.click();
	}

	public String getCommentText(String comment) {
		String commentText = null;
		List<WebElement> comments = this.driver.findElements(By.xpath(
				"//div[@class='js-list-actions mod-card-back']/*/*/*/*/div[@class='current-comment js-friendly-links js-open-card']/p"));
		for (int i = 0; i < comments.size(); i++) {
			commentText = comments.get(i).getText();
			if (comments.get(i).getText().equals(comment)) {
				return commentText;
			}
		}
		return commentText;
	}
}
