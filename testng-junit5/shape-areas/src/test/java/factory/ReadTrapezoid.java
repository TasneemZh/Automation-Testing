package factory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Factory;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadTrapezoid {
	@Factory
	public Object[] readCsv() {
		FileReader filereader;
		try {
			filereader = new FileReader("./src/test/resources/trapezoid.csv");
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

			List<String[]> allData = csvReader.readAll();

			Object[] arrayOfTests = new Object[allData.size()];
			int cnt = 0;
			TestTrapezoid testTrapezoid;
			Object expResult;
			for (String[] row : allData) {
				expResult = row[3].compareToIgnoreCase("error") < 0 ? Double.parseDouble(row[3]) : row[3];
				try {
					testTrapezoid = new TestTrapezoid(Double.parseDouble(row[0]), Double.parseDouble(row[1]),
							Double.parseDouble(row[2]), expResult);
				} catch (NumberFormatException e) {
					testTrapezoid = new TestTrapezoid(row[0], row[1], row[2], expResult);
				}
				arrayOfTests[cnt] = testTrapezoid;
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
