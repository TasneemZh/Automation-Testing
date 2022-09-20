package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import shapes.Circle;

public class ParameterizedTestCircle {

	@ParameterizedTest
	@CsvFileSource(resources = "/circle.csv", numLinesToSkip = 1)
	public void testArea(String radius, String expResultCsv) {
		try {
			Object expResult = expResultCsv.compareToIgnoreCase("error") < 0 ? Double.parseDouble(expResultCsv)
					: expResultCsv;
			Circle circle;
			try {
				circle = new Circle(Double.parseDouble(radius));
			} catch (NumberFormatException | NullPointerException e) {
				circle = new Circle(radius);
			}
			Object result = circle.calculateArea();
			Assertions.assertEquals(expResult, result);
		} catch (Exception e) {
			System.out.println(" Error for other reasons:\n\n " + e + "\n");
			Assertions.assertFalse(true);
		}
	}
}
