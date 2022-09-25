package trello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.OpenBrowser;
import files.ManageCsv;
import pages.Boards;
import pages.Card;
import pages.SignIn;

public class TrelloUsageTest {
	WebDriver webDriver;
	OpenBrowser browserType;
	final String uploadFilePath = "src/test/resources/upload.csv";
	final String downloadFilePath = "src/test/resources/downloads/upload.csv";

	public TrelloUsageTest() {
		this.browserType = new OpenBrowser();
	}

	@Test(enabled = true)
	@Parameters({ "browser", "boardTitle", "cardTitle", "cardDescription", "secondCardTitle" })
	public void testLoginIn(String browser, String boardTitle, String cardTitle, String cardDescription,
			String secondCardTitle) throws InterruptedException {
		this.webDriver = this.browserType.createDriver(browser);
		this.webDriver.get("https://trello.com/login");
		String firstTab = this.webDriver.getWindowHandle();
		Thread.sleep(5000);

		ManageCsv file = new ManageCsv();
		file.writeDataToUpload(uploadFilePath);
//		webDriver.manage().window().maximize();

		SignIn signIn = new SignIn(this.webDriver);
		signIn.loginEmailPart("tasneemisfree2@gmail.com");
		Thread.sleep(5000);

		signIn.loginPasswordPart("Palestine_2008");
		Thread.sleep(5000);

		Boards boards = new Boards(this.webDriver);
		boards.createNewBoard();
		Thread.sleep(5000);

		boards.createBoardMenu();
		Thread.sleep(5000);

		boards.nameBoard(boardTitle);
		Thread.sleep(5000);

		Card card = new Card(this.webDriver);
		Thread.sleep(5000);

		card.createCardWithTitle(cardTitle);
		Thread.sleep(3000);

		card.clickOnCard();
		Thread.sleep(5000);

		card.writeCardDescription(cardDescription);
		Thread.sleep(5000);

		card.uploadFile(uploadFilePath);
		Thread.sleep(15000);

		card.downloadFile();
		Thread.sleep(15000);

		Assert.assertEquals(file.readDataFromDownload(uploadFilePath), file.readDataFromUpload(uploadFilePath));
		int numOfRows = file.readDataFromDownload(uploadFilePath);
		for (int i = 0; i < numOfRows; i++) {
			Assert.assertEquals(file.getDownloadValue(i), file.getUploadValue(i));
		}

		card.closeCard();
		Thread.sleep(5000);

		String boardUrl = this.webDriver.getCurrentUrl();
		this.webDriver.switchTo().newWindow(WindowType.TAB);
		this.webDriver.get(boardUrl);
		Thread.sleep(5000);

		String secondTab = this.webDriver.getWindowHandle();
		this.webDriver.switchTo().window(secondTab);
		Thread.sleep(5000);

		this.webDriver.switchTo().window(firstTab);
		Thread.sleep(5000);

		card.createCardWithTitle(secondCardTitle);
		Thread.sleep(5000);

		this.webDriver.switchTo().window(secondTab);
		Thread.sleep(5000);

		this.webDriver.navigate().refresh();
		Thread.sleep(5000);
		
		Assert.assertTrue(card.checkCardExistence(secondCardTitle));
	}

	@AfterSuite
	public void shutDown() throws InterruptedException {
		Thread.sleep(5000);
		this.webDriver.close();
		Thread.sleep(5000);
		this.webDriver.quit();
	}
}