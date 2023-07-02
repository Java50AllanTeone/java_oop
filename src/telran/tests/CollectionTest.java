package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.Collection;

class CollectionTest {
	ArrayList<Integer> src;
	ArrayList<Integer> exp;
	
	@BeforeEach
	void init() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
	}
	
	
	/*	
	boolean addAll(Collection<T> collection);
	
	boolean removeAll(Collection<T> collection);
	boolean isEmpty();
	void clear();
	boolean contains(Object o);
	void ensureCapacity(int capacity);
	boolean retainAll(Collection<T> c);
	public void trimToSize();*/
	
	@Test
	void addTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6}, 6);
		assertTrue(src.add(6));
		assertEquals(exp, src);
		assertEquals(exp.size(), src.size());
		assertEquals(exp.getLength(), src.getLength());
		
		int size = src.size();
		src.add(7);
		assertEquals((size * 3) / 2 + 1, src.getLength());
	}
	
	
	@SuppressWarnings("removal")
	@Test
	void removeTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4}, 6);
		assertTrue(src.remove(new Integer(5)));
		assertFalse(src.remove(new Integer(5)));
		
		assertEquals(exp, src);
		assertEquals(exp.size(), src.size());
		assertEquals(exp.getLength(), src.getLength());
	}
	
	
	@Test
	void toArrayWithTest() {
		var arrSrc = new Integer[3];
		var arrTrg = src.toArray(arrSrc);
		var exp = new Integer[]{1, 2, 3, 4, 5};
		assertNotEquals(Arrays.toString(arrTrg), Arrays.toString(arrSrc));
		assertArrayEquals(exp, arrTrg);
		
		arrSrc = new Integer[10];
		arrTrg = src.toArray(arrSrc);
		assertArrayEquals(arrTrg, arrSrc);
	}
	
	@Test
	void toArrayTest() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 5);
		var arr = src.toArray();
		var exp = new Object[]{1, 2, 3, 4, 5};
		assertArrayEquals(exp, arr);
		
		src.add(6);
		arr = src.toArray();
		exp = new Object[]{1, 2, 3, 4, 5, 6};
		assertArrayEquals(exp, arr);
	}
	
	
	@Test
	void removeIfTest() {
		assertFalse(src.removeIf(e -> false));
		assertTrue(src.removeIf(e -> e == 5));
		
		assertEquals(4, src.size());
		assertEquals(6, src.getLength());
		
		var exp = new Object[]{1, 2, 3, 4};
		assertArrayEquals(exp, src.toArray());
	}
	
	@Test
	void sizeTest() {
		
		assertEquals(5, src.size());
		src.add(6);
		assertEquals(6, src.size());
		src.add(7);
		assertEquals(7, src.size());
		src.remove(0);
		assertEquals(6, src.size());
	}
	
	@Test
	void addAllTest() {
		
		assertEquals(5, src.size());
		src.add(6);
		assertEquals(6, src.size());
		src.add(7);
		assertEquals(7, src.size());
		src.remove(0);
		assertEquals(6, src.size());
	}
	

}
