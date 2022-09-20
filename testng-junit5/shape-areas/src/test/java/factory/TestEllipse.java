package factory;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import shapes.Ellipse;

public class TestEllipse {
	private Object xAxis;
	private Object yAxis;
	private Object expResult;

	public TestEllipse(Object xAxis, Object yAxis, Object expResult) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.expResult = expResult;
	}

	@Test
	public void testArea() {
		Ellipse ellipse = new Ellipse(this.xAxis, this.yAxis);
		Object result = ellipse.calculateArea();
		assertEquals(result, this.expResult);
	}
}
