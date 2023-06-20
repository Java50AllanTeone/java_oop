package telran.exceptions.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.exceptions.BallBrokenFloor;

class ExceptionsTest {

	
	@Test
	void ballBrokenFloorTest() {
		for (int i = 0; i < 100_000; i++) {
			var bbf = new BallBrokenFloor(200);
			assertEquals(bbf.getFloor(), getMinFloor(bbf));
		}
	}

	private int getMinFloor(BallBrokenFloor bbf) {
		int left = 0;
		int right = bbf.getnFloors();
		int middle = (left + right) / 2;
		
		while (left < right) {
			try {
				bbf.broken(middle);
				left = middle + 1;
			} catch (Exception e) {
				right = middle;
			}
			middle = (left + right) / 2;
		}
		return middle;
	}

}
