package telran.numbers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate implements Iterable<Integer> {
	int minInclusive;
	int maxExclusive;
	Predicate<Integer> predicate = e -> true;
	
	public Predicate<Integer> getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}

	
	public RangePredicate(int minInclusive, int maxExclusive) {
		if (minInclusive >= maxExclusive) {
			throw new IllegalArgumentException("min must be less than max");
		}
		
		this.minInclusive = minInclusive;
		this.maxExclusive = maxExclusive;
	}
	
	public int[] toArray() {
		int[] res = new int[maxExclusive - minInclusive];
		int index = 0;
		var it = this.iterator();
		
		while (it.hasNext()) {
			res[index++] = it.next();
		}
		
		return Arrays.copyOf(res, index);
	}




	private class RangePredicateIterator implements Iterator<Integer> {
		int current;
		int next;
		
		Predicate<Integer> innerPredicate;
	
		
		RangePredicateIterator(Predicate<Integer> predicate) {
			innerPredicate = predicate;	
			current = getNext(minInclusive - 1);
		}

		@Override
		public boolean hasNext() {
			next = getNext(current);
			
			if (next <= maxExclusive) {
				return true;
			}
			return false;	
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int res = current;
			current = next;
			return res;
		}
			
		private int getNext(int min) {
			do {
				min++;
			} while (!innerPredicate.test(min) && min < maxExclusive);
			return min;
		}	
		
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangePredicateIterator(predicate);
	}
	
	
	
	

}
