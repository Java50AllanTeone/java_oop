package telran.people.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.people.Search;

class SearchTest {

	@Test
	void test() {
		int[] src1 = {1, 2, 3, 4, 4, 4, 4, 4, 5, 6, 7, 8};
		int[] src2 = {1, 1, 1, 1, 1, 1, 1, 2, 5, 6, 7, 8};
		int[] src3 = {1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8};
		
		assertEquals(3, Search.binaryFirst(src1, 4));
		assertEquals(0, Search.binaryFirst(src2, 1));
		assertEquals(7, Search.binaryFirst(src3, 8));
		
		assertEquals(7, Search.binaryLast(src1, 4));
		assertEquals(6, Search.binaryLast(src2, 1));
		assertEquals(11, Search.binaryLast(src3, 8));
		
		assertEquals(-1, Search.binaryLast(src1, 0));
		assertEquals(-13, Search.binaryLast(src1, 9));
		
	}

}
