package pages;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CardDetails {
	WebDriver driver;
	Actions actions;
	static int cardsCnt = 0;

	public CardDetails(WebDriver driver) {
		this.driver = driver;
	}

	public void writeCardDescription(String description) {
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		WebElement descriptionInput = wait.until(ExpectedConditions.visibilityOf(
				this.driver.findElement(By.xpath("//textarea[@placeholder='Add a more detailed description…']"))));

		descriptionInput.sendKeys(description);

		clickOnInputButton("Save");
	}

	public void uploadFile(String filePath) {
		File file = new File(filePath);

		clickOnHyperLinkButton("Attachment");

		WebElement fileSelectionBtn = this.driver.findElement(By.xpath("//input[@type='file']"));
		fileSelectionBtn.sendKeys(file.getAbsolutePath());
	}

	public void downloadFile() {
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(this.driver.findElement(By.xpath("//div[@role='alert']"))));

		WebElement attachmentBtn = this.driver.findElement(By.xpath("//span[@class='icon-sm icon-external-link']/.."));
		attachmentBtn.click();
	}

	public void closeCard() {
		WebElement closeBtn = this.driver.findElement(By.xpath("//a[@aria-label='Close dialog']"));
		closeBtn.click();
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

	public void clickOnHyperLinkButton(String btnText) {
		WebElement hyperLinkBtn = this.driver.findElement(By.xpath("//a[@title='" + btnText + "']"));
		hyperLinkBtn.click();
	}

	public void clickOnInputButton(String btnText) {
		WebElement inputBtn = this.driver.findElement(By.xpath("//input[@value='" + btnText + "']"));
		inputBtn.click();
	}
}
