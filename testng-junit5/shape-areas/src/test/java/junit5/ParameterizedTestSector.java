package junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import shapes.Sector;

public class ParameterizedTestSector {

	@ParameterizedTest
	@CsvFileSource(resources = "/sector.csv", numLinesToSkip = 1)
	public void testArea(String radius, String angel, String expResultCsv) {
		try {
			Object expResult = expResultCsv.compareToIgnoreCase("error") < 0 ? Double.parseDouble(expResultCsv)
					: expResultCsv;
			Sector sector;
			try {
				sector = new Sector(Double.parseDouble(radius), Double.parseDouble(angel));
			} catch (NumberFormatException | NullPointerException e) {
				sector = new Sector(radius, angel);
			}
			Object result = sector.calculateArea();
			Assertions.assertEquals(expResult, result);
		} catch (Exception e) {
			System.out.println(" Error for other reasons:\n\n " + e + "\n");
			Assertions.assertFalse(true);
		}
	}
}
