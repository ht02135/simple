package simple.packages.stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

import simple.packages.Instructor;

public class MethodReferenceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exec(args);
	}

	static public void exec(String[] args) {
		System.out.println("run static public exec");
		MethodReferenceExample me = new MethodReferenceExample();

		me.runMethodReference(args);
	} // exec

	public void runMethodReference(String[] args) {
		System.out.println("run instance public runMethodReference");
		
		
		//-------------------------------------------
		//Static method references
		DoubleUnaryOperator sqrt = (a) -> Math.sqrt(a);
		DoubleUnaryOperator sqrt2 = Math::sqrt;

		IntBinaryOperator max = (a, b) -> Integer.max(a, b); //more than one args
		IntBinaryOperator max2 = Integer::max;
		
		//-------------------------------------------
		//Bound method references
		/*
		But bounded refers to a situation when you're calling a method in a lambda to an 
		external object that already exists
		*/
		
		//System.out is existing object
		Consumer<String> printer = (s) -> System.out.println(s); //System.out is existing object
		Consumer<String> printer2 = System.out::println;
		
		// now is existing object
		var now = LocalDate.now();
		// LAMBDA BASED ON EXISTING OBJECT
		Predicate<LocalDate> isNowAfter = (d) -> now.isAfter(d); // now is existing object
		// BOUND NON-STATIC METHOD REFERENCE
		Predicate<LocalDate> isNowAfter2 = now::isAfter;

		//-------------------------------------------
		//Unbound method references
		/*
		unbound receiver such as String::length is you're referring to a method of an object 
		that will be supplied as one of the lambda's parameters
		*/
		Function<String, Integer> toLength = (s) -> s.length();
		Function<String, Integer> toLength2 = String::length;
		
		Function<Instructor, String> getName = (i) -> i.getName();
		Function<Instructor, String> getName2 = Instructor::getName;
		
		BiFunction<String, String, Integer> indexOf = (sentence, word) -> sentence.indexOf(word);
		BiFunction<String, String, Integer> indexOf2 = String::indexOf;
		
		//-------------------------------------------
		//Constructor method references
		Supplier<List<String>> newListOfStrings = () -> new ArrayList<>();
		Supplier<List<String>> newListOfStrings2 = ArrayList<String>::new;
		
	}
	//me.runMethodReference(args);
	
}
