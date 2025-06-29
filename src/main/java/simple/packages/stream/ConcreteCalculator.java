package simple.packages.stream;

public class ConcreteCalculator implements Calculator {

	//override/implement abstract method
	@Override
	public int sum(int num1, int num2) {
		return num1 + num2;
	}

}
