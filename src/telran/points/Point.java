package telran.points;

import java.util.Objects;

public class Point implements Comparable<Point> {
	private final int x;
	private final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		Point start = new Point(0, 0);
		double length1 = this.getLengthBetween(start);
		double length2 = o.getLengthBetween(start);
		return Double.compare(length1, length2);
	}
	
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	private double getLengthBetween(Point start) {
		
		return Math.hypot(this.x - start.x, this.y - start.y);
	}
	

}
