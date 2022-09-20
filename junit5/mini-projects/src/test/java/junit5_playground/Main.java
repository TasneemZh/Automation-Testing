package junit5_playground;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<String[]> requests = new ArrayList<String[]>();
		ReadCsv file = new ReadCsv();
		requests = file.readCsvFile("./src/test/resources/requests.csv");
		System.out.println("Requests:-");
		for (int i = 0; i < requests.size(); i++) {
			System.out.println(requests.get(i)[0] + " " + requests.get(i)[1]);
		}
	}
}
