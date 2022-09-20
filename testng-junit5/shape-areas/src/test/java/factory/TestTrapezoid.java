package factory;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import shapes.Trapezoid;

public class TestTrapezoid {
	private Object baseUp;
	private Object baseDown;
	private Object height;
	private Object expResult;

	public TestTrapezoid(Object baseUp, Object baseDown, Object height, Object expResult) {
		this.baseUp = baseUp;
		this.baseDown = baseDown;
		this.height = height;
		this.expResult = expResult;
	}

	@Test
	public void testArea() {
		Trapezoid trapezoid = new Trapezoid(this.baseUp, this.baseDown, this.height);
		Object result = trapezoid.calculateArea();
		assertEquals(result, this.expResult);
	}
}
