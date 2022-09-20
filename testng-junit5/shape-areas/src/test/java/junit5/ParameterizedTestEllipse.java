package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import shapes.Ellipse;

public class ParameterizedTestEllipse {

	@ParameterizedTest
	@CsvFileSource(resources = "/ellipse.csv", numLinesToSkip = 1)
	public void testArea(String xAxis, String yAxis, String expResultCsv) {
		try {
			Object expResult = expResultCsv.compareToIgnoreCase("error") < 0 ? Double.parseDouble(expResultCsv)
					: expResultCsv;
			Ellipse ellipse;
			try {
				ellipse = new Ellipse(Double.parseDouble(xAxis), Double.parseDouble(yAxis));
			} catch (NumberFormatException | NullPointerException e) {
				ellipse = new Ellipse(xAxis, yAxis);
			}
			Object result = ellipse.calculateArea();
			Assertions.assertEquals(expResult, result);
		} catch (Exception e) {
			System.out.println(" Error for other reasons:\n\n " + e + "\n");
			Assertions.assertFalse(true);
		}
	}
}
