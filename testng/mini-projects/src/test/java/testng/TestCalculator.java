package testng;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestCalculator {
	@Parameters({ "input1", "input2" })
	@Test
	public void testCalculateSum(String input1, String input2) {
		int num1 = Integer.parseInt(input1);
		int num2 = Integer.parseInt(input2);
		int actual = Calculator.calculateSum(num1, num2);
		assertEquals(actual, Integer.sum(num1, num2));
	}
}
