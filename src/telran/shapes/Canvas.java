package telran.shapes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Canvas implements Shape, Iterable<Shape> {
	private Shape[] shapes = new Shape[0];

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Canvas other = (Canvas) obj;
		return Arrays.equals(shapes, other.shapes);
	}

	private class CanvasIterator implements Iterator<Shape> {
		int current = 0;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return current < shapes.length;
		}

		@Override
		public Shape next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			var res = shapes[current];
			current++;
			flNext = true;
			return res;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			flNext = false;
			removeFromArr(--current);
		}
		
		private void removeFromArr(int index) {
			Shape[] temp = new Shape[shapes.length - 1];
			System.arraycopy(shapes, 0, temp, 0, index);
			System.arraycopy(shapes, index + 1, temp, index, temp.length - index);
			shapes = temp;
		}
	}

	@Override
	public int perimeter() {
		int res = 0;
		Iterator<Shape> it = iterator();
		
		while (it.hasNext()) {
			res += it.next().perimeter();
		}
		return res;
	}

	@Override
	public int square() {
		int res = 0;
		Iterator<Shape> it = iterator();
		
		while (it.hasNext()) {
			res += it.next().square();
		}
		return res;
	}

	@Override
	public Iterator<Shape> iterator() {
		return new CanvasIterator();
	}
	
	public void addShape(Shape shape) {
		shapes = Arrays.copyOf(shapes, shapes.length + 1);
		shapes[shapes.length - 1] = shape;
	}
	
	public boolean removeIf(Predicate<Shape> predicate) {
		int oldLength = length();
		Iterator<Shape> it = iterator();
		
		while (it.hasNext()) {
			Shape shape = it.next();
			
			if (predicate.test(shape)) {
				it.remove();
			}
		}
		return oldLength > length();
	}
	
	
	public boolean removeNestedCanvases() {
		return removeIf(shape -> shape instanceof Canvas);
	}
	
	public int length() {
		return shapes.length;
	}
	
	public Shape[] toArray() {
		return shapes;
	}

}
