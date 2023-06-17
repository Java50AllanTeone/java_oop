package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BinaryOperator;

import org.junit.jupiter.api.Test;

import telran.numbers.CalcData;
import telran.numbers.Calculator;

class CalculatorTest {

	
	@Test
	void calculateTest() {
		assertEquals(20, Calculator.calculate(new CalcData(40, 20, '-')));
		assertEquals(40, Calculator.calculate(new CalcData(40, 0, '-')));
		assertEquals(-40, Calculator.calculate(new CalcData(0, 40, '-')));
		assertEquals(0, Calculator.calculate(new CalcData(0, 0, '-')));
		assertEquals(0, Calculator.calculate(new CalcData(40, 40, '-')));
		assertEquals(-20, Calculator.calculate(new CalcData(20, 40, '-')));
		
		assertEquals(60, Calculator.calculate(new CalcData(40, 20, '+')));
		assertEquals(60, Calculator.calculate(new CalcData(20, 40, '+')));
		assertEquals(0, Calculator.calculate(new CalcData(0, 0, '+')));
		assertEquals(20, Calculator.calculate(new CalcData(0, 20, '+')));
		assertEquals(20, Calculator.calculate(new CalcData(20, 0, '+')));
		assertEquals(0, Calculator.calculate(new CalcData(-20, 20, '+')));
		assertEquals(-40, Calculator.calculate(new CalcData(-20, -20, '+')));
		
		assertEquals(800, Calculator.calculate(new CalcData(40, 20, '*')));
		assertEquals(800, Calculator.calculate(new CalcData(20, 40, '*')));
		assertEquals(1, Calculator.calculate(new CalcData(1, 1, '*')));
		assertEquals(20, Calculator.calculate(new CalcData(20, 1, '*')));
		assertEquals(20, Calculator.calculate(new CalcData(1, 20, '*')));
		assertEquals(-800, Calculator.calculate(new CalcData(-40, 20, '*')));
		assertEquals(800, Calculator.calculate(new CalcData(-40, -20, '*')));
		assertEquals(-800, Calculator.calculate(new CalcData(40, -20, '*')));
		
		assertEquals(2, Calculator.calculate(new CalcData(40, 20, '/')));
		assertEquals(0.5, Calculator.calculate(new CalcData(20, 40, '/')));
		assertEquals(40, Calculator.calculate(new CalcData(40, 1, '/')));
		assertEquals(1, Calculator.calculate(new CalcData(1, 1, '/')));
		assertEquals(-2, Calculator.calculate(new CalcData(40, -20, '/')));
		assertEquals(-2, Calculator.calculate(new CalcData(-40, 20, '/')));
		assertEquals(2, Calculator.calculate(new CalcData(-40, -20, '/')));

	}
}


