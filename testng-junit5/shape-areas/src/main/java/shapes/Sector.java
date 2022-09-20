package shapes;

public class Sector {
	private Object radius;
	private Object angel;

	public Sector(Object radius, Object angel) {
		this.radius = radius;
		this.angel = angel;
	}

	public Object calculateArea() {
		Object result = this.radius instanceof Double && this.angel instanceof Double && (double) this.radius > 0
				&& (double) this.angel >= 0 && (double) this.angel <= 360
						? 0.5 * Math.pow((double) this.radius, 2) * (double) this.angel * 100.0
						: "error";

		if (result instanceof Double && (double) result != Double.POSITIVE_INFINITY) {
			return Math.round((double) result) / 100.0;
		}
		return result;
	}
}
