package telran.shapes.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.shapes.Canvas;
import telran.shapes.Rectangle;
import telran.shapes.Square;
import telran.shapes.Shape;

class CanvasTest {
	Canvas src;
	Canvas inner;
	
	@BeforeEach
	void init() {
		src = new Canvas();
		inner = new Canvas();
		
		inner.addShape(new Square(1));
		inner.addShape(new Rectangle(1, 2));
		
		src.addShape(new Square(1));
		src.addShape(new Rectangle(1, 2));
		src.addShape(inner);
	}
	
	@Test
	void addShapeTest() {
		src = new Canvas();
		
		assertEquals(0, src.toArray().length);
		src.addShape(new Square(1));
		assertEquals(1, src.toArray().length);
		src.addShape(new Rectangle(1, 2));
		assertEquals(2, src.toArray().length);
		
		var arr = src.toArray();
		assertEquals(new Square(1), arr[0]);
		assertEquals(new Rectangle(1, 2), arr[1]);
	}

	@Test
	void lengthTest() {
		assertEquals(3, src.length());
	}
	
	@Test
	void toArrayTest() {
		Shape[] exp = {new Square(1), new Rectangle(1, 2), inner};
		assertArrayEquals(exp, src.toArray());
	}
	
	@Test
	void iteratorTest() {
		Iterator<Shape> it = src.iterator();
		
		assertEquals(new Square(1), it.next());
		assertEquals(new Rectangle(1, 2), it.next());
		assertEquals(inner, it.next());

		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}
	
	@Test
	void removeIf() {
		Shape[] exp = {new Square(1), new Rectangle(1, 2)};
		assertTrue(src.removeIf(e -> e instanceof Canvas));
		assertFalse(src.removeIf(e -> e instanceof Canvas));
		assertArrayEquals(exp, src.toArray());
	}
	
	@Test
	void removeIfAll() {
		Shape[] expected = {};
		assertTrue(src.removeIf(e -> true));
		assertEquals(0,  src.length());
		assertArrayEquals(expected, src.toArray());
	}
	
	@Test
	void removeIteratorTest() {
		Shape[] exp = {new Rectangle(1, 2), inner};
		Iterator<Shape> it = src.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		it.next();
		it.remove();
		
		assertArrayEquals(exp, src.toArray());
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
	}
	
	@Test
	void perimeterTest() {
		int exp = 20;
		assertEquals(exp, src.perimeter());
	}
	
	@Test
	void squareTest() {
		int exp = 6;
		assertEquals(exp, src.square());
	}
	

}
