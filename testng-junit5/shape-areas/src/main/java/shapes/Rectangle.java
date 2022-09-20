package shapes;

public class Rectangle {
	private Object width;
	private Object height;

	public Rectangle(Object width, Object height) {
		this.width = width;
		this.height = height;
	}

	public Object calculateArea() {		
		Object result = this.height instanceof Double && this.width instanceof Double && (double) this.height > 0
				&& (double) this.width > 0 ? (double)this.height * (double)this.width * 100.0 : "error";

		if (result instanceof Double && (double) result != Double.POSITIVE_INFINITY) {
			return Math.round((double) result) / 100.0;
		}
		return result;
	}
}
