package junit5_playground;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class ReadCsv {

	public ArrayList<String[]> readCsvFile(String filePath) {
		ArrayList<String[]> array = new ArrayList<String[]>();
		CSVReader reader = null;

		try {
			reader = new CSVReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				array.add(new String[] { nextLine[0], nextLine[1]});
			}
			reader.close();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return array;
	}

}
