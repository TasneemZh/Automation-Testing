package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class ManageCsv {
	String uploadFilePath;
	String[][] downloadArr;
	String[][] uploadArr;
	FileReader downloadFile;

	public void writeDataToUpload(String filePath) {
		try {
			System.out.println("filePath: " + filePath);
			this.uploadFilePath = filePath;
			File file = new File(filePath);
			FileWriter uploadFile = new FileWriter(file);

			CSVWriter writer = new CSVWriter(uploadFile);

			String[] header = { "First Name", "Last Name" };
			writer.writeNext(header);

			writer.writeNext(new String[] { "Tasneem", "Zahdeh" });
			writer.writeNext(new String[] { "Sarah", "Winner" });
			System.out.println("In the CSV File!");

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readFile(String downloadFilePath) throws FileNotFoundException {
		this.downloadFile = new FileReader(downloadFilePath);
		return "";
	}

	public int readDataFromDownload() {
		try {
			CSVReader csvReaderDownload = new CSVReader(this.downloadFile);

			List<String[]> downloadRows = csvReaderDownload.readAll();

			int totalRows = downloadRows.size();
			downloadArr = new String[totalRows][1];

			int numOfRows = 0;
			for (String[] row : downloadRows) {
				downloadArr[numOfRows] = row;
				numOfRows++;
			}

			csvReaderDownload.close();
			System.out.println("Download Number of rows: " + totalRows);
			return totalRows;
		} catch (CsvException | IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int readDataFromUpload(String uploadFilePath) {
		try {
			FileReader uploadFile = new FileReader(uploadFilePath);
			CSVReader csvReaderUpload = new CSVReader(uploadFile);

			List<String[]> uploadRows = csvReaderUpload.readAll();

			int totalRows = uploadRows.size();
			uploadArr = new String[totalRows][1];

			int numOfRows = 0;
			for (String[] row : uploadRows) {
				uploadArr[numOfRows] = row;
				numOfRows++;
			}

			csvReaderUpload.close();
			System.out.println("Upload Number of rows: " + totalRows);
			return totalRows;
		} catch (CsvException | IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public String getDownloadValue(int index) {
		System.out.println("Download value: " + downloadArr[index][0]);
		return downloadArr[index][0];
	}

	public String getUploadValue(int index) {
		System.out.println("Upload value: " + uploadArr[index][0]);
		return uploadArr[index][0];
	}
}
