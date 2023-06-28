package telran.shapes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public class Canvas implements Shape, Iterable<Shape> {
	private Shape[] shapes = new Shape[0];
	
	
	private class CanvasIterator implements Iterator<Shape> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Shape next() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void remove() {
			
		}
	}

	@Override
	public int perimeter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int square() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Shape> iterator() {
		return new CanvasIterator();
	}
	
	public void addShape(Shape shape) {
		shapes = Arrays.copyOf(shapes, shapes.length + 1);
		shapes[shapes.length - 1] = shape;
	}
	
	public boolean removeIf(Predicate predicate) {
		return false;
		
	}
	
	public boolean removeNestedCanvases() {
		return removeIf(shape -> shape instanceof Canvas);
	}

}
