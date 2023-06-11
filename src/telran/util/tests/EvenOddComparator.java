package telran.util.tests;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			if (isEven(o1) && isEven(o2)) {
				return o1 - o2;
			} else if (!isEven(o1) && !isEven(o2)) {
				return o2 - o1;
			} else if (isEven(o1)) {
				return -1;
			} else {
				return 1;
			}
		}
		
		private boolean isEven(Integer num) {
			return num % 2 == 0;
		}
}
