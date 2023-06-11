package telran.util.tests;

import org.junit.jupiter.api.Test;

import static telran.util.Arrays.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArraysTest {
	Integer[] numbers = {10, 5, -5, 100, 200};
	String[] strings = {"lmn", "a", "ab", "abc", "b", "ba"};
	
	
	@Test
	void addObjectTest() {
		Integer[] expectedNumbers = {10, 5, -5, 100, 200, 50};
		assertArrayEquals(expectedNumbers, addObject(numbers, 50));
		
		String[] expectedStrings = {"lmn", "a", "ab", "abc", "b", "ba", "bb"};
		assertArrayEquals(expectedStrings, addObject(strings, "bb"));
	}
	
	@Test
	void insertObjectTest() {
		Integer[] expectedLast = {10, 5, -5, 100, 200, 50};
		Integer[] expectedFirst = {50, 10, 5, -5, 100, 200};
		Integer[] expectedMiddle = {10, 5, 50, -5, 100, 200};
		
		assertArrayEquals(expectedLast, insertObject(numbers, 5, 50));
		assertArrayEquals(expectedFirst, insertObject(numbers, 0, 50));
		assertArrayEquals(expectedMiddle, insertObject(numbers, 2, 50));
		
	}

}
