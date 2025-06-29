package simple.packages;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.IntStream;

/*
1>add dependancy to pom.xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.17.0</version>
</dependency>
2>maven >> update projects
It syncs the Eclipse project settings with that of the pom
2a>Dependency Resolution: It updates the project’s classpath to include any new 
dependencies you’ve added in the pom.xml. This ensures that all required libraries 
are available for your project.
2b>Plugin Configuration: If there are any changes to Maven plugins specified in the 
pom.xml, the update will apply those changes to your project.
2c>Project Structure: It can modify the project structure based on the Maven 
conventions. This includes reorganizing source folders, test folders, and resources 
if they are defined differently in the pom.xml.
*/
import org.apache.commons.lang3.*;

import simple.packages.*;

/*
mvn clean install -DskipTests=true
*/

public class JavaFeature {
	
	static int class_level_var=0;

	static public void main(String[] args) {
        exec(args);
        method_reference_turn_code_to_spaghetti(args);
    }
	
	static public void exec(String[] args) {
		System.out.println("run static public exec");
        JavaFeature me = new JavaFeature();
		me.run(args);
		me.runSection2(args);
		
        //-------------------------------------------------------- 
        //-------------------------------------------------------- 
		me.runSection2_29(args);
	}
	
	public void run(String[] args) {
		System.out.println("run instance public run");
		
		/*
1>imperative programming (how with detail steps)
  ex, i see the lamp, i will work to it, take it, and buy it...
2>declarative programing (what declare intent without steps)
  ex, i want to buy that lamp
  ex, SQL is example of declarative programming, select u.* from users
		 */
		
		/*
declarative progamming is smarter
java8 introduced declarative style functional programming using lambda
		 */
		
		//imperative example
		for (int i=1; i<=5; i++) {
			System.out.println("imperative programming for looping "+i);
		}
		
		//declarative exampe
		IntStream numbers = IntStream.of(1,2,3);
		numbers.forEach((i) -> {
			System.out.println("imperative programming for looping "+i);
		});
		
		//int sum = numbers.sum(); <-- cant do this due 'stream has already been operated upon'
		int sum = IntStream.of(1,2,3).sum();
		System.out.println("imperative programming for looping "+sum);
		
		//OptionalInt min = numbers.min();
		OptionalInt min = IntStream.of(1,2,3).min();
		System.out.println("imperative programming for looping "+min);
		
	} //run method
	
	public void runSection2(String[] args) {
		System.out.println("run instance public runSection2");
/*
install jdk-24_windows-x64_bin.msi
JAVA_HOME=C:\Program Files\Java\jdk-24

PS C:\Users\ht021> java --version
java 24.0.1 2025-04-15
Java(TM) SE Runtime Environment (build 24.0.1+9-30)
Java HotSpot(TM) 64-Bit Server VM (build 24.0.1+9-30, mixed mode, sharing)
*/
		
/*
install intellj community
1>File > Project Structure > Project > Project SDK
To change the Java Development Kit (JDK) version in IntelliJ IDEA, navigate to File > Project Structure > Project > Project SDK

2>Settings
go to setting to turn on show line number and blabla
*/
		
/*
lambda feature add in java8
lambda is anonymous function without name does not belong to any class

java method has
name, parameters, body, return_type

lambda expression ha
no_name, parameters, body, no_return_type

lamda expression
( ) -> { }
lambda input_param, arrow denote lambda, lambda body
 */
		
/*
lambda is used to implement functional interface
@FunctionalInterface
public interface Runnable {
	public abstract void run();
}

@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2)
}

@FunctionalInterface
public interface Callable<T> {
    T call() throws Exception
}
*/
		//lambda expression is ( ) -> { }
		//HelloWorldInterface helloWorldInterface = lambda_expression;
		HelloWorldInterface helloWorldInterface = () -> { return "Hello World"; };
		System.out.println("test helloWorldInterface "+helloWorldInterface.sayHelloWorld());

		//personally i dont like this way
		//i much prefer i remember lambda_expression = ( ) -> { };
		//i much prefer () -> { return "Hello World"; };
		HelloWorldInterface helloWorldInterface2 = () -> "Hello World";
		System.out.println("test helloWorldInterface2 "+helloWorldInterface2.sayHelloWorld());

		//lambda_expression with param
		//IncrementByFiveInterface incrementByFiveInterface = (x) -> x + 5;
		IncrementByFiveInterface incrementByFiveInterface = (x) -> { return x + 5; };
		System.out.println("test incrementByFiveInterface "+incrementByFiveInterface.incrementByFive(2));

		//ConcatenateInterface concatenateInterface = (a,b) -> a + " " + b;
		ConcatenateInterface concatenateInterface = (a,b) -> { return a + " " + b; };
		System.out.println("test concatenateInterface "+concatenateInterface.sconcat("Hello", "World"));

		//--------------------------------------------------------
		//Runnable
		//tradition
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				int sum = 0;
				for (int i = 0; i < 10; i++)
					sum += i;
				System.out.println("Traditional: " + sum);
			}
		};
		new Thread(runnable).start();

		//lambda
		Runnable runnable1 = () -> {
			int sum = 0;
			for (int i = 0; i < 10; i++)
				sum += i;
			System.out.println("Runnable Lambda: " + sum);
		};
		new Thread(runnable1).start();

		//just replace runnable1 with lambda_expression
		//using thread with lambda
		new Thread(() -> {
			int sum = 0;
			for (int i = 0; i < 10; i++)
				sum = sum + i;
			System.out.println("Thread Lambda: " + sum);
		}).start();

		//--------------------------------------------------------

		//Java Callable and Future Interfaces
		//we will learn to execute Callable tasks (which return a result of type Future after execution)

		//callable
		int[] array = IntStream.rangeClosed(0,5000).toArray();
		int total = IntStream.rangeClosed(0,5000).sum();
		Callable callable1 = () -> {
			int sum=0;
			for (int i=0;i< array.length/2;i++){
				sum = sum + array[i];
			}
			return sum;
		};
		Callable callable2 = () -> {
			int sum = 0;
			for (int i=array.length/2; i<array.length;i++){
				sum = sum + array[i];
			}
			return sum;
		};

		//In Java multithreading programs, we extensively use Java Callable and Future
		//The Executor Framework offers a submit() method to execute Callable implementations in a thread pool
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		try {
			//seems like List<Callable<Integer>> is kinda of template saying list is type=Callable obj, Callable
			//obj is type=Integer...
			List<Callable<Integer>> taskList = Arrays.asList(callable1, callable2);

			//Executes the given tasks, returning a list of Futures holding their status and results when all complete.
			List<Future<Integer>> results = executorService.invokeAll(taskList);

			int k=0;
			int sum=0;
			for (Future<Integer> result: results){ //loop thru results
				sum = sum + result.get();
				++k;
				//System.out.println("Sum of " + ++k + " is: " + result.get());
				System.out.println("Sum of " + k + " is: " + result.get());
			}
			System.out.println("Sum from the Callable is: " + sum);
			System.out.println("Correct sum from InStream is: " + total);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		//The shutdown() method doesn’t cause immediate destruction of the ExecutorService.
		// It will make the ExecutorService stop accepting new tasks and shut down after
		// all running threads finish their current work:
		executorService.shutdown();

		//--------------------------------------------------------
		//java functional interface in java8
		/*
		@FunctionalInterface
		1>can have only 1 abstract method
		2>can have multiple default method
		3>can have multiple static method

		A functional interface in Java is an interface that contains only one abstract method. Functional
		interfaces can have multiple default or static methods, but only one abstract method
		 */
		MyFunctionalInterface.staticMethod();;
		MyFunctionalInterface.staticMethod2();
		//implement interface via lambda_expression
		MyFunctionalInterface myFunctionalInterface = ( ) -> { System.out.println("run myMethod"); };
		myFunctionalInterface.defaultMethod();
		myFunctionalInterface.defaultMethod2();
		myFunctionalInterface.myMethod();

		MyRegularNonFunctionalClass myRegularNonFunctionalClass = new MyRegularNonFunctionalClass();
		myRegularNonFunctionalClass.defaultMethod();
		myRegularNonFunctionalClass.defaultMethod2();

		//--------------------------------------------------------
		//java.util.function consumer functional interface
		//Consumer Functional Interface pretty plain vanilla..
		Consumer<Integer> d = (x) -> {
			System.out.println("x*x = " + x*x);
			System.out.println("x/x = " + x/x);
		};
		d.accept(10);
		
		List<Instructor> instructors = Instructors.getAll();
		Consumer<Instructor> c1 = (x) -> {
			System.out.println("c1 x:"+x);
		};
        instructors.forEach(c1); //loop thru each instructor and perform c1 lambda
        instructors.forEach((x) -> { //loop thru each instructor and perform anonymous lambda which call ci lambda
            if(x.yearsOfExperience>10){
                c1.accept(x);
            }
        });
        
        Consumer<Instructor> c2 = (x) -> {
        	System.out.print("c2 x.getName:"+x.getName());
        };
        Consumer<Instructor> c3 = (x) -> {
        	System.out.println("c3 x.getCourses:"+x.getCourses());
        };
        instructors.forEach(c2.andThen(c3)); //loop c2 and then c3 lambda
        //if you see clearly, inside forEach(..) is lambda expression...
        instructors.forEach((x) -> {
            if (x.yearsOfExperience > 5 && !x.isOnlineCourses()){
                c1.andThen(c2).accept(x); //andThen is  Consumer default method
            }
        } );

		//--------------------------------------------------------
        //BIConsumer is Consumer takes 2 args

        //calculating sum of two integers
        BiConsumer<Integer, Integer> biConsumer1 = (x,y) -> { 
        	System.out.println("x+y: " + (x+y));
        };
        biConsumer1.accept(2,4);

        //concatenate strings
        BiConsumer<String,String> biConsumer2 = (x,y) -> {
        	System.out.println(x+y);
        };
        biConsumer2.accept("Fell on", " deaf ears");
        
        //lambda arg best use sensible arg name than just i or x...
        BiConsumer<String, String> biConsumer = (name, gender) -> {
        	System.out.println("name is :" + name + " and gender is: " + gender);
        };
        instructors.forEach((instructor) -> {
            biConsumer.accept(instructor.getName(), instructor.getGender());
        });
            
        BiConsumer<String, List<String>> biConsumer3 = (name, courses) -> {
        	System.out.println("name is " + name + " courses: " + courses);
        };
        instructors.forEach((instructor) -> {
            biConsumer3.accept(instructor.getName(), instructor.getCourses());
        });

		//--------------------------------------------------------
        //PredicateConsumer, single arg return true/false, has test method
        //if number is >10 return true other false
        //Predicate<Integer> p1 = (i) -> i>10;
        
        /*
7
You are confused about the scope of the return statement. The return statement (whether 
inserted as bytecode by the compiler or as source code by the programmer) returns from 
the lambda, and not from the method that calls the lambda.
Yes, when specifying only a single statement, its value is automatically returned 
from the lambda.
         */
        Predicate<Integer> p1 = (i) -> {
        	return i>10;
        };
        System.out.println("calling p1:"+p1.test(100));

        //i>10 && number is even number (i%2 ==0)
        //Predicate<Integer> p2 = (i) -> i%2==0;
        Predicate<Integer> p2 = (i) -> {
        	return i%2==0;
        };
        System.out.println("calling p2:"+p1.and(p2).test(20)); //and is Predicate default method
        
        //i>10 || number is even number (i%2==0)
        System.out.println("calling p2:"+p1.or(p2).test(4));
        
        //Lambdas can be categorized as a syntactic sugar.
        Predicate<Integer> p3 = new Predicate() // traditional implement anony interface
        {
			@Override
			public boolean test(Object i) {
				Integer temp = (Integer) i;
				return temp%2==0;
			}
        };
        System.out.println("calling p3:"+p2.and(p3).test(20));

        //all instructor who teaches online
        Predicate<Instructor> p4 = (i) -> {
        	return i.isOnlineCourses()==true;
        };
        //instructor experience is >10 years
        Predicate<Instructor> p5 = (i) -> {
        	return i.getYearsOfExperience() >10;
        };

        // is instructor teaches online and exprience is > 10 years
        System.out.println("---------------------");
        instructors.forEach((instructor) -> {
            if(p4.and(p5).test(instructor)){
                System.out.println("calling p4 p5"+instructor);
            }
        });
        
        //--------------------------------------------------------
        /*
        come to think of, short-hand way of define lambda variable in one line is not too bad...
        you can declare one line lambda in
        1>local method, use and throw away
        2>class method, use by the class or outside...
        3>what about lambda need multi-line use case? if lambda is not less than 5 line like if x else y, 
        then dont bother...  go with non-lambda...
        */
        /*
		When to Use Lambdas
		Functional Interfaces: If the behavior you want to change can be represented as a single abstract method (functional interface), lambdas can provide a concise and clear way to implement that behavior.
		Simpler Code: Lambdas can lead to more readable and maintainable code when replacing simple method overrides, especially if the overriding method is short and straightforward.
		Behavior Parameterization: If you want to pass different behaviors to a method without creating multiple subclasses, using lambdas can be a more flexible solution.
	
		When to Stick with Method Overrides
		Complex Logic: If the behavior being overridden involves complex logic or requires maintaining state, method overrides might be more appropriate for clarity and organization.
		Inheritance Hierarchies: If you're working within a deep inheritance hierarchy, method overrides can maintain the structure and allow for polymorphism that may be necessary for your design.
		Legacy Code: If you're working with legacy code that already uses method overriding extensively, introducing lambdas may complicate the codebase unnecessarily.
		Readability for New Developers: New developers or those unfamiliar with Java 8 features might find traditional method overriding easier to understand.
         */
        DoublePredicate p6 = (i) -> i<100.25;
        DoublePredicate p7 = (i) -> i>100.10;
        System.out.println(p6.and(p7).test(100.15));
        
        //all instructor who teaches online
        Predicate<Instructor> isOnlineCourse = (i) -> i.isOnlineCourses()==true;
        //instructor experience is >10
        Predicate<Instructor> isYearGreater10 = (i) -> i.getYearsOfExperience()>10;

        //Biconsumer print name and courses
        BiConsumer<String, List<String>> printNameAndCourses = (name, courses) ->
                System.out.println("calling printNameAndCourses, name is: " + name + " courses : " + courses);

        instructors.forEach((instructor) -> {
            if(isOnlineCourse.and(isYearGreater10).test(instructor))
            	printNameAndCourses.accept(instructor.getName(), instructor.getCourses());
        });
        
        BiPredicate<Boolean, Integer> isOnlineYearGreater10 = (online, experience) -> online==true && experience>10;
        instructors.forEach((instructor) -> {
            if(isOnlineYearGreater10.test(instructor.isOnlineCourses(), instructor.getYearsOfExperience()))
            	printNameAndCourses.accept(instructor.getName(), instructor.getCourses());
        }); 
        
        //--------------------------------------------------------
        /*
        Interface Consumer<T>
        T - the type of the input to the operation
        Interface BiConsumer<T,U>
        T - the type of the first argument to the operation
        U - the type of the second argument to the operation
        Interface IntFunction<R>
        R - the type of the result of the function
        ///////////////////////
        Interface Function<T,R> T=input R=return_result
        T - the type of the input to the function
        R - the type of the result of the function
        */
        Function<Integer, Double> sqrt = (i) -> { return Math.sqrt(i); };
        System.out.println("Square root of 64: " + sqrt.apply(64));
        System.out.println("Square root of 81: " + sqrt.apply(81));

        //Interface Function<T,R> T=input R=return_result
        Function<String,String> lowercaseFunction = (s) -> { return s.toLowerCase(); };
        System.out.println(lowercaseFunction.apply("PROGRAMMING"));
        
        //Interface Function<T,R> T=input R=return_result
        Function<String, String> concatFunction = (s) -> { return s.concat(" In Java"); };
        
        System.out.println(lowercaseFunction.andThen(concatFunction).apply("PROGRAMMING"));
        System.out.println("compose apply:"+lowercaseFunction.compose(concatFunction).apply("PROGRAMMING"));
        
        //readibility, i much prefer this way, then compose apply (which takes me abit to realize what it does...)
        System.out.println("apply apply:"+lowercaseFunction.apply(concatFunction.apply("PROGRAMMING")));
        
        Predicate<Instructor> isOnlineCourse2 = (i) -> { return i.isOnlineCourses()==true; };
        Function<List<Instructor>, Map<String,Integer>> mapFunction = (instructors2 -> {
            Map<String,Integer> map = new HashMap<>();
            instructors2.forEach((instructor2) -> {
                if(isOnlineCourse2.test(instructor2)) {
                    map.put(instructor2.getName(), instructor2.getYearsOfExperience());
                }
            });
            return map;
        });
        System.out.println(mapFunction.apply(Instructors.getAll()));
        
	} //runSection2
	
	public void runSection2_29(String[] args) {
		System.out.println("run instance public runSection2_29");
		
        Predicate<Instructor> isOnlineCourse = (i) -> i.isOnlineCourses()==true;
        BiFunction<List<Instructor>, Predicate<Instructor>, Map<String,Integer>> onlineCourseInstructorMap =
        		((instructors, instructorPredicate) -> {
        			/*
        			Interface Map<K,V>
        			K - the type of keys maintained by this map
					V - the type of values maintained by this map
        			 */
                    Map<String, Integer> map = new HashMap<>();
                    instructors.forEach(instructor -> {
                        if(instructorPredicate.test(instructor)){
                            map.put(instructor.getName(), instructor.getYearsOfExperience());
                        }
                    });
                    return map;
                });
        System.out.println(onlineCourseInstructorMap.apply(Instructors.getAll(), isOnlineCourse));
        
       //--------------------------------------------------------
       /*
       Interface UnaryOperator<T>
       T - the type of the operand and result of the operator.  type of input output
       */
       UnaryOperator<Integer> unary = i -> i * 10;
       System.out.println("UnaryOperator:"+unary.apply(100));

       //can be done via Function bit more typing...
       Function<Integer, Integer> function = i -> i*10;
       System.out.println("Function:"+function.apply(100));
       
       /*
       Interface Comparator<T>
       T - the type of objects that may be compared by this comparator
       int	compare(T o1, T o2)
       Interface BinaryOperator<T>
       T - the type of the operands and result of the operator
       R apply(T t, U u) 
       */
       BinaryOperator<Integer> combineIntOperator = (a,b) -> a + b;
       System.out.println(combineIntOperator.apply(2,4));

       /*
       Comparator is functional interface under java.util and not java.util.function..
       This is a functional interface and can therefore be used as the assignment target 
       for a lambda expression or method reference. 
       */
       Comparator<Integer> comparator = (a,b) -> a.compareTo(b);
       //maxBy(Comparator<? super T> comparator)
       BinaryOperator<Integer> maxBi = BinaryOperator.maxBy(comparator);
       System.out.println("maxBi:"+maxBi.apply(7,8));

       //minBy(Comparator<? super T> comparator)
       BinaryOperator<Integer> minBy = BinaryOperator.minBy(comparator);
       System.out.println("minBy:"+minBy.apply(7,8));
       
       //--------------------------------------------------------
       /*
       Supplier interface take no input but output something
       can be considered as factory method...
       Interface Supplier<T>
       T - the type of results supplied by this supplier
       */
       Supplier<Integer> supplier = () ->  { return (int) (Math.random() * 1000); }; //interesting no param is ()
       System.out.println(supplier.get());
       
       //--------------------------------------------------------
       /*
       Method references are a special type of lambda expressions.
	   There are four kinds of method references:
			Static methods
			Instance methods of particular objects
			Instance methods of an arbitrary object of a particular type
			Constructor
	   to be frank, i dont see much value added for method reference. it is diminishing return
	   situation. method reference will just make your code more spaghetti...
       */

       //method reference in Static methods use case
       /*
       // Lambda expression
       (args) -> Class.staticMethod(args);
       // Method reference
       Class::staticMethod;
       */
       List<String> messages = Arrays.asList("hello", "baeldung", "readers!");
       messages.forEach((word) -> { StringUtils.capitalize(word); });
       messages.forEach(StringUtils::capitalize);
       
       Function<Integer, Double> sqrt= a -> Math.sqrt(a);
       Function<Integer, Double> sqrt1 = Math::sqrt;
       
       //Instance methods of particular objects
       /*
       // Lambda expression
       (args) -> obj.instanceMethod(args);
       // Method reference
       obj::instanceMethod;
       */
       List<Integer> numbers = Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);
       numbers.sort((a, b) -> a.compareTo(b));
       System.out.println("sorted:"+numbers);
       
       List<Integer> numbers2 = Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);
       numbers2.sort(Integer::compareTo); 
       System.out.println("sorted:"+numbers2);
          
       Predicate<Instructor> p1 = instructor -> instructor.isOnlineCourses();
       Predicate<Instructor> p2 = Instructor::isOnlineCourses; 
       
       Function<String, String> lowercaseFunction = s -> s.toLowerCase();
       Function<String, String> lowercaseFunction2 = String::toLowerCase; 
       System.out.println("lowercaseFunction:"+lowercaseFunction.apply("HELLO"));
       System.out.println("lowercaseFunction:"+lowercaseFunction2.apply("HELLO"));
       
       //Instance methods of an arbitrary object of a particular type
       //Constructor
       /*
       // Lambda expression
       (args) -> new ClassName(args);
       // Method reference
       ClassName::new;
       */
       Function<Integer,Integer> factoryMethod = (i) -> new Integer(i);
       //can only be used with functional reference...
       Function<Integer,Integer> factoryMethod2 = Integer::new; // can only use with functional reference
       System.out.println("factoryMethod:"+factoryMethod.apply(10));
       System.out.println("factoryMethod:"+factoryMethod2.apply(10));
       
       //lambda version
       InstructorFactory instructorFactory = (name, yearsOfExperience, title, gender, onlineCourse, courses) -> new Instructor(name, yearsOfExperience, title, gender, onlineCourse, courses);
       Instructor instructor = instructorFactory.getInstructor("Mike", 10, "Software Developer"
    		   , "M", true, Arrays.asList("Java Programming", "C++ Programming", "Python Programming"));
       System.out.println("instructorFactory:"+instructor); 
    	       
       //method reference
       InstructorFactory instructorFactory2 = Instructor::new; // can only use with functional reference
       Instructor instructor2 = instructorFactory2.getInstructor("Mike", 10, "Software Developer"
    		   , "M", true, Arrays.asList("Java Programming", "C++ Programming", "Python Programming"));
       System.out.println("instructorFactory2:"+instructor2); 
       
       //--------------------------------------------------------
       /*
       scope of lambda = same as = scope of nested block
       nested block can only reference local variable...
       rules
       1>local name can not be same as param name
       2>cant modify local variable <-- strange i can modify local variable declared in nested block without error. 
         may be he is referring different scope local variable which obvious you cant reference...
       3>no restriction to class/instance variable. weird, contradict 'can only reference local variable'
       */
       Supplier<Integer> cantModifyLocal = () ->  { 
    	   int x = 10;
    	   x++;
    	   x=x+9;
    	   return x;
       };
       System.out.println("cantModifyLocal:"+cantModifyLocal.get());
       
       //class_level_var
       int local_level_var_ref_in_lambda =10; //local variable
       int local_level_var = 10;
       IntConsumer intConsumer = (a) -> System.out.println(a*10);
       List<Instructor> instructors7 = Instructors.getAll();
       instructors7.forEach(instructor7 -> {
    	   //you can reference local_level_var
    	   System.out.println("class_level_var"+instructor7 + " " +  local_level_var_ref_in_lambda);
    	   //but you cant reference local_level_var
    	   //local_level_var++;
           System.out.println("class_level_var"+instructor7 + " " +  class_level_var);
       });
       class_level_var++;
       //if you reference local_level_var_ref_in_lambda in lambda, then you cant modify too...
       //local_level_var_ref_in_lambda++;
       local_level_var++; // this is fine, because not ref in lambda...
	}
	
	public static void method_reference_turn_code_to_spaghetti(String[] args) {
		Predicate<Instructor> p1 = (i) -> { return i.getYearsOfExperience()>10; };  //lambda
	    Predicate<Instructor> p2 = JavaFeature::greaterThanTenYearsOfExperience;    //method reference
	    Instructors.getAll().forEach(instructor -> {
	    	if (p1.test(instructor)){
	    		System.out.println("p1 lambda (use 1 line of code) :"+instructor);
	    	}
	    	//method reference does only one thing turn code into spaghetti and complex to read...
	    	if (p2.test(instructor)){
	    		System.out.println("p2 method reference (use 6 line of code) :"+instructor);
	    	}
	    });
	}
	
	public static boolean greaterThanTenYearsOfExperience(Instructor instructor) {
	    if (instructor.getYearsOfExperience()>10)
	        return true;
	    return false;
	}

} //JavaFeature class

/*
intellj
1>FORGET Navigation >> Search Everywhere, use find icon
2>SUCKS you click on searched item without able to expand it and it disappeared...
2>ESC to remove the pesky popup...
 */


