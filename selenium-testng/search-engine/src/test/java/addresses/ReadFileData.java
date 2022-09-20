// Incomplete Code











//package addresses;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.List;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.Factory;
//
//import com.opencsv.CSVReader;
//import com.opencsv.CSVReaderBuilder;
//import com.opencsv.exceptions.CsvException;
//
//import browsers.OpenBrowser;
//
//public class ReadFileData {
//	@Factory
//	public Object[] readCsv() {
//		FileReader filereader;
//		try {
//			filereader = new FileReader("./src/test/resources/addresses.csv");
//			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
//
//			List<String[]> allData = csvReader.readAll();
//			List<String[]> withAdditionalData = csvReader.readAll(); // try: = allData;
//			List<String[]> exportToCsv;
//
//			Object[] arrayOfTests = new Object[allData.size()];
//			int cnt = 0;
//			TestAddressValidity addressUrl;
//			OpenBrowser browserType = new OpenBrowser();
//			WebDriver webDriver = browserType.createDriver("chrome");
//						
//			for (String[] row : allData) {
//				addressUrl = new TestAddressValidity(row[1], row[2], webDriver); // check if addressUrl is String
//				arrayOfTests[cnt] = testAddress;
//				cnt++;
//			}
//			filereader.close();
//			csvReader.close();
//			return arrayOfTests;
//		} catch (IOException | CsvException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//}
