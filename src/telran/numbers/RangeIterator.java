package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeIterator implements Iterator<Integer> {
	private int minInclusive;
	private int maxExclusive;
	private int current;

	public RangeIterator(int min, int max) {
		this.minInclusive = min;
		this.maxExclusive = max;
		current = minInclusive;
	}

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
