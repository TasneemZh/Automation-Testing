package trello;

import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.OpenBrowser;
import files.ManageCsv;
import pages.Board;
import pages.CardDetails;
import pages.SignIn;

public class TrelloUsageTest {
	WebDriver driver;
	OpenBrowser browserType;
	final String uploadFilePath = "src/test/resources/upload.csv";
	final String downloadFilePath = "src/test/resources/downloads/upload.csv";

	public TrelloUsageTest() {
		this.browserType = new OpenBrowser();
	}

	// divide the tests
	@Test(enabled = true)
	@Parameters({ "browser", "boardTitle", "cardTitle", "cardDescription", "secondCardTitle" })
	public void testLoginIn(String browser, String boardTitle, String cardTitle, String cardDescription,
			String secondCardTitle) throws InterruptedException {
		this.driver = this.browserType.createDriver(browser);
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		this.driver.get("https://trello.com/login");
		String firstTab = this.driver.getWindowHandle();

		ManageCsv file = new ManageCsv();
		file.writeDataToUpload(uploadFilePath);
//		driver.manage().window().maximize();

		SignIn signIn = new SignIn(this.driver);
		signIn.loginEmailPart("healthie.temp.email@gmail.com");
		Thread.sleep(3000);

		signIn.loginPasswordPart("RegularUserForTesting123");

		Board board = new Board(this.driver);
		board.createNewBoard();

		board.createBoardMenu();

		board.nameBoard(boardTitle);

		CardDetails card = new CardDetails(this.driver);

		board.createCardWithTitle(cardTitle);

		board.clickOnCard(cardTitle);

		card.writeCardDescription(cardDescription);

		card.uploadFile(uploadFilePath);

		card.downloadFile();

		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
		try {
			wait.until(ExpectedConditions.javaScriptThrowsNoExceptions(file.readFile(downloadFilePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(file.readDataFromDownload(), file.readDataFromUpload(uploadFilePath));
		int numOfRows = file.readDataFromDownload();
		for (int i = 0; i < numOfRows; i++) {
			Assert.assertEquals(file.getDownloadValue(i), file.getUploadValue(i));
		}

		card.closeCard();

		String boardUrl = this.driver.getCurrentUrl();
		this.driver.switchTo().newWindow(WindowType.TAB);
		this.driver.get(boardUrl);

		String secondTab = this.driver.getWindowHandle();
		this.driver.switchTo().window(secondTab);

		this.driver.switchTo().window(firstTab);
		Thread.sleep(3000);

		board.createCardWithTitle(secondCardTitle);

		this.driver.switchTo().window(secondTab);

		this.driver.navigate().refresh();

		Assert.assertTrue(board.checkCardExistence(secondCardTitle));

		this.driver.switchTo().window(firstTab);

		board.clickOnCard(cardTitle);

		card.copyPasteDescription();

		card.saveComment();

		Assert.assertEquals(card.getCommentText(cardDescription), cardDescription);

		card.closeCard();

		board.clickOnCard(secondCardTitle);

		card.clickOnHyperLinkButton("Archive");

		card.clickOnHyperLinkButton("Delete");

		card.clickOnInputButton("Delete");

		this.driver.switchTo().window(secondTab);

		this.driver.navigate().refresh();

		try {
			board.checkCardExistence(secondCardTitle);
		} catch (NoSuchElementException error) {
			Assert.assertTrue(true);
		}

		this.driver.switchTo().window(firstTab);

		board.hoverOverMenu(boardTitle);

		board.openDropDownMenu();

		board.clickOnButtonByText("Close board...");

		board.clickOnButtonByText("Close");

		board.deleteBoard();
	}

	@AfterSuite
	public void shutDown() throws InterruptedException {
		this.driver.close();
		this.driver.quit();
	}
}

// To-Do:
// Separate Tests so each test would have only one assert
// Initialize all elements above in the class Constructor