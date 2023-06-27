package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
	private int minInclusive;
	private int maxExclusive;
	private int[] removedNumbers = new int[0];
	
	private class RangeIterator implements Iterator<Integer> {
		int current = minInclusive;

		@Override
		public boolean hasNext() {
			return current < maxExclusive;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int res = current++;
			return res;
		}	
	}

	
	public Range(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("min must be less than max");
		}
		this.minInclusive = min;
		this.maxExclusive = max;
	}
	
	public int length() {
		return maxExclusive - minInclusive - removedNumbers.length;
	}
	
	public int[] toArray() {
		int[] res = new int[length()];
		int index = 0;
		
		for (int num : this) {
			res[index++] = num;
		}
		return res;
	}
	
	
	
	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}
	
	public boolean removeIf(Predicate<Integer> predicate) {
		int oldLength = length();
		Iterator<Integer> it = iterator();
		while (it.hasNext()) {
			int num = it.next();
			
			if (predicate.test(num)) {
				it.remove();
			}
		}
		//TODO
		return oldLength > length();
		
	}

}
