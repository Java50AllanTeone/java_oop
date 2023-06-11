package telran.util;
import static java.util.Arrays.*;

public class Arrays {
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
	
	public static <T> void bubbleSort(T[] array) {
		boolean flSorted = false;
		int length = array.length;
		
		do {
			flSorted = true;
			length--;
			
			for (int i = 0; i < length; i++) {
				
			}
			
		} while (!flSorted);
	}
	
	
	private static <T> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	
	

}
