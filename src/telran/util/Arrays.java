package telran.util;
import static java.util.Arrays.*;

import java.util.Comparator;

public class Arrays {
	
	public static Comparator<Integer> comparFunc = (o1, o2) -> {
		int res;
	
		if (isEven(o1) && isEven(o2)) {
			res = o1 - o2;
		} else if (!isEven(o1) && !isEven(o2)) {
			res = o2 - o1;
		} else if (isEven(o1)) {
			res = -1;
		} else {
			res = 1;
		}
		return res;
	};
	
	
	public static <T> T[] addObject(T[] array, T obj) {
		T[] res = copyOf(array, array.length + 1);
		res[array.length] = obj;
		return res;
	}
	
	public static <T> T[] insertObject(T[] array, int index, T obj) {
		T[] res = copyOf(array, array.length + 1);
		System.arraycopy(array, 0, res, 0, index);
		System.arraycopy(array, index, res, index + 1, array.length - index);
		res[index] = obj;
		
		return res;
	}
	
	private static boolean isEven(Integer num) {
		return num % 2 == 0;
	}
	
	public static <T> void bubbleSort(T[] array, Comparator<T> comp) {
		boolean flSorted = false;
		int length = array.length;
		
		do {
			flSorted = true;
			length--;
			
			for (int i = 0; i < length; i++) {
				if (comp.compare(array[i], array[i + 1]) > 0) {
					flSorted = false;
					swap(array, i, i + 1);
				}
			}	
		} while (!flSorted);
	}

	private static <T> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
