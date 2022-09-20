package shapes;

public class Ellipse {
	private Object xAxis;
	private Object yAxis;

	public Ellipse(Object xAxis, Object yAxis) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public Object calculateArea() {
		Object result = this.xAxis instanceof Double && this.yAxis instanceof Double && (double) this.xAxis > 0
				&& (double) this.yAxis > 0 ? Math.PI * (double) this.xAxis * (double) this.yAxis * 100.0 : "error";

		if (result instanceof Double && (double) result != Double.POSITIVE_INFINITY) {
			return Math.round((double) result) / 100.0;
		}
		return result;
	}
}
