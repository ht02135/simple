package simple.packages.feature;

import java.io.*;
import java.math.*;
import java.net.*;
import java.net.http.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.security.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.zip.*;

import simple.packages.*;
import simple.packages.datetime.*;
import simple.packages.stream.*;
import simple.packages.feature.*;

import java.sql.*;

public class MoreJavaFeature {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exec(args);
	}

	static public void exec(String[] args) {
		System.out.println("run static public exec");
		MoreJavaFeature me = new MoreJavaFeature();

		//https://medium.com/java-and-beyond/modern-java-an-in-depth-guide-from-version-8-to-21-by-akiner-alkan-f89b50e13c72
		//Java 8 to Java 21
		try {
			me.runJava8(args);
			me.runJava9(args);
			me.runJava10(args);
			me.runJava11(args);
			me.runJava12(args);
			me.runJava13(args);
			me.runJava14(args);
			
			/*
			Java 15
			Garbage Collector Updates
			*/
			
			me.runJava16(args);
			me.runJava17(args);
			me.runJava21(args);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException re) { //unchecked RuntimeException
        	System.out.println("runchecked exception RuntimeException re="+re);
        	/*
        	You can retrieve these suppressed exceptions by calling the 
    	    Throwable.getSuppressed method from the exception thrown by the try block
        	*/
        	System.out.println("runchecked exception RuntimeException re.getSuppressed()="+re.getSuppressed());
        	re.printStackTrace();
        } catch (Exception e) { //checked Exception
        	System.out.println("checked exception Exception re="+e);
        	/*
        	You can retrieve these suppressed exceptions by calling the 
    	    Throwable.getSuppressed method from the exception thrown by the try block
        	*/
        	System.out.println("checked exception Exception e.getSuppressed()="+e.getSuppressed());
        	e.printStackTrace();
        }
		
	} // exec
	
    public void runJava8(String[] args) {
		System.out.println("run instance public runJava8");
		
		//---------------------------------
		//Lambda Expression
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello, world!");
			}
		};
		Runnable lambdaRunnable = () -> {
			System.out.println("Hello, world!");
		};
		
		//---------------------------------
		//Stream API
		List<String> fruits = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");
		// Using Stream API to filter and transform elements
        List<String> result = fruits
        	.stream()
            .filter(fruit -> fruit.length() > 5)
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println(result); // Output: [BANANA, ORANGE]
				  
		//---------------------------------
		//Method Reference
		String[] words = {"apple", "banana", "cherry", "date", "elderberry"};

        // Using explicit lambda expression for sorting
        Arrays.sort(words, (a, b) -> a.compareToIgnoreCase(b));
        // Using method reference to sort the array
        Arrays.sort(words, String::compareToIgnoreCase);
        
		//---------------------------------
        /*
        Default Methods
        Default methods are methods defined in an interface with an implementation
        */
        Person person = new Person();
        person.greet(); // Will use the default method implementation
        
        //---------------------------------
        //Date and Time API
        // Current date, time, and date-time
        //LocalDate, LocalTime, LocalDateTime
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current Date: " + currentDate);
        System.out.println("Current Time: " + currentTime);
        System.out.println("Current Date-Time: " + currentDateTime);
        // Formatting and parsing
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(dateFormatter);
        System.out.println("Formatted Date: " + formattedDate);
        String dateString = "2023-08-01";
        LocalDate parsedDate = LocalDate.parse(dateString, dateFormatter);
        System.out.println("Parsed Date: " + parsedDate);
    
		System.out.println("exit instance public runJava8");
    }
    
    public void runJava9(String[] args) {
		System.out.println("run instance public runJava9");
		
		//------------------------------
		/*
		Java Module System
		1>Module: A module is a self-contained unit of code that encapsulates its implementation
		  details, dependencies, and provides a clear API. Modules group related packages and 
		  resources together, providing a higher level of abstraction.
		2>Module Descriptor (module-info.java): Each module has a module descriptor, specified 
		  in a file named module-info.java. This descriptor defines the module’s name, 
		  dependencies, exported packages, and other module-related settings.
		3>Module Path: The module path is a new way of specifying dependencies for a Java 
		  application. It replaces the traditional classpath. Modules are resolved based on 
		  their dependencies and explicit requirements, which helps prevent classpath conflicts.
		*/
		
		//------------------------------
		/*
		Try-with-resources
		The try-with-resources statement is used in Java to automatically close resources that 
		are used within a try block
		*/
		
		//------------------------------
		/*
		Private Interface Methods
		Private interface methods are a feature introduced in Java 9 to allow interfaces 
		to have private methods. These methods are intended to be used within the 
		interface itself
		*/
		var myClass = new MyClass();
		// call public publicMethod which call private privateMethod
		myClass.publicMethod(); 
		
		System.out.println("exit instance public runJava9");
    }
    
    //Try-with-resources
    public static void writeToFileZipFileContents(String zipFileName,
    	String outputFileName) throws IOException {

    	Charset charset = StandardCharsets.US_ASCII;
    	Path outputFilePath = Paths.get(outputFileName);
    	
    	// Open zip file and create output file with 
    	// try-with-resources statement
    	/*
    	If an exception is thrown 
    	1>from the try block 
    	2>and one or more exceptions are thrown from the try-with-resources statement
    	3>You can retrieve these suppressed exceptions by calling the 
    	  Throwable.getSuppressed method from the exception thrown by the try block
    	*/
    	try (
    		/*
    		comment out, because they are not AutoCloseable
    		The resource type Charset does not implement java.lang.AutoCloseable
    		 */
    	    //Charset charset = StandardCharsets.US_ASCII;
    	    //Path outputFilePath = Paths.get(outputFileName);

    		//ZipFile implements ZipConstants, Closeable
    		//Writer implements Appendable, Closeable, Flushable
    		ZipFile zf = new ZipFile(zipFileName);
    		BufferedWriter writer = Files.newBufferedWriter(outputFilePath, charset)
    	) {
    		// Enumerate each entry
    		for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
    			// Get the entry name and write it to the output file
    			String newLine = System.getProperty("line.separator");
    			String zipEntryName = ((ZipEntry)entries.nextElement()).getName() + newLine;
    			writer.write(zipEntryName, 0, zipEntryName.length());
    		} //for
    		
    		//they are Closeable, so NO need to explicit call it...
    		//zf.close();
    		//writer.close();
    	} catch (RuntimeException re) { //unchecked RuntimeException
        	System.out.println("runchecked exception RuntimeException re="+re);
        	/*
        	You can retrieve these suppressed exceptions by calling the 
    	    Throwable.getSuppressed method from the exception thrown by the try block
        	*/
        	System.out.println("runchecked exception RuntimeException re.getSuppressed()="+re.getSuppressed());
        	re.printStackTrace();
        } catch (Exception e) { //checked Exception
        	System.out.println("checked exception Exception re="+e);
        	/*
        	You can retrieve these suppressed exceptions by calling the 
    	    Throwable.getSuppressed method from the exception thrown by the try block
        	*/
        	System.out.println("checked exception Exception e.getSuppressed()="+e.getSuppressed());
        	e.printStackTrace();
        }
    }
    
    //Try-with-resources
    public static void viewTable(Connection con) throws SQLException {

        String query = "select COF_NAME, SUP_ID, PRICE, SALES, TOTAL from COFFEES";

        //only AutoCloseable obj can be in try (AutoCloseable obj)
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + ", " + supplierID + ", " + 
                	price + ", " + sales + ", " + total);
            }
        } catch (SQLException e) {
        	System.out.println("e=" + e);
        } catch (RuntimeException re) { //unchecked RuntimeException
        	System.out.println("runchecked exception RuntimeException re="+re);
        	/*
        	You can retrieve these suppressed exceptions by calling the 
    	    Throwable.getSuppressed method from the exception thrown by the try block
        	*/
        	System.out.println("runchecked exception RuntimeException re.getSuppressed()="+re.getSuppressed());
        	re.printStackTrace();
        } catch (Exception e) { //checked Exception
        	System.out.println("checked exception Exception re="+e);
        	/*
        	You can retrieve these suppressed exceptions by calling the 
    	    Throwable.getSuppressed method from the exception thrown by the try block
        	*/
        	System.out.println("checked exception Exception e.getSuppressed()="+e.getSuppressed());
        	e.printStackTrace();
        }
    }
	
    public void runJava10(String[] args) {
		System.out.println("run instance public runJava10");
		
		//---------------------------------
		//Local Variable Type Inference
		var name = "John Doe"; // Infers String type
		var age = 30; // Infers int type
		var salary = 50000.0; // Infers double type
		   
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Salary: " + salary);
		
		System.out.println("exit instance public runJava10");
    }
    
    public void runJava11(String[] args) {
		System.out.println("run instance public runJava11");
		
		//---------------------------------
		//Local Variable Type in Lambda Expressions
		List<String> names = new ArrayList<>();
		names.add("Alice");
		names.add("Bob");
		names.add("Charlie");
		names.forEach((var name) -> {
		  System.out.println("Hello, " + name);
		});
		
		System.out.println("exit instance public runJava11");
    }
    
    public void runJava12(String[] args) throws IOException {
		System.out.println("run instance public runJava12");
		
		//---------------------------------
		//String Indent and Transform
		String original = "Hello\nWorld";
		String indented = original.indent(4);
		System.out.println(indented);
		// Output:
		//     Hello
		//     World
		
		//----------------------------------
		//Transform method applies a transformation function to the string and returns the result.
		original = "Hello";
		String transformed = original.transform(s -> s.toUpperCase());
		System.out.println(transformed); // Output: HELLO
		
		//-------------------------------------
		/*
		Files Mismatch
		Files.mismatch method is part of the java.nio.file package in Java, and it is used 
		to compare the content of two files for differences. It’s specifically designed to 
		efficiently determine whether two files have differing content without needing to 
		read the entire contents of the files.
		*/
		Path filePath1 = Files.createTempFile("file1", ".txt");
		Path filePath2 = Files.createTempFile("file2", ".txt");
		Files.writeString(filePath1, "I love Java");
		Files.writeString(filePath2, "I love Technology");
		long mismatch = Files.mismatch(filePath1, filePath2); // It returns 7
		
		System.out.println("exit instance public runJava12");
    }
    
    public void runJava13(String[] args) throws IOException {
		System.out.println("run instance public runJava13");
		
		//------------------------------
		//TextBlocks, start with """ and ends with """
        String html = """
        	<html>
        		<body>
                    <p>"Java, Programming"</p>
                </body>
            </html>
        """;
        System.out.println("html="+html);
		
		System.out.println("exit instance public runJava13");
    }
    
    public void runJava14(String[] args) throws IOException {
		System.out.println("run instance public runJava13");
		
		//------------------------------
		/*
		Yield Keyword
		Java 12 introduces expressions to Switch statement and
		*/
		var day = "Saturday";
		var result = switch (day) {
			case "Monday", "Tuesday", "Wednesday","Thursday", "Friday": yield "Weekday";
			case "Saturday", "Sunday": yield "Weekend";
			default: yield "Invalid day.";
		};
		System.out.println("result="+result);

		
		System.out.println("exit instance public runJava14");
    }
    
    public void runJava16(String[] args) throws IOException {
		System.out.println("run instance public runJava16");
		
		//------------------------------
		//Pattern Matching for instanceof
		//Java 14 introduces instanceof operator to have type test pattern
		// Traditional instanceof
		var animal = new Person();
		if (animal instanceof Person) {
			Person cat = (Person) animal;
		    cat.greet();
		}
		// Modern instanceof
		if (animal instanceof Person cat) { //<-- perfect example of diminishing return...
		    cat.greet();
		}
		
		//--------------------------------
		/*
		Records
		Java 14 introduces a new class type record as preview feature to facilitate 
		creation of immutable data objects
		*/
		/*
		Hung thought
		1>i wasted 3hours google online for example.  it is all incomplete bit/pieces
		*/
	
		System.out.println("exit instance public runJava16");
    }
    
    public void runJava17(String[] args) throws IOException {
		System.out.println("run instance public runJava17");
		
		//--------------------------------------
		/*
		Sealed class is a feature introduced in Java 15 to enhance the control over 
		class inheritance and ensure that only specific subclasses can extend it. 
		//////////////////////////////
		1>another diminishing return.  WHAT IS THE POINT????
		2>just use final to control class hierarchy....
		*/
		var sealed = new SealedPerson();		// permits NonSealedPerson, EsperPerson
		var nonSealed = new NonSealedPerson(); 	// non-sealed
		var esper = new EsperPerson();			// final extend SealedPerson
		
		System.out.println("exit instance public runJava17");
    }
    
    public void runJava21(String[] args) throws IOException {
		System.out.println("run instance public runJava21");
		
		//----------------------------------------------
		/*
		Virtual Threads
		In previous threading model, Java’s threads directly correspond to operating system (OS) 
		threads, resulting in limitations on the number of threads that can be created due to 
		OS constraints. In the traditional threading model, excessive thread creation can strain 
		the OS and incur high costs, particularly for short-lived threads.

		Java 21 introduces virtual threads, offering mapping between virtual threads and OS 
		threads, allowing for theoretically unlimited virtual thread creation. This innovation 
		addresses the limitations of traditional threading models, enabling the creation of
		numerous threads to meet the demands of high-throughput server applications. With 
		virtual threads, the previous constraint on thread creation is eliminated, enabling 
		the continuation of the thread-per-request style commonly used in server applications.

		An example demonstrates the use of virtual threads compared to OS/platform threads. 
		By utilizing ExecutorService to execute 500,000 thread per tasks, the JDK efficiently 
		manages the execution on a limited number of carrier and OS threads, allowing 
		developers to write concurrent code effortlessly. 
		*/
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
		    IntStream.range(0, 20).forEach(i -> {
		        executor.submit(() -> {
		            Thread.sleep(Duration.ofSeconds(1));
		            return i;
		        });
		    });
		    // executor.close() is called implicitly, and waits
		} catch (RuntimeException re) { //unchecked RuntimeException
        	System.out.println("runchecked exception RuntimeException re="+re);
        	System.out.println("runchecked exception RuntimeException re.getSuppressed()="+re.getSuppressed());
        	re.printStackTrace();
        } catch (Exception e) { //checked Exception
        	System.out.println("checked exception Exception re="+e);
        	System.out.println("checked exception Exception e.getSuppressed()="+e.getSuppressed());
        	e.printStackTrace();
        }
		
		//--------------------------------------------
		//SequencedCollection interface 
		/*
		interface SequencedCollection<E> extends Collection<E> {
    		// new method
    		SequencedCollection<E> reversed();
    		// methods promoted from Deque
    		void addFirst(E);
    		void addLast(E);
    		E getFirst();
    		E getLast();
    		E removeFirst();
    		E removeLast();
		}
		*/
		
		//-------------------------------------------
		//String Templates
		//They provide a mechanism to create template expressions that can be rendered into strings.
		String codingLanguage = "Java";
		String sentence = "${codingLanguage} is awesome";
		System.out.println(sentence);
		// Output:
		//   Java is awesome!
		
		System.out.println("exit instance public runJava21");
    }
}
