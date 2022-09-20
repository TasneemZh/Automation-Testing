package factory;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import shapes.Triangle;

public class TestTriangle {
	private Object base;
	private Object height;
	private Object expResult;

	public TestTriangle(Object base, Object height, Object expResult) {
		this.base = base;
		this.height = height;
		this.expResult = expResult;
	}

	@Test
	public void testArea() {
		Triangle triangle = new Triangle(this.base, this.height);
		Object result = triangle.calculateArea();
		assertEquals(result, this.expResult);
	}
}
