package predicate.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import predicate.MyPredicate;

class MyPredicateTest {
	MyPredicate<Integer> pred = e -> e > 20;;

	@Test
	void predicateTest() {
		assertTrue(pred.test(50));
		assertTrue(pred.test(21));
		assertTrue(pred.test(100));
		
		assertFalse(pred.test(0));
		assertFalse(pred.test(20));
		assertFalse(pred.test(-50));
	}
	
	@Test
	void andTest() {
		pred = pred.and(e -> e < 100);
		
		assertTrue(pred.test(50));
		assertTrue(pred.test(21));
		assertTrue(pred.test(99));
		
		assertFalse(pred.test(0));
		assertFalse(pred.test(20));
		assertFalse(pred.test(100));
	}
	
	@Test
	void negateTest() {
		pred = pred.negate();
	
		assertTrue(pred.test(0));
		assertTrue(pred.test(20));
		assertTrue(pred.test(-50));
		
		assertFalse(pred.test(50));
		assertFalse(pred.test(21));
		assertFalse(pred.test(100));
	}
	
	@Test
	void isEqualTest() {
		pred = MyPredicate.isEqual(500);
	
		assertTrue(pred.test(500));	
		assertFalse(pred.test(100));
	}

}
