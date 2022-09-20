package shapes;

public class Square {
	private Object length;

	public Square(Object length) {
		this.length = length;
	}

	public Object calculateArea() {
		Object result = this.length instanceof Double && (double) this.length > 0
				? Math.pow((double) this.length, 2) * 100.0
				: "error";

		if (result instanceof Double && (double) result != Double.POSITIVE_INFINITY) {
			return Math.round((double) result) / 100.0;
		}
		return result;
	}
}
