package pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Card {
	WebDriver driver;
	String title;
	int cardIndex;

	public Card(WebDriver driver) {
		this.driver = driver;
	}

	public void createCardWithTitle(String title) {
		System.out.println("Title: " + title);
		this.title = title;
		WebElement titleInput = this.driver
				.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']"));
		titleInput.sendKeys(title);

		WebElement createCardBtn = this.driver.findElement(By.xpath("//input[@value='Add card']"));
		createCardBtn.click();
	}

	public void clickOnCard() {
		List<WebElement> cardsBtn = this.driver.findElements(By.xpath("//span[@class='list-card-title js-card-name']"));
		for (int i = 0; i < cardsBtn.size(); i++) {
			if (cardsBtn.get(i).getText().equals(this.title)) {
				this.cardIndex = i;
				cardsBtn.get(i).click();
			}
		}
	}

	public void writeCardDescription(String description) {
		WebElement descriptionInput = this.driver.findElement(By.xpath("//textarea[@placeholder='Add a more detailed description…']"));
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
}
