package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import shapes.Parallelogram;

public class ParameterizedTestParallelogram {

	@ParameterizedTest
	@CsvFileSource(resources = "/parallelogram.csv", numLinesToSkip = 1)
	public void testArea(String base, String height, String expResultCsv) {
		try {
			Object expResult = expResultCsv.compareToIgnoreCase("error") < 0 ? Double.parseDouble(expResultCsv)
					: expResultCsv;
			Parallelogram parallelogram;
			try {
				parallelogram = new Parallelogram(Double.parseDouble(base), Double.parseDouble(height));
			} catch (NumberFormatException | NullPointerException e) {
				parallelogram = new Parallelogram(base, height);
			}
			Object result = parallelogram.calculateArea();
			Assertions.assertEquals(expResult, result);
		} catch (Exception e) {
			System.out.println(" Error for other reasons:\n\n " + e + "\n");
			Assertions.assertFalse(true);
		}
	}
}
