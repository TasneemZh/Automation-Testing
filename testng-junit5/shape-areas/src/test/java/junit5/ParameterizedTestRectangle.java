package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import shapes.Rectangle;

public class ParameterizedTestRectangle {

	@ParameterizedTest
	@CsvFileSource(resources = "/rectangle.csv", numLinesToSkip = 1)
	public void testArea(String width, String height, String expResultCsv) {
		try {
			Object expResult = expResultCsv.compareToIgnoreCase("error") < 0 ? Double.parseDouble(expResultCsv)
					: expResultCsv;
			Rectangle rectangle;
			try {
				rectangle = new Rectangle(Double.parseDouble(width), Double.parseDouble(height));
			} catch (NumberFormatException | NullPointerException e) {
				rectangle = new Rectangle(width, height);
			}
			Object result = rectangle.calculateArea();
			Assertions.assertEquals(expResult, result);
		} catch (Exception e) {
			System.out.println(" Error for other reasons:\n\n " + e + "\n");
			Assertions.assertFalse(true);
		}
	}
}
