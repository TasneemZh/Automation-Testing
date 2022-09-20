package factory;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import shapes.Square;

public class TestSquare {
	private Object length;
	private Object expResult;

	public TestSquare(Object length, Object expResult) {
		this.length = length;
		this.expResult = expResult;
	}

	@Test
	public void testArea() {
		Square square = new Square(this.length);
		Object result = square.calculateArea();
		assertEquals(result, this.expResult);
	}
}
