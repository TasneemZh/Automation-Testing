package factory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Factory;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadEllipse {
	@Factory
	public Object[] readCsv() {
		FileReader filereader;
		try {
			filereader = new FileReader("./src/test/resources/ellipse.csv");
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

			List<String[]> allData = csvReader.readAll();

			Object[] arrayOfTests = new Object[allData.size()];
			int cnt = 0;
			TestEllipse testEllipse;
			Object expResult;
			for (String[] row : allData) {
				expResult = row[2].compareToIgnoreCase("error") < 0 ? Double.parseDouble(row[2]) : row[2];
				try {
					testEllipse = new TestEllipse(Double.parseDouble(row[0]), Double.parseDouble(row[1]), expResult);
				} catch (NumberFormatException e) {
					testEllipse = new TestEllipse(row[0], row[1], expResult);
				}
				arrayOfTests[cnt] = testEllipse;
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
