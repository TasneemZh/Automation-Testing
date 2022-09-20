package factory;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import shapes.Parallelogram;

public class TestParallelogram {
	private Object base;
	private Object height;
	private Object expResult;

	public TestParallelogram(Object base, Object height, Object expResult) {
		this.base = base;
		this.height = height;
		this.expResult = expResult;
	}

	@Test
	public void testArea() {
		Parallelogram parallelogram = new Parallelogram(this.base, this.height);
		Object result = parallelogram.calculateArea();
		assertEquals(result, this.expResult);
	}
}
