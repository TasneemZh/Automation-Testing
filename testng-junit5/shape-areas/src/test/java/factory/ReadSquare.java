package factory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Factory;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadSquare {
	@Factory
	public Object[] readCsv() {
		FileReader filereader;
		try {
			filereader = new FileReader("./src/test/resources/square.csv");
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

			List<String[]> allData = csvReader.readAll();

			Object[] arrayOfTests = new Object[allData.size()];
			int cnt = 0;
			TestSquare testSquare;
			Object expResult;
			for (String[] row : allData) {
				expResult = row[1].compareToIgnoreCase("error") < 0 ? Double.parseDouble(row[1]) : row[1];
				try {
					testSquare = new TestSquare(Double.parseDouble(row[0]), expResult);
				} catch (NumberFormatException e) {
					testSquare = new TestSquare(row[0], expResult);
				}
				arrayOfTests[cnt] = testSquare;
				cnt++;
			}
			filereader.close();
			csvReader.close();
			return arrayOfTests;
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}
		return null;
	}
}
