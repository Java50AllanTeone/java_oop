package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.numbers.FilteredIterator;
import telran.numbers.RangeIterator;

class FilteredIteratorTest {
	List<Integer> range = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	List<Integer> act;
	List<Integer> exp;

	Iterator<Integer> srcIt;
	Iterator<Integer> it;

	@BeforeEach
	void init() {
		srcIt = new RangeIterator(1, 10);
		act = new ArrayList<>();
		exp = new ArrayList<>();
	}

	// all values accepted from srcIterator are passed through filter
	@Test
	void testAllFiltered() {
		test(e -> true);
	}

	// never values accepted from srcIterator are passed through filter
	void testNoneFiltered() {
		test(e -> false);
	}

	// first accepted value is passed, but last is not passed
	void testFirstFiltered() {
		test(e -> e == 1);
	}

	// first is not passed, but last is passed
	void testLastFiltered() {
		test(e -> e == 9);
	}
	
	// the srcIterator has no values from the beginning
	void testEmpty() {
		srcIt = new RangeIterator(1, 1);
		test(e -> true);
	}

	void testMore() {
		test(e -> e > 5);
	}

	void testLess() {
		test(e -> e < 5);
	}

	void testEven() {
		test(e -> e % 2 == 0);
	}

	void testOdd() {
		test(e -> e % 2 != 0);
	}

	private void test(Predicate<Integer> pred) {
		Iterator<Integer> iter = new FilteredIterator<>(srcIt, pred);

		iter.forEachRemaining(act::add);
		exp = range.stream().filter(pred).collect(Collectors.toList());
		assertEquals(exp, act);
	}

}
