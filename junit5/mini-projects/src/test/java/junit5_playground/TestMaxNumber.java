package junit5_playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TestMaxNumber {
	@ParameterizedTest
	@CsvFileSource(resources = "/max_number.csv", numLinesToSkip = 1)
	public void testMaxNumber(String num1, String num2, String expected) {
		int maxNum = Math.max(Integer.parseInt(num1), Integer.parseInt(num2));
		Assertions.assertEquals(Integer.parseInt(expected), maxNum);
	}
}