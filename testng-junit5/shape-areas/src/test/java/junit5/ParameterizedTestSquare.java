package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import shapes.Square;

public class ParameterizedTestSquare {

	@ParameterizedTest
	@CsvFileSource(resources = "/square.csv", numLinesToSkip = 1)
	public void testArea(String length, String expResultCsv) {
		try {
			Object expResult = expResultCsv.compareToIgnoreCase("error") < 0 ? Double.parseDouble(expResultCsv)
					: expResultCsv;
			Square square;
			try {
				square = new Square(Double.parseDouble(length));
			} catch (NumberFormatException | NullPointerException e) {
				square = new Square(length);
			}
			Object result = square.calculateArea();
			Assertions.assertEquals(expResult, result);
		} catch (Exception e) {
			System.out.println(" Error for other reasons:\n\n " + e + "\n");
			Assertions.assertFalse(true);
		}
	}
}
