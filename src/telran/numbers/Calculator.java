package telran.numbers;

import java.util.function.BinaryOperator;

public class Calculator {
	static DoubleBinaryOperator[] operators = {
			(a, b) -> a + b,
			(a, b) -> a - b,
			(a, b) -> a * b,
			(a, b) -> a / b,
	};	
	static String operations = "+-*/";

	
	public static double calculate(CalcData cd) {
		DoubleBinaryOperator operator = operators[operations.indexOf(cd.getOperation())];
		return operator.apply(cd.getOp1(), cd.getOp2());
	}

}


interface DoubleBinaryOperator extends BinaryOperator<Double> {
	
}