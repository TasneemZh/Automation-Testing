package shapes;

public class Trapezoid {
	private Object baseUp;
	private Object baseDown;
	private Object height;

	public Trapezoid(Object baseUp, Object baseDown, Object height) {
		this.baseUp = baseUp;
		this.baseDown = baseDown;
		this.height = height;
	}

	public Object calculateArea() {
		Object result = this.baseUp instanceof Double && this.baseDown instanceof Double
				&& this.height instanceof Double && (double) this.baseUp > 0 && (double) this.baseDown > 0
				&& (double) this.height > 0
						? (((double) this.baseUp + (double) this.baseDown) / 2) * (double) this.height * 100.0
						: "error";

		if (result instanceof Double && (double) result != Double.POSITIVE_INFINITY) {
			return Math.round((double) result) / 100.0;
		}
		return result;
	}
}
