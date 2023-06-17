package predicate;

public interface MyPredicate<T> {
	
	boolean test(T elem);
	
	default MyPredicate<T> and(MyPredicate<T> other) throws NullPointerException {

		return e -> this.test(e) && other.test(e);
	}
	
	default MyPredicate<T> negate() {
		
		return e -> !this.test(e);
	}
	
	default MyPredicate<T> or(MyPredicate<T> other) throws NullPointerException {
		
		return e -> this.test(e) || other.test(e);
	}
	
	static <T> MyPredicate<T> isEqual(Object target) {
		
		return e -> target.equals(e);
	}
	
	
}
