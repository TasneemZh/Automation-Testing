package factory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Factory;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadSector {
	@Factory
	public Object[] readCsv() {
		FileReader filereader;
		try {
			filereader = new FileReader("./src/test/resources/sector.csv");
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

			List<String[]> allData = csvReader.readAll();

			Object[] arrayOfTests = new Object[allData.size()];
			int cnt = 0;
			TestSector testSector;
			Object expResult;
			for (String[] row : allData) {
				expResult = row[2].compareToIgnoreCase("error") < 0 ? Double.parseDouble(row[2]) : row[2];
				try {
					testSector = new TestSector(Double.parseDouble(row[0]), Double.parseDouble(row[1]), expResult);
				} catch (NumberFormatException e) {
					testSector = new TestSector(row[0], row[1], expResult);
				}
				arrayOfTests[cnt] = testSector;
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
