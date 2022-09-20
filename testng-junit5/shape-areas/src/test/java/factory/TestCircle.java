package factory;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import shapes.Circle;

public class TestCircle {
	private Object radius;
	private Object expResult;

	public TestCircle(Object radius, Object expResult) {
		this.radius = radius;
		this.expResult = expResult;
	}

	@Test
	public void testArea() {
		Circle circle = new Circle(this.radius);
		Object result = circle.calculateArea();
		assertEquals(result, this.expResult);
	}
}
