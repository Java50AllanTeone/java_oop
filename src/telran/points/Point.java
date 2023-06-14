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
	
	private double getLengthBetween(Point start) {
		
		return Math.hypot(this.x - start.x, this.y - start.y);
	}
	
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
	

	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		return this.x == other.x && this.y == other.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	

	

}
