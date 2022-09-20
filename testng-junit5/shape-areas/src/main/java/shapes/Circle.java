package shapes;

public class Circle {
	private Object radius;

	public Circle(Object radius) {
		this.radius = radius;
	}

	public Object calculateArea() {
		Object result = this.radius instanceof Double && (double) this.radius > 0
				? Math.PI * Math.pow((double) this.radius, 2) * 100.0
				: "error";

		if (result instanceof Double && (double) result != Double.POSITIVE_INFINITY) {
			return Math.round((double) result) / 100.0;
		}
		return result;
	}
}
