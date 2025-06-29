package simple.packages.stream;

import java.io.*;
import java.math.*;
import java.security.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

import simple.packages.*;
import simple.packages.stream.*;

class RandomTokens{
    long id;
    long tokens;

    public RandomTokens(long id, long tokens) {
        this.id = id;
        this.tokens = tokens;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTokens() {
        return tokens;
    }

    public void setTokens(long tokens) {
        this.tokens = tokens;
    }
}

public class StreamExample {
	
	static long tokenCount = 50000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exec(args);
	}

	static public void exec(String[] args) {
		System.out.println("run static public exec");
		StreamExample me = new StreamExample();
		System.out.println("============================");
		me.eclipseTutorial(args);
		System.out.println("============================");
		me.runMethodReference(args);
		System.out.println("============================");
		me.runException(args);
		System.out.println("============================");
		
		me.runSection6(args);
		me.runSection7(args);
		me.runSection8(args);
		me.runSection10(args);
		me.runSection10Part2(args);
		me.runSection11(args);
		me.runSection12(args);
		
	} // exec
	
	public void eclipseTutorial(String[] args) {
		System.out.println("enter instance public eclipseTutorial");
		
		// https://www.digitalocean.com/community/tutorials/eclipse-shortcuts
		
		// CTRL+SPACE is auto suggestion take crown of MOST useful shortcut...
		// type I then CTRL+SPACE is auto suggestion
		// it popup list of interface and class for you to choose
		// wonder if this include interface and class in jar...
		
		// type I then CTRL+1
		// Quickfix for errors and warnings, depends on the cursor position
		// this i pretty useless too, because you can just click (x) icone....
		
		// Ctrl + Shift + F	Format source code
		
		// Ctrl + Shift + R	Open / Search for resources (.java .txt files)
		// my 2 cent not too useful compare just do search...
		
		// Ctrl + Shift + T	Open / Search for types, very useful in finding classes
		// is either this or CTRL+SPACE
		// my 2 cent not too useful too, because i probably just use CTRL+SPACE
		// who can say no to type 2 button than 3 button....
		// opening classes (types), they may not be in files but in jars.
		
		// Debug Mode : seems need to switch to Debug Perspective
		// once you are done just flip back to Java Perspective...
		// to debug, you have to do 'Debug As' and not 'Run As'
		// if youd 'Run As', then it seems you not trigger flip into Debug Mode/Perspective...
		
		// conditional breakpoint
        /*
        steps
        1>setup a break at System.out.println("display x="+x);
        2>go into Debug Perspective
        3>go to breakpoint property of the breakpoint
        4>check/turn on Conditional
        5>type into conditional box 'x >= 3.0'
        6>then 'Debug As' and will trigger break point at x-3.3
        */
        DoubleStream numbers = giveMeNumbers(); // Refactor >> Extract Method convenient...
        numbers.forEach((x) -> {
        	// set breakpoint in this line to test out conditional breakpoint....
        	System.out.println("display x="+x);
        });
        
        /*
        F4 bring up type hiearchy TAB
        CTRL+T also pop quick type hiearchy POPUP
        1>Select say 'DoubleStream'
        2>press F4 show Type Hierarchy
        */
        
        List<Instructor> enhancedInstructors = EnhancedInstructors.getAll();

        Map<String, List<String>> enhancedInstructorMap = enhancedInstructors.stream()
	            .filter(i -> i.isOnlineCourses()) // .filter(i -> i.isOnlineCourses())
	            .filter(i -> i.getYearsOfExperience()>10) // .filter(i -> i.getYearsOfExperience()>10)
	            .peek(s-> System.out.println(s))
	            .collect(Collectors.toMap(Instructor::getName, Instructor::getCourses));
        System.out.println("enhancedInstructorMap:"+enhancedInstructorMap);
        
        System.out.println("exit instance public eclipseTutorial");
	} //eclipseTutorial

	private DoubleStream giveMeNumbers() {
		DoubleStream numbers = DoubleStream.of(1.2,2.2,3.3,4.4,5.5);
		return numbers;
	}

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
		
		//--------------------------------------------
		/*
		when to use method reference for lambda
		You use lambda expressions to create anonymous methods
		however, a lambda expression does nothing but call an existing method
		Method references enable you use existing method than defining new 'anonymous methods'
		*/
		List<Instructor> enhancedInstructors = EnhancedInstructors.getAll();

		//all lambda = defining/calling anonymous method
		System.out.println("=========================");
        Map<String, List<String>> enhancedInstructorMap = enhancedInstructors
        	.stream()
        	.filter((i) -> i.isOnlineCourses()) // .filter(i -> i.isOnlineCourses())
	        .filter((i) -> i.getYearsOfExperience()>10) // .filter(i -> i.getYearsOfExperience()>10)
	        .peek((s) -> System.out.println(s))
	        .collect(Collectors.toMap((i) -> i.getName() , (i) -> i.getCourses()));
        System.out.println("lambda lambdaenhancedInstructorMap:"+enhancedInstructorMap);
        
        //method reference = defining/calling existing method
        //since pretty much all those method are defined, just use method reference...
        System.out.println("=========================");
        enhancedInstructorMap = enhancedInstructors
        	.stream()
	        .filter(Instructor::isOnlineCourses)
	        //this one cant be done with just Instructor::getYearsOfExperience
	        //because it has additional code >5
	        .filter((i) -> i.getYearsOfExperience()>5)
	        .peek(System.out::println)
	        .filter(Instructor::getGreaterThan10)
	        .peek(System.out::println)
	        .collect(Collectors.toMap(Instructor::getName, Instructor::getCourses));
        System.out.println("method reference enhancedInstructorMap:"+enhancedInstructorMap);
        
        System.out.println("exit instance public runMethodReference");
	} //MethodReferenceExample 

	public void runException(String[] args) {
		System.out.println("run instance public runException");
		
		//---------------------------------------------------
		/*
		exceptions
		/////////////////////////////////////////
		1>java.lang.Exception
		a>checked exceptions that must be handled explicitly
		b>For example, file I/O operations or database access issues are typical use cases for 
		  checked exceptions (Exception). They require explicit handling in the code, like 
		  retrying the operation
		/////////////////////////////////////////
		2>java.lang.RuntimeException
		is an essential class that allows your application to handle unexpected problems without 
		crashing. Runtime exceptions are exceptions only detected during the execution of your 
		app - things like invalid user input or issues with external resources like files or 
		networks.
		Runtime exceptions represent problems that are the result of a programming problem, and as 
		such, the API client code cannot reasonably be expected to recover from them or to handle 
		them in any way. Such problems include arithmetic exceptions, such as dividing by zero; 
		pointer exceptions, such as trying to access an object through a null reference; and 
		indexing exceptions, such as attempting to access an array element through an index 
		that is too large or too small.
		a>unchecked exceptions that do not require explicit handling
		b>for programming errors that should generally be fixed by the developer, but it’s not 
		  mandatory to handle them explicitly in the code
		/////////////////////////////////////////
		4 best practices for custom exceptions
		1>Always provide a benefit
		It provides information or functionality that is not part of Java’s standard exceptions.
		2>Follow the naming convention
		3>Provide Javadoc comments for your exception class
		4>Provide a constructor that sets the cause
		5>reality do we need to extend/subclass Exception and RuntimeException ????
		*/
        try {
    		int denominator = 0;
    		if (denominator == 0) {
                throw new RuntimeException("You can't divide by zero!");
            }
        } catch (RuntimeException re) { //unchecked RuntimeException
        	System.out.println("runchecked exception RuntimeException re="+re);
        	re.printStackTrace();
        } catch (Exception e) { //checked Exception
        	System.out.println("checked exception Exception re="+e);
        	e.printStackTrace();
        }
        
        /*
        1>java.lang.Exception (They're usually for external factors that you can anticipat)
        a>Checked exceptions are problems that Java forces you to plan for in advance. You must 
          either handle them or declare that your method might throw them. They're usually 
          for external factors that you can anticipate.
        b>like bad weather. You know these might happen, so you're required to prepare for them
        
        2>java.lang.RuntimeException (no force to handle. often due to programming errors)
        a>Unchecked exceptions, on the other hand, are problems that Java doesn't force you to 
          plan for. You can handle them if you want, but you're not required to. They're often 
          due to programming errors.
        b>like running out of gas or getting a flat tire. These are things that could happen due 
          to your own oversight. You're not legally required to constantly check your fuel gauge 
          or tire pressure, but if these problems occur, your trip will still be disrupted.
          
        3>technique to use RuntimeException and java.lang.RuntimeException
          if situation is unchecked like bug -> RuntimeException
          else default to ->java.lang.Exception
        */
        String fileName = "file does not exist"; 
        File file = new File(fileName);
        try {
        	FileInputStream stream = new FileInputStream(file); 
        } catch (RuntimeException re) { //unchecked RuntimeException
        	System.out.println("runchecked exception RuntimeException re="+re);
        	re.printStackTrace();
        } catch (Exception e) { //checked Exception
        	/*
        	this will be exec
        	checked exception Exception re=java.io.FileNotFoundException: file does not exist 
        	(The system cannot find the file specified) java.io.FileNotFoundException: file 
        	does not exist (The system cannot find the file specified)
				at java.base/java.io.FileInputStream.open0(Native Method)
        	*/
        	System.out.println("checked exception Exception re="+e);
        	e.printStackTrace();
        }
        
        /*
        List of RuntimeException examples
		ArithmeticException
		NullPointerException
		ClassCastException
		DateTimeException
		ArrayIndexOutOfBoundsException
		NegativeArraySizeException
		ArrayStoreException
		UnsupportedOperationException
		NoSuchElementException
		ConcurrentModificationException
        */
        try {
        	//ArtithmeticException
        	int x = 100;
        	int y = 0;  // denominator is set to zero
        	System.out.println( x/y ); // throws ArithmeticException
        	
        	//NullPointerException
        	String data = null;
        	System.out.println( data.length() ); // throws NullPointerException
        	
        	//ClassCastException 
        	//no idea why this is "runtime exception" when eclipse already front run the trade...
        	Date today = new Date();
        	//in reality you will get this unchecked exception error (x) in eclipse
        	//Timestamp time = (Timestamp) today;
        	
        	//DateTimeException
        	LocalDate now = LocalDate.now();
        	DateTimeFormatter.RFC_1123_DATE_TIME.format(now);
        	
        	//ArrayIndexOutOfBoundsException (with rise of collection, chance encounter array exception?)
        	String[] data2 = new String[5];
        	data2[6] = "More Data";
        	
        	//NegativeArraySizeException (with rise of collection, chance encounter array exception?)
        	String[] data3 = new String[-5]; // throws Runtime Exception
        	data3[1] = "More Data";
        	
        	//ArrayStoreException (with rise of collection, chance encounter array exception?)
        	/*
        	This Java runtime exception happens when the wrong type of object is placed into an array.
        	a BigInteger array is created, followed by an attempt to add a Double. The Double does not 
        	share a relationship with the BigInteger, so this triggers an ArrayStoreException at runtime.
        	*/
        	Number[] bigInt = new BigInteger[5];
        	bigInt[0] = Double.valueOf(12345);
        	
        	//UnsupportedOperationException
        	/*
        	Java programmers often use the Arrays class to morph a Java array into a more user-friendly
        	List. However, the List that Java returns is read-only. Developers who are unaware of this
        	fact and try to add new elements run into the UnsupportedOperationException.
        	*/
        	Integer[] data4 = {1,2,3,5,8,13,21};
        	List<Integer> list4 = Arrays.asList(data4); //asList return fixed size list
        	list4.add(new Integer(0)); //throw will throw UnsupportedOperationException
        	
        	//NoSuchElementException
        	//You can't iterate through an empty iterator.
        	Set set = new HashSet();
        	set.iterator().next(); // Java runtime exception thrown
        	
        	if (!set.isEmpty()) {
        		set.iterator().next();
        	}
        	
        	//ConcurrentModificationException
        	/*
        	When you iterate over a list, the underlying collection must be fixed and not updated. 
        	Thus, the add method within the following snippet of code throws a 
        	ConcurrentModificationException.
            */
        	List servers = new ArrayList();
        	servers.add("Tomcat");
        	                   
        	Iterator<String> iterator = servers.iterator();
        	while (iterator.hasNext()) {
        	  String server = iterator.next();
        	  servers.add("Jetty");  // throws a Runtime Exception
        	}
        	
        } catch (RuntimeException re) { //unchecked RuntimeException
        	/*
        	runchecked exception RuntimeException re=java.lang.ArithmeticException: / by zero
			java.lang.ArithmeticException: / by zero
        	*/
        	System.out.println("runchecked exception RuntimeException re="+re);
        	re.printStackTrace();
        } catch (Exception e) { //checked Exception
        	System.out.println("checked exception Exception re="+e);
        	e.printStackTrace();
        }
        
		System.out.println("exit instance public runException");
	}
		
	public void runSection6(String[] args) {
		System.out.println("run instance public runSection6");
		/*
		stream=sequence of obj support various method can be pipelined to produce 
		desired result
		1>stream API is used to process collection of obj
		2>java.util.Stream support map-reduce-filter transformation on stream 
		3>think stream as abstraction over collection (just like list map blabla)
		4>Stream does not change data structure, only return result per pipeline operation
		5>each operation is lazy exec and returned stream can pass to next pipeline operation..
		*/
		
		/*
		intermediate operation
		1>Map
		Stream<ElementType> —> map() operation —> Stream<NewElementType>
		map() method relies on a functional interface called Function<T, R>. 
		This interface represents a function that accepts one argument of type T 
		and produces a result of type R
		*/
	    Function<Integer, Double> sqrt= (a) -> { return Math.sqrt(a); };
	    Function<Integer, Double> sqrt2= (a) -> Math.sqrt(a);
	    Function<Integer, Double> sqrt3 = Math::sqrt;
	       
		List<String> listOfStrings = Arrays.asList("1", "2", "3", "4", "5");
		
		//proper () -> {} setup
		//apparantly, you need to do cast in map(..)
		//run collect to turn Stream into List<Integer>
		List<Integer> listOfIntegers = listOfStrings.stream()
				.map((s) -> { return (Integer) Integer.valueOf(s); } ).collect(Collectors.toList());
		System.out.println("map listOfIntegers:"+listOfIntegers);
		
		//run collect to turn Stream into List<Integer>
		List<Integer> listOfIntegers2 = listOfStrings.stream()
				.map((s) -> (Integer) Integer.valueOf(s)).collect(Collectors.toList());
		System.out.println("map listOfIntegers2:"+listOfIntegers2);
		
		//method reference Integer::valueOf
		//run collect to turn Stream into List<Integer>
		List<Integer> listOfIntegers3 = listOfStrings.stream()
			.map(Integer::valueOf).collect(Collectors.toList());
		System.out.println("map listOfIntegers3:"+listOfIntegers3);
		
		//-------------------------------
		/*
		Filter
		filter result based on predicate passed in function
		*/
		List<Integer> list = Arrays.asList(3, 4, 6, 12, 20);
		System.out.println("filter list:"+list);
		
		//run collect to turn Stream into List<Integer>
		List<Integer> list2 = list.stream()
		    .filter(num -> num % 5 == 0).collect(Collectors.toList());
		System.out.println("filter list2:"+list2);
				
		list.stream()
            .filter(num -> num % 5 == 0)
            .forEach((i) -> { System.out.println("filter i:"+i); } );
		
		//-------------------------------
		/*
		Sorted
		sorted method used to sort stream based on comparator
		*/
		List<Integer> list3 = Arrays.asList(-9, -18, 0, 25, 4);
		//list3.stream().sorted().forEach(System.out::println);
		list3.stream().sorted().forEach((i) -> { System.out.println(i); });
		
		//-----------------------------
		/*
		Terminal operation on stream...
		1>collect()
		2>forEach()
		3>reduce() - perform reduction on element of stream
		map-reduce-filter operation
		*/
		//reduce method to get the longest String
		//variable = (condition) ? expressionTrue :  expressionFalse;
		List<String> words = Arrays.asList("GFG", "Geeks", "for", "GeeksQuiz", "GeeksforGeeks");
		Optional<String> longestString = words.stream()
	            .reduce((word1, word2) -> { return word1.length() > word2.length() ? word1 : word2; } );
		//longestString.ifPresent(System.out::println);
		//ifPresent take Consumer so no return
		longestString.ifPresent((s) -> { System.out.println("reduce longestString:"+s); } );
	
		Optional<String> longestString2 = words.stream()
	            .reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2);
		longestString2.ifPresent((s) -> { System.out.println("reduce longestString2:"+s); } );
		
		// to get the sum of all elements
		// Using reduce to find the sum of all elements
        int sum = list.stream()
	            .reduce(0, (element1, element2) -> element1 + element2);
        System.out.println("The sum of all elements is " + sum); 
        
        //-------------------------------------
        //example
        Predicate<Instructor> p1 = i -> i.isOnlineCourses();
        Predicate<Instructor> p2 = i -> i.getYearsOfExperience()>10;

        List<Instructor> instructors = Instructors.getAll();
        instructors.stream().filter(p1).filter(p2);

        Map<String, List<String>> map = instructors.stream()
	            .filter(p1) // .filter(i -> i.isOnlineCourses())
	            .filter(p2) // .filter(i -> i.getYearsOfExperience()>10)
	            .peek(s-> System.out.println(s))
	            .collect(Collectors.toMap(Instructor::getName, Instructor::getCourses));
        System.out.println("filter filter map:"+map);

        Map<String, List<String>> map2 = instructors.stream()
                .filter(p1)
                .filter(p2)
                .peek(s-> System.out.println(s))
                .collect(Collectors.toMap(Instructor::getName, Instructor::getCourses));
        System.out.println("filter filter map:"+map2);
        
        /*
        open declaration
        1>filter() Stream<T> filter(Predicate<? super T> predicate);
        filter takes Predicate and return Stream
        2>toMap
        public static <T, K, U>
        Collector<T, ?, Map<K,U>> toMap(
        	Function<? super T, ? extends K> keyMapper,
            Function<? super T, ? extends U> valueMapper)
        interesting both are Function
        3>intellj has stream trace to inspect the flow...  does eclipse have similar???
        */

        /*
        1>collection - data structure hold data, can add/remove element from collection, 
          collection have to be iterated externally
        1a>collection can traverse multi time, collection eagerly constructed,
          example List, Set, Map
        2>stream NOT data structure, take input from collection and perform action on them,
          CANT add/remove element from collection, stream internally iterated...
        2a>stream can traver only ONCE, stream lazy constructed (need terminal operation), 
          example, map, reduce, filter...
        */
        
        /*
        debug via Stream API peek()
        */
        Predicate<Instructor> onlineCoursePredicate = i -> i.isOnlineCourses();
        Predicate<Instructor> yearGreaterThan10Predicate = i -> i.getYearsOfExperience()>10;
        Consumer<Instructor> debugConsumer = i -> System.out.println("debug before filter:"+i);
        
        System.out.println("============================");
        List<Instructor> instructors2 = Instructors.getAll();
        System.out.println("instructors2:"+instructors2);
        
        Map<String, List<String>> instructorMap = instructors2.stream()
        		.peek(debugConsumer) // Stream<T> peek(Consumer<? super T> action);
                .filter(onlineCoursePredicate)
                .peek(i -> System.out.println("debug after onlineCoursePredicate:"+i))
                .filter(yearGreaterThan10Predicate)
                .peek(i -> System.out.println("debug after yearGreaterThan10Predicate:"+i))
                .collect(Collectors.toMap(Instructor::getName, Instructor::getCourses));
        System.out.println("filter filter map:"+instructorMap);
        System.out.println("============================");
        
        Function<Integer, Double> sqrt4 = i -> Math.sqrt(i);
        List<Integer> intList = Arrays.asList(3, 4, 6, 12, 20);
        
        //<R> Stream<R> map(Function<? super T, ? extends R> mapper);
        List<Double> sqrtIntList2 = intList.stream()
				.map(sqrt4).collect(Collectors.toList());
        System.out.println("sqrtIntList2 calling sqrt4:"+sqrtIntList2);
        
        List<Double> sqrtIntList3 = intList.stream()
				.map(i -> Math.sqrt(i)).collect(Collectors.toList());
        System.out.println("sqrtIntList3 calling i -> Math.sqrt(i):"+sqrtIntList3);
        
        List<Double> sqrtIntList4 = intList.stream()
				.map(Math::sqrt).collect(Collectors.toList());
        System.out.println("sqrtIntList4 calling Math::sqrt:"+sqrtIntList4);
        
        //---------------------------------------------
        //Map example
        //List allow dup and is ordered
        List<String> instructorUpperCaseNameList = Instructors.getAll().stream()
                .map(Instructor::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("instructorUpperCaseNameList:"+instructorUpperCaseNameList);

        //Set, NOT allow dup and is unordered
        Set<String> instructorUpperCaseNameSet = Instructors.getAll().stream()
        		.map(Instructor::getName)
        		.map(String::toUpperCase)
        		.collect(Collectors.toSet());
        System.out.println("instructorUpperCaseNameSet:"+instructorUpperCaseNameSet);
	} //runSection6

	public void runSection7(String[] args) {
		System.out.println("run instance public runSection7");
		/*
		FlapMap = combo of flat and map operation
		used to flatten the stream...
		flatten into words...
		///////////////////
		one way to return unigue collection is using Set, because Set not allow dup		
		*/
        Set<String> flattenCourses = Instructors.getAll().stream()
                .map(Instructor::getCourses)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
        System.out.println(flattenCourses);
        
        //------------------------------------
        //count   distinct
        Long count = Instructors.getAll().stream()
                .map(Instructor::getCourses)
                .flatMap(List::stream)
                .distinct()
                .count();

        System.out.println(count);
        //distinct, sorted
        List<String> courses = Instructors.getAll().stream()
                .map(Instructor::getCourses)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("courses"+courses);

        //anymatch, allmatch and nonmatch
        boolean match = Instructors.getAll().stream()
                .map(Instructor::getCourses) //transform into courses
                .flatMap(List::stream)
                .noneMatch(s -> s.startsWith("J"));
        System.out.println("match"+match);
        
        /*
		The map() method in Java Stream is a functional operation that transforms each element 
		in a stream by applying a specified function to it. The map() method returns a new 
		stream consisting of the transformed elements.
        map() is used to transform stream element.
        ////////////////////
        Java 8's — 
        Consumer - takes input and consume and return nothing, 
        Predicate - takes input and return boolean, 
        Supplier - take nothing and return something, 
        and Function - take input and return output.  that is why map uses Function
///////////////////
		As already discussed in the post that flatMap() is the combination of a map and a 
		flat operation i.e. it first applies the map function and then flattens the result. 
		Let us consider some examples to understand what exactly flattening a stream is.
		Example 1:
		The list before flattening:
		[ [2, 3, 5], [7, 11, 13], [17, 19, 23] ]
		The list has 2 levels and consists of 3 small lists. After Flattening, it gets 
		transformed into a "one level" structure as shown :
		[ 2, 3, 5, 7, 11, 13, 17, 19, 23 ] 
        */
        boolean match2 = Instructors.getAll().stream()
        		.map(Instructor::new) //transform into another new Instructor
        		.map(i -> new Instructor(i) ) //transform into another new Instructor
                .map(i -> { 
                	Instructor temp = new Instructor(i);
                	temp.setName(temp.getName()+"UPDATED in map/transformation operation");
                	return temp;
                }) //transform into another new Instructor
                .map(Instructor::getCourses) //transform into courses in string
                //.flatMap(list -> list.stream())
                .flatMap(List::stream) // flaten the string
                .noneMatch(s -> s.startsWith("J"));
        System.out.println("match2"+match2);
        
        //------------------------------
        //sorted with comparing
        //come to think of, this is quick way to collect data from stream into data structure for analysis
        List<Instructor> list = Instructors.getAll().stream()
                .sorted(Comparator.comparing(Instructor::getName).reversed())
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        
        /*
        filter
        returns stream matching predicate
        it does not do any filtering, but create new stream with element PASS predicate...
        */
        list = Instructors.getAll().stream()
                .filter(instructor -> instructor.getYearsOfExperience()>10)
                .sorted(Comparator.comparing(Instructor::getName))
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        
        /*
        reducing is repeated operation of combine element
        T reduce(T identity, BinaryOperator<T> accumulator);
        you start identity=0
///////////////////////////////
		example of identity <-- initial value of combine
		reduce("", String::concat)
		reduce(true, (a,b) -> a&&b)
		reduce(false, (a,b) -> a||b)
		reduce(Collections.emptySet(),(a,b)->{ Set<X> s=new HashSet<>(a); s.addAll(b); return s; })
		reduce(Double.POSITIVE_INFINITY, Math::min)
		reduce(Double.NEGATIVE_INFINITY, Math::max)
         */
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int results = numbers.stream()
                    //0 +1 = 1        //
                    //1 + 2 = 3        
                    //3 + 3 = 6        
                    //6+ 4 = 10        
                    .reduce(0,(a,b) -> a +b); //in addition, identity need=0
        int results1 = numbers.stream().reduce(1,(a,b) -> a* b); //in multiply, identity need=1
        System.out.println(results);
        System.out.println(results1);
        
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String result = letters.stream()
        			.reduce("", (partialString, element) -> partialString + element);

        Optional instructor = Instructors.getAll().stream()
                .reduce((s1,s2) -> s2.getYearsOfExperience()
                        >s1.getYearsOfExperience()?s2:s1);
        if(instructor.isPresent())
            System.out.println("instructor calling anony bifunction:"+instructor.get());
        
        //Interface BiFunction<T,U,R>
        //tenery operator
        BiFunction<Instructor, Instructor, Instructor> greaterExperience
        	= (s1,s2) -> s2.getYearsOfExperience() > s1.getYearsOfExperience() ? s2 : s1;
        Optional instructor2 = Instructors.getAll().stream()
        		.reduce((s1,s2) -> greaterExperience.apply(s1, s2));
        if(instructor2.isPresent())
        	System.out.println("instructor2 calling greaterExperience:"+instructor2.get());
        
        //------------------------------------
        //ex map-filter-reduce
        //total years of experience instructors
        int result5 = Instructors.getAll().stream()
                .filter(Instructor::isOnlineCourses) //filter instructors
                .map(Instructor::getYearsOfExperience) // transform to getYearsOfExperience
                .reduce(0,Integer::sum); // sum of getYearsOfExperience
        System.out.println(result5);
        
        //------------------------------------
        //max
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8);
        //max using stream max function
        /*
        Optional<T> max(Comparator<? super T> comparator);
        public int compareTo(Integer anotherInteger)
        Integer::compareTo
        (a,b) -> { return a.compareTo(b); }
        */
        Optional result6 = numberList.stream().max(Integer::compareTo);
        result6 = numberList.stream().max((a,b) -> { return a.compareTo(b); });
        if(result6.isPresent())
            System.out.println("result6:"+result6.get());

        int result7 = numberList.stream().reduce(0,(a,b) -> a>b?a:b);
        System.out.println("result7:"+result7);
        
        Optional result8= numberList.stream().reduce((a,b) -> a>b?a:b);
        if(result8.isPresent())
            System.out.println("result8:"+result8.get());

        /*
        Optional<T> reduce(BinaryOperator<T> accumulator);
        Optional<T> max(Comparator<? super T> comparator);
        public int compareTo(Integer anotherInteger)
        Integer::max
        (a,b) -> { return Integer.max(a, b); }
        */
        Optional result9 = numberList.stream().reduce(Integer::max);
        result9 = numberList.stream().reduce((a,b) -> { return Integer.max(a, b); });
        if(result8.isPresent())
            System.out.println("result9:"+result9.get());
        
        //sanity check
        Integer one = Integer.valueOf(1);
        Integer two = Integer.valueOf(2);

        /*
        one.compareTo(two):-1
        two.compareTo(two):0
        two.compareTo(one):1
		*/
        System.out.println("one.compareTo(two):"+one.compareTo(two));
        System.out.println("two.compareTo(two):"+two.compareTo(two));
        System.out.println("two.compareTo(one):"+two.compareTo(one));
        
        /*
        Integer.max(one, two):2
        Integer.max(two, two):2
        Integer.max(two, one):2
        */
        System.out.println("Integer.max(one, two):"+Integer.max(one, two));
        System.out.println("Integer.max(two, two):"+Integer.max(two, two));
        System.out.println("Integer.max(two, one):"+Integer.max(two, one));
        
        boolean match3 = Instructors.getAll().stream()
        		.map(Instructor::new) //transform into another new Instructor
        		.map(i -> new Instructor(i) ) //transform into another new Instructor
                .map(i -> { 
                	Instructor temp = new Instructor(i);
                	temp.setName(temp.getName()+" UPDATED in map/transformation operation");
                	return temp;
                }) //transform into another new Instructor
                .map(i -> i.getGender())
                //no need to flatten, because list in this case is string and not list. so cant get stream
                //.flatMap(list -> list.stream())	
                //.flatMap(List::stream) 			
                .noneMatch(s -> s.contains("UPDATED"));
        System.out.println("match3"+match3);
        
        Function<Instructor, Instructor> updateInstructorMapper = (i) -> {
        	Instructor temp = new Instructor(i);
        	temp.setName(temp.getName()+" UPDATED in map/transformation operation");
        	return temp;
        };
        boolean match4 = Instructors.getAll().stream()
        		.map(Instructor::new) //transform into another new Instructor
        		.map(i -> new Instructor(i) ) //transform into another new Instructor
                .map(i -> { return updateInstructorMapper.apply(i); }) //transform into another new Instructor
                .map(i -> i.getGender())
                //no need to flatten, because list in this case is string and not list. so cant get stream
                //.flatMap(list -> list.stream())	
                //.flatMap(List::stream) 			
                .noneMatch(s -> s.contains("UPDATED"));
        System.out.println("match4"+match4);

        //-----------------------------------
        /*
        findAny
        findFirst
        */
        Optional<Instructor> instructorOptional = Instructors.getAll().stream().findAny();
        if(instructorOptional.isPresent())
            System.out.println(instructorOptional.get());

        Optional<Instructor> instructorOptional2 = Instructors.getAll().stream().findFirst();
        if(instructorOptional2.isPresent())
            System.out.println(instructorOptional2.get());
	} //runSection7

	public void runSection8(String[] args) {
		System.out.println("run instance public runSection8");

		//of example
		//public static<T> Stream<T> of(T... values)
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8);
        stream.forEach(System.out::println);

        System.out.println("-----------");
        
        //iterate generate a stream of 10 even numbers
        //public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f) 
        /*
        UnaryOperator
        Represents an operation on a single operand that produces a result of the same type as 
        its operand
        ////////////////////////////
        iterate with unary (feels like same type mapper/trasformer) factory method...
        */
        UnaryOperator<Integer> unary = i -> i + 2; //input and output need same type
        //UnaryOperator<Integer> unary = (i) -> { return i + 2; };
        Stream<Integer> stream1 = Stream.iterate(0,i->i+2).limit(10);
        stream1.forEach((i) -> System.out.println("i:"+1));

        stream1 = Stream.iterate(0,unary).limit(10);
        stream1.forEach((i) -> System.out.println("unary i:"+1));

        System.out.println("--------------");
        
        //generate 10 random numbers
        /*
        public static<T> Stream<T> generate(Supplier<? extends T> s)
        Supplier take no input and generate output
        ///////////////////
        generate feels like true method factory with supplier
        */
        Stream<Integer> stream2 = Stream.generate(new Random()::nextInt).limit(10);
        stream2.forEach((i) -> System.out.println("stream2 i:"+1));
        //stream2.forEach(System.out::println);
        
        Supplier<Integer> supplier3 = () ->  { return (int) (Math.random() * 1000); }; //random is class method
        Stream<Integer> stream3 = Stream.generate(supplier3).limit(10);
        stream3.forEach((i) -> System.out.println("stream3 i:"+1));
        //stream3.forEach(System.out::println);
        
        Supplier<Integer> supplier4 = () ->  { return (int) (new Random().nextInt()); }; //nextInt is instance method
        Stream<Integer> stream4 = Stream.generate(supplier4).limit(10);
        stream4.forEach((i) -> System.out.println("stream4 i:"+1));
        //stream4.forEach(System.out::println);

        //-------------------------------
        //aggregate function
        
        //----------------------
        // int/long/double to generate stream...
        //of
        DoubleStream numbers = DoubleStream.of(1.2,2.2,3.3,4.4,5.5);
        numbers.forEach(System.out::println);
        System.out.println("--------------");

        //iterate
        numbers = DoubleStream.iterate(0,i->i+2.0).limit(5);
        numbers.forEach(System.out::println);
        System.out.println("--------------");

        //generate
        numbers = DoubleStream.generate(new Random()::nextDouble).limit(5);
        numbers.forEach(System.out::println);
        System.out.println("--------------");

        //range
        numbers = LongStream.range(1,5).asDoubleStream();
        numbers.forEach(System.out::println);
        System.out.println("--------------");

        //rangeClosed
        numbers = LongStream.rangeClosed(1,5).asDoubleStream();
        numbers.forEach(System.out::println);
        System.out.println("--------------");
        
        //-----------------------------
        /*
        Boxing / unboxing
        boxing = convert int to Integer.  primitive to obj
        unboxing = convert Integet tp int.  obj to primitive
        */

        IntStream numStream = IntStream.rangeClosed(0,5000); //primitive int stream
        List<Integer> numbers8 = numStream.boxed().collect(Collectors.toList()); // boxing
        numbers8.forEach(System.out::println);

        Optional<Integer> sum = numbers8.stream().reduce((a,b)-> a + b); // reduce is unboxing
        if (sum.isPresent())
        System.out.println (sum.get());

        int sum1 = numbers8.stream().mapToInt(Integer::intValue).sum(); // mapToInt is unboxing
        System.out.println(sum1);
        
        //-------------------------------
        //MapToObj
        List<RandomIds> randomIds = IntStream.rangeClosed(0,5)
                .mapToObj((i) -> {
                    return new RandomIds(i, ThreadLocalRandom.current().nextInt(100));
                }).collect(Collectors.toList());

        randomIds.forEach(System.out::println);
        System.out.println("------------");

        LongStream longStream = IntStream.rangeClosed(0,5).mapToLong(i -> (long)i);
        longStream.forEach(System.out::println);
        System.out.println("------------");

        DoubleStream doubleStream = LongStream.rangeClosed(0,5).mapToDouble(i -> (double)i);
        doubleStream.forEach(System.out::println);
        
        System.out.println("exit instance public runSection8");
	} //runSection8
	
	public void runSection10(String[] args) {
		System.out.println("run instance public runSection10");
		
		//join
		// result=EFG
        String result = Stream.of("E","F", "G").collect(Collectors.joining());
        System.out.println("result="+result);

        //join overload version1
        //result=E,F,G
        result = Stream.of("E","F","G").collect(Collectors.joining(","));
        System.out.println("result="+result);

        //join overload version1
        /*
        public static Collector<CharSequence, ?, String> joining(
        	CharSequence delimiter,
            CharSequence prefix,
            CharSequence suffix)
        */
        //result={E,F,G}
        result = Stream.of("E", "F", "G").collect(Collectors.joining(",","{", "}"));
        System.out.println("result="+result);

        //instructors names seperated by delimiter and prefix and suffix
        String namesConcatenated = Instructors.getAll().stream()
        	.map(Instructor::getName)
        	.collect(Collectors.joining(",","{", "}"));
        System.out.println("namesConcatenated = " + namesConcatenated);
        
        //---------------------------------
        //counting ex
        long count = Instructors.getAll().stream()
        	.filter(Instructor::isOnlineCourses)
        	.count();
        System.out.println(count);
    
        //collectors.counting
        //something about using Collectors.counting() in more downstream operation...
        /*
        think try to do sql equivalent...
        SELECT working_area, COUNT(*)
		FROM agents
		GROUP BY working_area;
        */
        count = Instructors.getAll().stream()
            .filter(Instructor::isOnlineCourses)
            .collect(Collectors.counting());
        System.out.println("count = " + count);
        
        //counting is mostly for Collectors.groupingBy
        // Output: {Liam=2, Sophia=2, Noah=1, Emma=1}
        List<String> names = List.of("Liam", "Sophia", "Noah", "Liam", "Sophia", "Emma");
        Map<String, Long> nameCount = names
        	.stream()
        	.collect(Collectors.groupingBy(name -> name, Collectors.counting()));
        
        //System.out.println("groupingBy nameCount: " + nameCount); 
        nameCount.forEach((key,value) ->{
            System.out.println("groupingBy countingt: key = " + key + " value = " + value);
        });
        
        //------------------------------
        //mapping
        //map
        List<String> namesList = Instructors.getAll()
        	.stream()
        	.map(Instructor::getName)
            .collect(Collectors.toList());
        namesList.forEach(System.out::println);

        //mapping
        namesList= Instructors.getAll()
        	.stream()
            .collect(Collectors.mapping(Instructor::getName, Collectors.toList()));

        namesList.forEach(System.out::println);

        //Instructors by their years of experience
        Map<Integer, List<String>> mapYearsOfExperienceAndNames = Instructors.getAll()
        	.stream()
            .collect(Collectors.groupingBy(Instructor::getYearsOfExperience,
                     Collectors.mapping(Instructor::getName, Collectors.toList())));
        
        //System.out.println("groupingBy mapYearsOfExperienceAndNames: " + mapYearsOfExperienceAndNames); 
        mapYearsOfExperienceAndNames.forEach((key,value) ->{
            System.out.println("key = " + key + " value = " + value);
        });
        
        //--------------------------------------------
        // minBy() and maxBy() return collector that return min/max given comparator
        // comparitor returns 1,0,-1
        
	} //runSection10
	
	///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
	public void runSection10Part2(String[] args) {
		System.out.println("run instance public runSection10Part2");
		
		Optional<Instructor> instructor = null;
		
		//stream().min
		instructor = Instructors.getAll()
			.stream()
            .min(Comparator.comparing(Instructor::getYearsOfExperience));
        System.out.println("instructor = " + instructor);
        
        //minBy() in stream().collect()
        instructor = Instructors.getAll()
        	.stream()
            .collect(Collectors.minBy(Comparator.comparing(Instructor::getYearsOfExperience)));
        System.out.println("instructor = " + instructor);
        System.out.println("---------------");

        //stream().max()
        //max is used directly on the stream to find the maximum element,
        instructor = Instructors.getAll()
        	.stream()
            .max(Comparator.comparing(Instructor::getYearsOfExperience));
        System.out.println("instructor = " + instructor);

        //maxBy() in stream().collect()
        //maxBy is typically used when collecting elements into a container, such as a List ,
        //and you want to find the maximum after the collection operation
        instructor = Instructors.getAll()
        	.stream()
            .collect(Collectors.maxBy(Comparator.comparing(Instructor::getYearsOfExperience)));
        System.out.println("instructor = " + instructor);
        System.out.println("---------------");
        
        //------------------------------
        //Collectors.summingInt
        int sum = Instructors.getAll()
        	.stream()
            .collect(Collectors.summingInt(Instructor::getYearsOfExperience));
        System.out.println("sum = " + sum);
        
        //calculate average of years of experience of all instructors
        //Collectors.averagingInt
        double average = Instructors.getAll()
        	.stream()
            .collect(Collectors.averagingInt(Instructor::getYearsOfExperience));
        System.out.println("average = " + average);
        
        //-----------------------------
        //-----------------------------
        //groupingBy(classifier)
        //you can build a <custom key , value> pair
        Map<Integer,  List<Instructor>> instructorByNameLength = Instructors.getAll()
        	.stream()
            .collect(Collectors.groupingBy(
            	(i) -> i.getName().length()	//classifier
            ));
        instructorByNameLength.forEach((key,value) -> {
            System.out.println("instructorByNameLength key = " + key + " value = " + value);
        });
        System.out.println("-----------------");
        
        Map<String, List<Instructor>> instructorByGender = Instructors.getAll()
        	.stream()
        	.collect(Collectors.groupingBy(
        		Instructor::getGender //classifier
        	));
        instructorByGender.forEach((key,value) -> {
            System.out.println("instructorByGender key = " + key + " value = " + value);
        });
        System.out.println("-----------------");
        
        //grouping by experience where >10 years of experience is classified
        //as Senior and others are junior
        Map<String, List<Instructor>> instructorsByExperience = Instructors.getAll()
        	.stream()
        	.collect(Collectors.groupingBy(
        		i -> i.getYearsOfExperience()>10 ? "SENIOR": "JUNIOR" //classifier
        	));
        instructorsByExperience.forEach((key,value) -> {
        	System.out.println("instructorsByExperience key = " + key + " value = " + value);
        });
        System.out.println("-----------------");

        //-----------------------------
        //-----------------------------
        //overloaded groupingBy(classifier,downstream)
        //technically overloaded groupingBy can be avoid if you just do filter and then collect...
        //meaning dont involving downstream since you are at .collect() terminating operation...
        List<String> names = List.of("Sid", "Mike", "Jenny", "Gene", "Rajeev");
        Map<Integer, List<String>> result = names
        	.stream()
        	//technically overloaded groupingBy can be avoid if you just do filter and then collect
        	.filter(s-> s.contains("e")) 
            .collect(Collectors.groupingBy(
            	String::length,													// classifier
            	Collectors.filtering(s-> s.contains("e"), Collectors.toList())	// downstream
            ));						
        System.out.println("result = " + result);
        System.out.println("-----------------");

        //instructor grouping them by Senior(>10) and Junior(<10) and filter them
        //on online courses
        Map<String, List<Instructor>> instructorByExpAndOnline = Instructors.getAll()
        	.stream()
        	.collect(Collectors.groupingBy(
        		i -> i.getYearsOfExperience()>10 ? "SENIOR": "JUNIOR", 				// classifier
        		Collectors.filtering(s->s.isOnlineCourses(), Collectors.toList())	// downstream
        	));
        instructorByExpAndOnline.forEach((key, value) -> {
            System.out.println("key  = " + key + " value = " + value);
        });
        System.out.println("-----------------");
        
        /*
        SELECT column_name(s)
		FROM table_name
		WHERE condition         <-- this is your filter
		GROUP BY column_name(s) <-- this is your group by
		ORDER BY column_name(s);
		
		in sql, you filter and groupby
		translate, if sole purpose of overloaded Collectors.groupingBy is filtering, then overloaded
		is useless, because you can filter and then collect without using 'overloaded Collectors.groupingBy' 
        */

        //-----------------------------
        //-----------------------------  
        //overloaded groupingBy(classifier,mapFactory,downstream)
        /*
        spirit of this 'verloaded groupingBy(classifier,mapFactory,downstream)'.
        it internally calls 
        return groupingBy(classifier, HashMap::new, downstream);
        */
        result = names
        	.stream()
            .collect(Collectors.groupingBy(
            	String::length, 			// classifier
            	LinkedHashMap::new, 		// mapFactory
            	Collectors.filtering(s-> s.contains("e"),Collectors.toList()) // downstream
            ));

        System.out.println("mapFactory result = " + result);
        System.out.println("------------------");
        
        instructorByExpAndOnline = Instructors.getAll()
        	.stream().collect(Collectors.groupingBy(
        		i -> i.getYearsOfExperience()>10 ? "SENIOR": "JUNIOR",				// classifier
                LinkedHashMap::new, 												// mapFactory
                Collectors.filtering(s->s.isOnlineCourses(), Collectors.toList())	// downstream
            ));
        instructorByExpAndOnline.forEach((key, value) -> {
            System.out.println("mapFactory instructorByExpAndOnline key  = " + key + " value = " + value);
        });
        System.out.println("-----------------");
        
        //-----------------------------
        //-----------------------------
        //stream().min()
        Optional<Instructor> instructor11 = Instructors.getAll()
            	.stream()
                .min(Comparator.comparing(Instructor::getYearsOfExperience));
        System.out.println("stream().min() instructor11 = " + instructor11);
            
        //Collectors.minBy
        instructor11 = Instructors.getAll()
        	.stream()
            .collect(Collectors.minBy(
            	Comparator.comparing(Instructor::getYearsOfExperience) //comparator
            ));
        System.out.println("Collectors.minBy instructor11 = " + instructor11);
        System.out.println("---------------");

        instructor11 = Instructors.getAll()
        	.stream()
            .max(Comparator.comparing(Instructor::getYearsOfExperience));
        System.out.println("stream().max() instructor11 = " + instructor11);

        instructor11 = Instructors.getAll()
    		.stream()
            .collect(Collectors.maxBy(
            	Comparator.comparing(Instructor::getYearsOfExperience) //comparator
            ));
        System.out.println("Collectors.maxBy instructor11 = " + instructor11);
        System.out.println("---------------");

        //-----------------------------
        //-----------------------------
        //Collectors.partitioningBy
        Predicate<Instructor> experiencePredicate = i -> i.getYearsOfExperience()>10;

        Map<Boolean, List<Instructor>> partitionMap = Instructors.getAll()
        	.stream()
        	.collect(Collectors.partitioningBy( //collect into List by default
        		experiencePredicate //Predicate
        	));
        partitionMap.forEach((key,value)-> {
        	System.out.println("key = " + key + " value = " + value);
        });
        System.out.println("-------------------------------");

        //partition but return is set instead of list
        Map<Boolean, Set<Instructor>> partitionSet = Instructors.getAll()
        	.stream()
        	.collect(Collectors.partitioningBy(
        		experiencePredicate, //Predicate
        		Collectors.toSet()	 //Collector
        	));
        partitionSet.forEach((key,value) -> {
        	System.out.println("key = " + key + " value: " + value);
        });
        
        System.out.println("exit instance public runSection10");
	}
	//runSection10Part2

	//function
    public static Long measureSequentialStream(int numberOfTimes){
        long startTime= System.currentTimeMillis();
        for (int i=0;i<numberOfTimes;i++)
        	sumSequentialStream();
        return System.currentTimeMillis() - startTime;
    }
    
    //function
    public static Long measureParallelStream(int numberOfTimes){
        long startTime = System.currentTimeMillis();
        for (int i=0;i<numberOfTimes;i++)
        	sumParallelStream();
        return System.currentTimeMillis() - startTime;
    }

    //suppler
    public static int sumSequentialStream(){
    	//running sequence
        return IntStream.rangeClosed(0,50000).sum();
    }

    //suppler
    public static int sumParallelStream(){
    	//running parallel
        return IntStream.rangeClosed(0,50000).parallel().sum();
    }
    
	///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
	public void runSection11(String[] args) {
		System.out.println("run instance public runSection11");
		
		//---------------------------
		//parallel stream
		//fork/join frame work
		
		//loop1, result
		List<Integer> loopNumberList = IntStream.rangeClosed(1,20).boxed().collect(Collectors.toList());
		Map<Integer,Long> parallelStreamPerformanceList = loopNumberList
				.stream()
				.collect(Collectors.toMap(
					Integer::valueOf, 						//keyMapper Function
					StreamExample::measureParallelStream	//valueMapper Function
				));
		parallelStreamPerformanceList.forEach((key,value) -> {
        	System.out.println("parallelStreamPerformanceList key = " + key + " value: " + value);
        });
		
		Map<Integer,Long> sequentialStreamPerformanceList = loopNumberList
				.stream()
				.collect(Collectors.toMap(
					Integer::valueOf, 						//keyMapper Function
					StreamExample::measureSequentialStream	//valueMapper Function
				));
		sequentialStreamPerformanceList.forEach((key,value) -> {
        	System.out.println("sequentialStreamPerformanceList key = " + key + " value: " + value);
        });
		
		//-----------------------
	    int loop=20;
	    long result = measurePerformance(StreamExample::sortSequentialStream, loop);
	    System.out.println("Time taken to process sort in sequential: " + result + " msecs");
	        
	    result = measurePerformance(StreamExample::sortParallelStream, loop);
	    System.out.println("Time taken to process sort in parallel: " + result + "msecs");
	    
	    System.out.println("exit instance public runSection11");
	}
    
    public static long measurePerformance(Supplier<Long> supplier, int numberofTimes){
        long startTime = System.currentTimeMillis();
        for (int i=0;i<numberofTimes;i++)
            supplier.get();
        return System.currentTimeMillis() - startTime;
    }

    public static long sortSequentialStream() {
        List<RandomTokens> randomTokens = LongStream.rangeClosed(0,tokenCount)
        	.mapToObj((i) ->{
        		return new RandomTokens(i, ThreadLocalRandom.current().nextLong(tokenCount));
            }).collect(Collectors.toList());
        randomTokens.stream().sorted(Comparator.comparing(RandomTokens::getTokens));
        return -1;
    }

    public static long sortParallelStream() {
        List<RandomTokens> randomTokens = LongStream.rangeClosed(0,tokenCount)
        	.parallel().mapToObj((i) ->{
        		return new RandomTokens(i, ThreadLocalRandom.current().nextLong(tokenCount));
            }).collect(Collectors.toList());
        randomTokens.stream().parallel().sorted(Comparator.comparing(RandomTokens::getTokens));
        return -1;
    }

	///////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    public static boolean ifNull(Integer num) {
        return num == null;
    }
    
    public static boolean ifNull(String str) {
        return str == null;
    }
    
    public static String getStandardWords(){
        String[] words = new String[10];
        String word = words[1];
        return (!ifNull(word)) ? word : "";
    }
    
    //optional is overrated diminishing return compare simple check for null
    public static Optional<String> getOptionalWords(){
        String[] words = new String[10];
        Optional<String> optionalS = Optional.ofNullable(words[1]);
        /*
        if (optionalS.isPresent())
            return optionalS;
        else
            return Optional.empty();
        */
        return (optionalS.isPresent()) ? optionalS : Optional.empty();
    }
    
    public void runSection12(String[] args) {
		System.out.println("run instance public runSection12");

        //orElse
        Integer[] numbers = new Integer[10]; 
        Integer number = numbers[0];
        
        try {
            /*
            tenery operator hint
            variable = (condition) ? expressionTrue :  expressionFalse;
            hmn, you can only do null check on obj but not primitive
            */
        	//what is point of optional when me can check null on object?
            int result = (number != null) ? number.intValue() : -1;
            System.out.println("not using optional result=" + result);
            
            result = (!ifNull(number)) ? number.intValue() : -1;
            System.out.println("ifNullOrZero not using optional result=" + result);
            
            System.out.println("ifNullOrZero sanity check numbers=" + numbers);
            //sanity check numbers[1]=null
            System.out.println("ifNullOrZero sanity check numbers[1]=" + numbers[1]);
        }
        catch(Exception e) {
        	System.out.println("not using optional run into exception="+e);
        }
        
        //using optional
        Optional<Integer> number2 = Optional.ofNullable(numbers[0]);
        int result2 = number2.orElse(-1);
        System.out.println("orElse result2=" + result2);

        //orElseGet takes Supplier
        result2 = number2.orElseGet(() -> -1);
        System.out.println("orElseGet result2=" + result2);
        
        //orElseGet takes Supplier
        Supplier<Integer> supplier2 = () -> -1; // lambda expressio
        result2 = number2.orElseGet(supplier2);
        System.out.println("supplier2 orElseGet result2=" + result2);

        //orElseThrow
        try {
        	//orElseThrow take Supplier
        	//Exception::new is valid supplier...
            result2 = number2.orElseThrow(Exception::new);
            System.out.println("orElseThrow result2=" + result2);
        } catch (Exception e) {
        	System.out.println("orElseThrow="+e);
        }
        
        //---------------------------------------------------
        //more optional lab
        
        Optional<String> optionalString = Optional.of("Hello World");
        System.out.println("optionalString = " + optionalString);
        
        optionalString = getOptionalWords();
        System.out.println("calling getOptionalWords() optionalString:" + optionalString);
        
		System.out.println("exit instance public runSection12");
		
		//---------------------------------------------------
		//isPresent
        Optional<String> stringOptional = Optional.ofNullable("Hello World");
        if(stringOptional.isPresent())
            System.out.println("stringOptional = " + stringOptional);

        stringOptional.ifPresent(s -> System.out.println("s = " + s));
    }
    
} //StreamExample
