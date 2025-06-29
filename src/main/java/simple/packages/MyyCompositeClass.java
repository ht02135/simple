package simple.packages;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class MyyCompositeClass implements MyyCompositeInterface {

	public Boolean displayResult(String a, String b) {
		// TODO Auto-generated method stub
		//BiPredicate need boolean test(T t, U u)
		BiPredicate<String, String> biPredicate = (x, y) -> {
			return x.length() > y.length();
		};
		return (Boolean) biPredicate.test(a, b);
	}

	public void displayHelloWorld(String a) {
		//wrap lambda
		//A consumer that returns something is not a consumer anymore.
		//so consumer return type is void
	    Consumer<String> consumer = (i) -> { System.out.println(i+" display HelloWorld"); };
	    consumer.accept(a);
	}

	public void displayIncrementByFive(int a) {
		//wrap lambda
		//A consumer that returns something is not a consumer anymore.
		//so consumer return type is void
		IntConsumer incrementByFive = (i) -> { System.out.println("displayIncrementByFive i="+i+5); };
		incrementByFive.accept(a);
	}

	public static void main(String[] args) {
        exec(args); 
	}
	
	static public void exec(String[] args) {
		System.out.println("run static public exec");
		
		MyyCompositeClass me = new MyyCompositeClass();
		me.displayHelloWorld("test");
		me.displayIncrementByFive(10);
		if (me.displayResult("test","hello"))
			System.out.println("return true");
		else
			System.out.println("false");
	}
}

/*
summary, seems fine to use lambda in class method if lambda expression is optimal one lime...
otherwise, you risk turning your code into spaghetti...
*/