package testng;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.Factory;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class GetRequests {

	@Factory
	public Object[] getData() {
		FileReader filereader;
		try {
			filereader = new FileReader("./src/test/resources/requests.csv");
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			List<String[]> allData = csvReader.readAll();
			
			System.out.println("Number of rows: " + allData.size());

			Object[] arrayOfTests = new Object[allData.size()];
			int cnt = 0;
			for (String[] row : allData) {
				System.out.println(row[0] + " - " + row[1]);
				arrayOfTests[cnt] = new TestGetRequests(row[0], Integer.parseInt(row[1]));
				cnt++;
			}

			return arrayOfTests;
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}
		return null;
	}
}
