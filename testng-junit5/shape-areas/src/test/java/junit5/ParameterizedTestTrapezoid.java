package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import shapes.Trapezoid;

public class ParameterizedTestTrapezoid {

	@ParameterizedTest
	@CsvFileSource(resources = "/trapezoid.csv", numLinesToSkip = 1)
	public void testArea(String baseUp, String baseDown, String height, String expResultCsv) {
		try {
			Object expResult = expResultCsv.compareToIgnoreCase("error") < 0 ? Double.parseDouble(expResultCsv)
					: expResultCsv;
			Trapezoid trapezoid;
			try {
				trapezoid = new Trapezoid(Double.parseDouble(baseUp), Double.parseDouble(baseDown),
						Double.parseDouble(height));
			} catch (NumberFormatException | NullPointerException e) {
				trapezoid = new Trapezoid(baseUp, baseDown, height);
			}
			Object result = trapezoid.calculateArea();
			Assertions.assertEquals(expResult, result);
		} catch (Exception e) {
			System.out.println(" Error for other reasons:\n\n " + e + "\n");
			Assertions.assertFalse(true);
		}
	}
}
