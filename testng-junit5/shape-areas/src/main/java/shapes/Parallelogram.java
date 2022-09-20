package shapes;

public class Parallelogram {
	private Object base;
	private Object height;

	public Parallelogram(Object base, Object height) {
		this.base = base;
		this.height = height;
	}

	public Object calculateArea() {
		Object result = this.base instanceof Double && this.height instanceof Double && (double) this.base > 0
				&& (double) this.height > 0 ? (double) this.base * (double) this.height * 100.0 : "error";

		if (result instanceof Double && (double) result != Double.POSITIVE_INFINITY) {
			return Math.round((double) result) / 100.0;
		}
		return result;
	}
}
