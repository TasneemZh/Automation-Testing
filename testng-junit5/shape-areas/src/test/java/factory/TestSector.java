package factory;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import shapes.Sector;

public class TestSector {
	private Object radius;
	private Object angel;
	private Object expResult;

	public TestSector(Object radius, Object angel, Object expResult) {
		this.radius = radius;
		this.angel = angel;
		this.expResult = expResult;
	}

	@Test
	public void testArea() {
		Sector sector = new Sector(this.radius, this.angel);
		Object result = sector.calculateArea();
		assertEquals(result, this.expResult);
	}
}
