package telran.shapes;


public class Rectangle implements Shape {
	private int height;
	private int width;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		return height == other.height && width == other.width;
	}

	public Rectangle(int height, int width) {
		this.height = height;
		this.width = width;
	}
	
	@Override
	public int perimeter() {
		return 2 * height + 2 * width;
	}
	
	@Override
	public int square() {
		return height * width;
	}

}
