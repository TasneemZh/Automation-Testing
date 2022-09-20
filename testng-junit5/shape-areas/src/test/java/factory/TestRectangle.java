package factory;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import shapes.Rectangle;

public class TestRectangle {
	private Object width;
	private Object height;
	private Object expResult;

	public TestRectangle(Object width, Object height, Object expResult) {
		this.width = width;
		this.height = height;
		this.expResult = expResult;
	}

	@Test
	public void testArea() {
		Rectangle rectangle = new Rectangle(this.width, this.height);
		Object result = rectangle.calculateArea();
		assertEquals(result, this.expResult);
	}
}
