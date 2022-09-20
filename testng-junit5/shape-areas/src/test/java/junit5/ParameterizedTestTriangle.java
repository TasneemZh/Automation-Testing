package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import shapes.Triangle;

public class ParameterizedTestTriangle {

	@ParameterizedTest
	@CsvFileSource(resources = "/triangle.csv", numLinesToSkip = 1)
	public void testArea(String base, String height, String expResultCsv) {
		try {
			Object expResult = expResultCsv.compareToIgnoreCase("error") < 0 ? Double.parseDouble(expResultCsv)
					: expResultCsv;
			Triangle triangle;
			try {
				triangle = new Triangle(Double.parseDouble(base), Double.parseDouble(height));
			} catch (NumberFormatException | NullPointerException e) {
				triangle = new Triangle(base, height);
			}
			Object result = triangle.calculateArea();
			Assertions.assertEquals(expResult, result);
		} catch (Exception e) {
			System.out.println(" Error for other reasons:\n\n " + e + "\n");
			Assertions.assertFalse(true);
		}
	}
}
