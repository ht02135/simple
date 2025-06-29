package simple.packages.datetime;

import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.math.*;
import java.net.*;
import java.net.http.*;
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

import simple.packages.*;
import simple.packages.datetime.*;
import simple.packages.stream.*;

import java.sql.*;

public class DateTimeExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exec(args);
	}

	static public void exec(String[] args) {
		System.out.println("run static public exec");
		DateTimeExample me = new DateTimeExample();
		
		me.runSection14(args);
		me.isJShellUseless(args);
		try {
			me.runSection16(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		me.runVar(args);
		me.runVarWithLamda(args);
		me.runHttpClient(args);
		me.runSection25(args);
		
	} // exec
	
	/*
	What’s wrong with the old Java Date API?
	1>A Date instance represents an instant in time, not a date. Importantly, that means:
	a>It doesn’t have a time zone.
	b>It doesn’t have a format.
	c>It doesn’t have a calendar system. 
	2>other problems are:
	a>It rates years as two digits since 1900.
	b>Months are zero indexed (0 – January, 11 – December).
	c>All classes in this old API are mutable. As a result, any time you want to give a 
	  date back (say, as an instance structure) you need to return a clone of that date
	d>Date represents a DateTime, but in order to defer to those in SQL land, there’s 
	  another subclass java.sql.Date, which represents a single day (though without a 
	  timezone associated with it).
	e>It implicitly uses the system-local time zone in many places – including toString() 
	  – which confuses many developers.
	/////////////////////////////////////
	New Date and Time API for Java
	1>Immutable-value classes.
	
	*/
	
    public void runSection14(String[] args) {
		System.out.println("run instance public runSection14");

        //OLD Date and DateFormat API
		/*
		SimpleDateFormat df.format(dateObj)=2025-06-23
		SimpleDateFormat df.format(dateObj)=08:06:43
		Calendar.getInstance().getTime()=Mon Jun 23 08:06:43 EDT 2025
		*/
		System.out.println("=========================");
		
        Date dateObj = new Date();
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("SimpleDateFormat df.format(dateObj)=" + df.format(dateObj));
        
        df = new SimpleDateFormat("HH:mm:ss");
        System.out.println("SimpleDateFormat df.format(dateObj)=" + df.format(dateObj));

        System.out.println("Calendar.getInstance().getTime()="+Calendar.getInstance().getTime()); 
        
        //-------------------------------
        //NEW Date/Time API with diminishing returning. (better? i dont see the point)
        /*
        localDate=2025-06-23
		localTime=08:06:43.787408700
		localDateTime=2025-06-23T08:06:43.787408700
        */
        
        //LocalDate
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate=" + localDate);
        //LocalTime
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime=" + localTime);
        //LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime=" + localDateTime);
        
        //-------------------------------------------------
        //additional LocalDate lab
        System.out.println("=========================");
        
        localDateTime = LocalDateTime.now();
        System.out.println("localDateTime=" + localDateTime);
        
        //of
        localDateTime = LocalDateTime.of(2022, 1, 12, 12,12,12);
        System.out.println("localDateTime=" + localDateTime);
        
        localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("localDateTime=" + localDateTime);

        //get
        System.out.println("localDateTime.getHour()=" + localDateTime.getHour());
        System.out.println("localDateTime.getMonth()=" + localDateTime.getMonth());
        System.out.println("localDateTime.getMinute()=" + localDateTime.getMinute());
        System.out.println("localDateTime.getSecond()=" + localDateTime.getSecond());
        System.out.println("localDateTime.get()=" + localDateTime.get(ChronoField.MONTH_OF_YEAR));

        //Modify
        System.out.println("localDateTime.plusYears(3)=" + localDateTime.plusYears(3));
        System.out.println("localDateTime.plusHours(4)=" + localDateTime.plusHours(4));
        System.out.println("localDateTime.plusMinutes(60)=" + localDateTime.plusMinutes(60));
        System.out.println("localDateTime.with(ChronoField)=" + 
        	localDateTime.with(ChronoField.HOUR_OF_DAY,3));
        System.out.println("localDateTime.with(LocalTime)=" + 
        	localDateTime.with(LocalTime.MIDNIGHT));
        
        //--------------------------------------------------
        //more LocalDate lab modification
        /*
        1>localDate, localTime, localDateTime are IMMUTABLE
        2>these plusDays() or minusDays() does not modify existing obj
          instead new instance of obj with modified value is created/returned.
          to track those new instance obj, you need to assign it to var...
        */
        
        //4 days from now
        localDate = localDate.plusDays(4);
        System.out.println("localDate = " + localDate);
        System.out.println("localDate.plusDays(4) = " + localDate.plusDays(4));
        
        localDate = localDate.now(); 
        System.out.println("localDate = " + localDate);
        System.out.println("localDate.plusMonths(2) = " + localDate.plusMonths(2));
        System.out.println("localDate.plusYears(2) = " + localDate.plusYears(2));
        System.out.println("localDate.minusDays(10) = " + localDate.minusDays(10));
        System.out.println("localDate.withYear(2023) = " + localDate.withYear(2023));
        //
        System.out.println("localDate.with(ChronoField) = "
                + localDate.with(ChronoField.YEAR, 2025));

        System.out.println("localDate.with(TemporalAdjusters) = "
                + localDate.with(TemporalAdjusters.lastDayOfMonth()));
        
        //-------------------------------------------------
        //more LocalTime lab
        System.out.println("=========================");
        
        localTime = LocalTime.now();
        System.out.println("localTime=" + localTime);
        
        localTime = LocalTime.of (15, 18);
        System.out.println("localTime=" + localTime);
        
        localTime = LocalTime.of(15, 18, 22);
        System.out.println("localTime=" + localTime);

        localTime = LocalTime.of(15,18,23,22222222);
        System.out.println("localTime=" + localTime);

        //get
        System.out.println("localTime.getHour()=" + localTime.getHour());
        System.out.println("localTime.getMinute()=" + localTime.getMinute());
        System.out.println("localTime.getSecond()=" + localTime.getSecond());
        System.out.println("localTime.getNano()=" + localTime.getNano());

        System.out.println("localTime.get(ChronoField)="
                + localTime.get(ChronoField.SECOND_OF_DAY));
        System.out.println("localTime.get(ChronoField)=" +
                localTime.get(ChronoField.MINUTE_OF_DAY));
        
        /*
        turn on eclipse context asist
        1>turn on Java >> Editor >> Content Assist >> Auto activation triggers for Java
        2>In the Auto activation triggers for Java paste the following value
        .@(#&$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ
        3>Java -> Editor -> Code Minings -> [x] Show method parameter names
        */
        
        //-------------------------------------------
        //more LocalTime modify lab
        System.out.println("=========================");
        
        localTime = LocalTime.now();
        System.out.println("localTime=" + localTime);
        
        System.out.println("localTime.plusHours(2)=" + localTime.plusHours(2));
        System.out.println("localTime.plusMinutes(22)=" + localTime.plusMinutes(22));
        System.out.println("localTime.plusSeconds(30)=" + localTime.plusSeconds(30));
        System.out.println("localTime.plusNanos(222222)=" + localTime.plusNanos(222222));
        System.out.println("localTime.minusHours(2)=" + localTime.minusHours(2));
        System.out.println("localTime.minus(2,ChronoUnit.HOURS)="
                + localTime.minus(2, ChronoUnit.HOURS));

        /*
        localTime=09:51:50.995850100
        localTime.plusHours(2)=11:51:50.995850100
		localTime.plusMinutes(22)=10:13:50.995850100
		localTime.plusSeconds(30)=09:52:20.995850100
		localTime.plusNanos(222222)=09:51:50.996072322
		localTime.minusHours(2)=07:51:50.995850100
		localTime.minus(2,ChronoUnit.HOURS)=07:51:50.995850100
		localTime.with(LocalTime.MIDNIGHT)=00:00
        */
        System.out.println("localTime.with(LocalTime.MIDNIGHT)="
                + localTime.with(LocalTime.MIDNIGHT));
        System.out.println("localTime.with(ChronoField.HOUR_OF_DAY,4)="
                + localTime.with(ChronoField.HOUR_OF_DAY,4));
        
        //------------------------------------------
        //more LocalDateTime lab
        System.out.println("=========================");
        
        localDateTime = LocalDateTime.now();
        System.out.println("localDateTime=" + localDateTime);
        
        //of
        localDateTime = LocalDateTime.of(2022, 1, 12, 12,12,12);
        System.out.println("localDateTime=" + localDateTime);
        
        localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("localDateTime=" + localDateTime);

        //get
        System.out.println("localDateTime.getHour()=" + localDateTime.getHour());
        System.out.println("localDateTime.getMonth()=" + localDateTime.getMonth());
        System.out.println("localDateTime.getMinute()=" + localDateTime.getMinute());
        System.out.println("localDateTime.getSecond()=" + localDateTime.getSecond());
        System.out.println("localDateTime.get()="
                + localDateTime.get(ChronoField.MONTH_OF_YEAR));

        //Modify
        /*
        localDateTime=2025-06-23T09:51:50.996823100
        localDateTime.plusYears(3)=2028-06-23T09:51:50.996823100
		localDateTime.plusHours(4)=2025-06-23T13:51:50.996823100
		localDateTime.plusMinutes(60)=2025-06-23T10:51:50.996823100
		localDateTime.with(ChronoField.HOUR_OF_DAY,3)=2025-06-23T03:51:50.996823100
		localDateTime.with(LocalTime.MIDNIGHT)=2025-06-23T00:00
        */
        System.out.println("localDateTime.plusYears(3)=" + localDateTime.plusYears(3));
        System.out.println("localDateTime.plusHours(4)=" + localDateTime.plusHours(4));
        System.out.println("localDateTime.plusMinutes(60)=" + localDateTime.plusMinutes(60));
        System.out.println("localDateTime.with(ChronoField.HOUR_OF_DAY,3)="
                + localDateTime.with(ChronoField.HOUR_OF_DAY,3));
        System.out.println("localDateTime.with(LocalTime.MIDNIGHT)="
                + localDateTime.with(LocalTime.MIDNIGHT));

        //------------------------------------------
        //Duration only works for LocalTime and NOT LocalDateTime
        System.out.println("=========================");
        
        localDateTime = LocalDateTime.now();
        System.out.println("localDateTime.toLocalDate()=" + localDateTime.toLocalDate());
        System.out.println("localDateTime.toLocalTime()=" + localDateTime.toLocalTime());
        
        localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("localDateTime=" + localDateTime);
		
        //duration between 2 localdatetime instances
        localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.now().plusHours(2);
        
       //Duration only works for LocalTime and NOT LocalDateTime
        Duration duration = Duration.between(localDateTime, localDateTime1);
        System.out.println("duration.toHours()=" + duration.toHours());
        
        duration = Duration.ofHours(3);
        System.out.println("duration.toMinutes()=" + duration.toMinutes());

        LocalTime localTime1 = LocalTime.now();
        LocalTime localTime2 = LocalTime.now().plusMinutes(60);
        
        //Duration only works for LocalTime and NOT LocalDateTime
        duration = Duration.between(localTime1, localTime2);
        System.out.println("duration=" + duration.toMinutes());

        /*
        comment this out, because you CANT do duration between 'LocalDate'
        you will run into this
        Exception in thread "main" java.time.temporal.UnsupportedTemporalTypeException: Unsupported unit: Seconds
			at java.base/java.time.LocalDate.until(LocalDate.java:1636)
			at java.base/java.time.Duration.between(Duration.java:489)
			at simplePackage.datetime.DateTimeExample.runSection14(DateTimeExample.java:264)
         */
        /*
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.now().plusDays(1);
        duration = Duration.between(localDate1, localDate2);
        System.out.println("duration=" + duration);
        */
        
        //------------------------------------------
        //zone
        System.out.println("=========================");
        
        //This method will return instant based on system UTC clock
        Instant timestamp = Instant.now();
        System.out.println("timestamp.getNano()=" + timestamp.getNano());
        
        Instant timestamp2 = Instant.now().plusSeconds(3600); 
        duration = Duration.between(timestamp2,timestamp);
        System.out.println("duration.toSeconds()=" + duration.toSeconds());

        ZoneId.getAvailableZoneIds().stream()
    	.forEach(System.out::println);
        
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(timestamp2, ZoneId.systemDefault());
        System.out.println("localDateTime2=" + localDateTime2);
        
        /*
        localDateTime2=2025-06-23T12:25:18.862488200
		ZonedDateTime.of() Europe/London=2025-06-23T12:25:18.862488200+01:00[Europe/London]
		ZonedDateTime.of() America/New_York=2025-06-23T12:25:18.862488200-04:00[America/New_York]
		ZonedDateTime.now() Europe/London=2025-06-23T16:25:18.867681300+01:00[Europe/London]
		ZonedDateTime.now() America/New_York=2025-06-23T11:25:18.867681300-04:00[America/New_York]
        */
        System.out.println("ZonedDateTime.of() Europe/London=" + 
            ZonedDateTime.of(localDateTime2, ZoneId.of("Europe/London")));
        System.out.println("ZonedDateTime.of() America/New_York=" + 
            ZonedDateTime.of(localDateTime2, ZoneId.of("America/New_York")));

        System.out.println("ZonedDateTime.now() Europe/London=" + 
        	ZonedDateTime.now(ZoneId.of("Europe/London")));
        System.out.println("ZonedDateTime.now() America/New_York=" + 
        	ZonedDateTime.now(ZoneId.of("America/New_York")));

        //------------------------------------------
        System.out.println("=========================");
        localDateTime = LocalDateTime.now();
        System.out.println("localDateTime=" + localDateTime);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("America/New_York"));
        System.out.println("zonedDateTime=" + zonedDateTime);
        System.out.println(localDateTime.atOffset(ZoneOffset.ofHours(-10)));
		
        dateObj = new Date();
        localDateTime = dateObj.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime=" + localDateTime);
        
        //need to be specific to avoid conflict...
        java.sql.Date dateSql = new java.sql.Date(System.currentTimeMillis());
        localDate = dateSql.toLocalDate();
        System.out.println("localDate=" + localDate);
        
        //-------------------------------------------------------
        //additional
        //Parsing date and time
        String str = "2017-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        
        dateTime = LocalDateTime.of(2017, Month.APRIL, 8, 12, 30);
        String formattedDateTime = dateTime.format(formatter); // "2017-04-08 12:30"
        
		System.out.println("exit instance public runSection14");
    }
    
    public void isJShellUseless(String[] args) {
		System.out.println("run instance public isJShellUseless");
	
        //------------------------------------------
		//JShell part of JDK9 now + REPL
		//dont bother it is pointless. 
		//i can do better by create XYZ CLASS with main method testing the code with printline
		//and quickly run thru 'mvn clean install -DskipTests=true' and launch the app and see result
		
		System.out.println("exit instance public isJShellUseless");
    }
    
    public void runSection16(String[] args) throws IOException {
		System.out.println("run instance public runSection16");
		
		//---------------------------------------------------
		//java9 factory methods create unmodifiable list, set, map
		
        //OLD way
        List<String> names = new ArrayList(); 
        names.add("Syed"); 
        names.add("Mike"); 
        names.add("Jenny"); 
        names = Collections.unmodifiableList(names);
        System.out.println("names = " + names);
        
        //NEW way with java9 factory method
        List<String> names2 = List.of("Syed", "Mike", "Jenny");
        System.out.println("names2 = " + names2);
        Set<String> set = Set.of("Syed", "Mike", "Jenny");
        System.out.println("set = " + set);
        Map<String, String> map = Map.of("Grade1", "Syed", "Grade2", "Mike");
        System.out.println("map = " + map);
        
        //test modify the list will run into exception, so comment these out
        // names2.add("Gene");
        // set= Set.of("Syed", "Syed", "Mike");
        // names.sort(Comparator.naturalOrder());	
        
        //-----------------------------------
        //try-with-resource
        /*
        1>resource = object (AutoCloseable,Closeable) must be closed when program finish
        2>try-with-close make sure that object is closed at end of statement
        */
        //java 8 
        Reader inputString = new StringReader("Don't cut any corners");
        BufferedReader bufferedReader = new BufferedReader(inputString);
        try(BufferedReader bufferedReader1=bufferedReader){
            System.out.println("bufferedReader1.readLine() = "
                    + bufferedReader1.readLine());
        }

        //java 9
        Reader inputString2 = new StringReader("Hang in there");
        BufferedReader bufferedReader2 = new BufferedReader(inputString2);
        try(bufferedReader2){
            System.out.println("bufferedReader2.readLine() = "
                    + bufferedReader2.readLine());
        }
        
        //-----------------------------------
        
		System.out.println("exit instance public runSection16");
    }
    
    public void runVar(String[] args) {
		System.out.println("run instance public runVar");
	
        //------------------------------------------
        //var type with example
        //java10 you can drop explicit type, depend on type inference

		
        var name = "Mike";
        System.out.println("name = " + name);

        var dateTime = LocalDateTime.now(); // LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("dateTime = " + dateTime);

        HashMap<Integer, String> map1 = new HashMap<>();
        var map2 = new HashMap<Integer, String>();

        //integer array
        int[] numbers1 = {1,2,3,4,5};
        var numbers2 = new int[] {1,2,3,4,5};

        //list
        List<String> names1 = new ArrayList<>(); 
        names1.add("Syed"); 
        names1.add("Mike");
        System.out.println("names1 = " + names1);
        
        var names2 = new ArrayList<>(); 
        names2.add("Gene"); 
        names2.add(1.0001);
        names2.add(1);
        System.out.println("names2 = " + names2);
        
        var integers = List.of(1,2,3,4,5);
        integers.forEach(System.out::println);
        
        //you will get cant convert long to int, so comment out
        //int num = 999999999999999L; 
        var num = 99999999999999999.99999; 
        
        var result = 9/2;
        System.out.println("result = " + result);
        
        var result1 = 9.0/2;
        System.out.println("result1 = " + result1);
        
        result1 = 11d/2d;
        System.out.println("result1 = " + result1);

        var idiomOfTheDay = "A blessing in disguise";
        printString(idiomOfTheDay);
        
        //------------------------------------------
        /*
        java10- garbage collection improvement
        1>Parallel Full GC for G1
          parallel full GC for GC1
        2>introduction of Garbage Collector Interface
        */
        
        //------------------------------------------
        /*
        epsilon GC
        1>introduced java11
        2>epsilon is no-operation GC
        3>it only allocate memory
        4>it does not clear heap of unused obj
        5>when jvm run out of memory, hello outofmemory
        6>following JVM options:
        -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC
        ///////////////////////////
        7>Pros and cons of Epsilon GC
		So, what's so great about Epsilon GC? DevOps professionals who deploy applications and 
		microservices to JVM with it get the lowest memory management latency.
		The drawback? When the JVM reaches the limits of its allocated memory, it will crash 
		and burn with an OutOfMemoryError, terminating the JVM. Once the Java heap is exhausted, 
		no further object allocations are possible. And since no memory reclamation is allowed, 
		the JVM will fail with an OutOfMemoryError written to the logs and a heap dump written 
		to the file system.
		/////////////////////////////
		8>When to use Epsilon GC to be honest, use cases are joke even for pure test performance...
		-performance testing routines where GC routines skew the gathered metrics;
		-extremely short-lived routines where the JVM is terminated on exit;
		-for ultra-latency sensitive applications where the memory footprint is well-established;
		-for garbage-free applications that perform primarily iterative and conditional logic;
		-for highly clustered Java-based microservice deployments on Kubernetes where intermittent JVM failures are unlikely to cause a service degradation; and
		-for servers that allocate immense quantities of memory at startup and then cycle down at the end of the workday.
        */
        
		System.out.println("exit instance public runVar");
    }

    public static void runVarWithLamda(String[] args){
    	System.out.println("exit instance public runVarWithLamda");

        //-------------------------------------------
        //adding var to lambda
        // with java11
        // (var x, var y) -> x + y;
        // (var x, int y) -> x + y;  // you CANT mix implicit with explicit
        
    	//------------------------------------------
        //type
        List<Instructor> instructors = Instructors.getAll();
        Predicate<Instructor> experiencePredicate = (Instructor s) -> s.getYearsOfExperience()>10; 
        instructors.forEach((Instructor instructor) -> {
            if (experiencePredicate.test(instructor)) {
            	String result = instructor.getName();
                System.out.println("type result = " + result);
            }
        });

        BiFunction<Integer,Integer,Integer> sum = (Integer x, Integer y) -> x + y;
        System.out.println("type sum.apply(2,4) = " + sum.apply(2,4));

    	//------------------------------------------
        //var
        var instructors2 = Instructors.getAll();
        //need lambda explicit type
        //var experiencePredicate2 = (var s) -> s.getYearsOfExperience()>10; 
        //var experiencePredicate2 = (Instructor s) -> s.getYearsOfExperience()>10;
        Predicate<Instructor> experiencePredicate2 = (var s) -> s.getYearsOfExperience()>10; 
        instructors2.forEach((var instructor) -> {
            if (experiencePredicate2.test(instructor)) {
            	String result = instructor.getName();
                System.out.println("var result = " + result);
            }
        });
        
        BiFunction<Integer,Integer,Integer> sum2 = (var x, var y) -> x + y;
        System.out.println("var sum2.apply(2,4) = " + sum2.apply(2,4));
        
    	//------------------------------------------
        //unless i am mistaken, you dont even need to specify var for lambda expression
        var instructors3 = Instructors.getAll();
        //need lambda explicit type
        //var experiencePredicate2 = (var s) -> s.getYearsOfExperience()>10; 
        //var experiencePredicate2 = (Instructor s) -> s.getYearsOfExperience()>10;
        Predicate<Instructor> experiencePredicate3 = (s) -> s.getYearsOfExperience()>10; 
        instructors2.forEach((instructor) -> {
            if (experiencePredicate3.test(instructor)) {
            	String result = instructor.getName();
                System.out.println("remove var result = " + result);
            }
        });
        
        BiFunction<Integer,Integer,Integer> sum3 = (x,  y) -> x + y;
        System.out.println("remove var sum3.apply(2,4) = " + sum3.apply(2,4));
        
    	System.out.println("exit instance public runVarWithLamda");
    }
    
    public static void runHttpClient(String[] args){
    	System.out.println("run instance public runHttpClient");
    	System.out.println("============================");
    	
    	//-----------------------------------
    	//java11 has new http client setup support http1.1 http2
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        	.uri(URI.create("https://www.yahoo.com"))
            .build();
        
        HttpResponse<String> response = null;
        
        System.out.println("response ============================");
        try {
            /*
            using the synchronous method HttpResponse.send, once execution reaches client.send(...), 
            the main application thread will pause and block every other action until the server 
            returns a response (or the request fails)
            */
			response = client.send(
			     request,
			     HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("response.body() = " + response.body());
        System.out.println("response ============================");
        
        //------------------------------------
        //Asynch
        System.out.println("response2 ============================");
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
        	.uri(URI.create("https://www.ldapsoft.com"))
            .build();
        try {
        	/*
        	sendAsync(HttpRequest, BodyHandler) sends the request and receives the response 
        	asynchronously. The sendAsync method returns immediately with a CompletableFuture<HttpResponse>. 
        	The CompletableFuture completes when the response becomes available. The returned 
        	CompletableFuture can be combined in different ways to declare dependencies among 
        	several asynchronous tasks.
        	///////////////////////////////////////////
        	1>The java.net.http.HttpClient has a non-blocking version of its send method: sendAsync.
        	2>Instead of returning a string response directly, this method returns a CompletableFuture. 
        	The CompletableFuture class is a wrapper for the result of an operation that may or may 
        	not have finished yet.
        	3>The request may or may not have been completed, soCompletableFuture could be in a loading 
        	state. If we want to do something with the result once the request completes, we can 
        	register a listener function to the Future with Future.thenApply
        	*/
        	CompletableFuture<HttpResponse<String>> response2 = client.sendAsync(
            	request, 													//request
            	HttpResponse.BodyHandlers.ofString()	// responseBodyHandler
            );
        	
        	/*
        	The method thenApply works similarly to Java Stream's map: Chain a function that will 
        	be executed with the result of the previous operation as a parameter; that function 
        	then transforms the data and returns something else (in our example, the function 
        	transforms the HttpResponse to a String).
        	//////////////////////////////
        	1>thenApply on the other hand takes a Function and returns a CF carrying the 
        	return value of the function.
        	2>thenAccept takes a Consumer and returns a T=Void CF
        	3>thenAccept() is an instance method in Java. It is used when we don’t want to return 
        	anything from our callback function and only want to run some code once a Future 
        	completes
        	*/
            response2
            	.thenApply(HttpResponse::body) //.thenApply((httpResponse) -> httpResponse.body());
            	.thenApply(String::toLowerCase) //map/transform to lower case
            	.thenAccept(System.out::println)
            	.get(); //you have to call this for html to spit out to console...
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("response2 ============================");
        
        
        System.out.println("response3 ============================");
    	CompletableFuture<HttpResponse<String>> response3 = client.sendAsync(
            	request, 													//request
            	HttpResponse.BodyHandlers.ofString()	// responseBodyHandler
            );    
        response3
    	.thenApply(HttpResponse::body) //.thenApply((httpResponse) -> httpResponse.body());
    	.thenApply(String::toLowerCase) //map/transform to lower case
    	.thenAccept(System.out::println);
        System.out.println("response3 ============================");
        
    	System.out.println("============================");
    	System.out.println("exit instance public runHttpClient");
    }
    
    public static void printString (String toPrint){
        System.out.println("toPrint = " + toPrint);
    }
    
    public void runSection25(String[] args) {
		System.out.println("run instance public runSection25");
		
		//java12 introduced switch statement
		/*
		OLD switch statement
		1>verbose and difficult to write
		2>if you dont define break, then code will fall thru
		/////////////////////
		NEW switch expression
		1>break not needed
		*/
		
		//OLD
        String month="JANUARY";
        String quarter;
        switch (month){
            case "JANUARY":
                quarter = "FIRST QUARTER";
                break;
            case "FEBURARY":
                quarter = "FIRST QUARTER";
                break;
            case "MARCH":
                quarter = "FIRST QUARTER";
                break;
            case "APRIL":
                quarter = "SECOND QUARTER";
                break;
            case "MAY":
                quarter = "SECOND QUARTER";
                break;
            case "JUNE":
                quarter = "SECOND QUARTER";
            case "JULY":
                quarter = "THIRD QUARTER";
                break;
            case "AUGUST":
                quarter = "THIRD QUARTER";
                break;
            case "SEPTEMBER":
                quarter = "THIRD QUARTER";
                break;
            case "OCTOBER":
                quarter = "FORTH QUARTER";
                break;
            case "NOVEMBER":
                quarter = "FOURTH QUARTER";
                break;
            case "DECEMBER":
                quarter = "FOURTH QUARTER";
                break;
            default:
                quarter= "UNKNOWN QUARTER";
                break;
        }
        System.out.println("switch statement quarter = " + quarter);

        //NEW
        //The yield keyword lets us exit a switch expression by returning a value 
        //that becomes the value of the switch expression.
        quarter = switch(month) {
        	case "JANUARY", "FEBURARY", "MARCH" ->{
        		var isLeapYear = LocalDate.now().isLeapYear();
        		yield (isLeapYear ? "FIRST QUARTER - LEAP YEAR": "FIRST QUARTER");
        	}
        	/*
        	yield = return
        	1>(x) -> { return x; } // lambda expression 
        	2>(x) -> { yield x; } // switch expression
        	3>x -> x // works for lambda and switch...
        	 */
        	case "APRIL", "MAY", "JUNE" -> { yield "SECOND QUARTER"; }
        	case "JULY", "AUGUST", "SEPTEMBER" -> "THIRD QUARTER";
        	case "OCTOBER", "NOVEMBER", "DECEMBER" -> "FOURTH QUARTER"; 
        	default -> "UNKNOWN QUARTER"; 
        };
        System.out.println("switch expression quarter = " + quarter);
        
        //-------------------------------------------------
        //more switch
        switch (month){
            //lambda
        	case "JANUARY", "FEBURARY", "MARCH" -> { System.out.println("FIRST QUARTER"); }
        	//short hand lambda
        	case "APRIL", "MAY", "JUNE" -> System.out.println("SECOND QUARTER");
        	case "JULY", "AUGUST", "SEPTEMBER"-> { System.out.println("THIRD QUARTER"); }
        	case "OCTOBER", "NOVEMBER", "DECEMBER" -> System.out.println("FOURTH QUARTER");
        	default -> System.out.println("UNKNOWN QUARTER");
        };
        
        //------------------------------------------------
        // multiline text block with """
        String st1 = """
                Hello World
                Using 
                text blocks !""";
        System.out.println("st1 = " + st1);
    
        String phrase = """
                    { 
                    employee : "Mike", 
                    employeeId: 10001; 
                    employeeType: FT
                    }
                    """;
        System.out.println("phrase = " + phrase);
    
        String html = """
                <html>
                    <body>
                    <p>"Java, Programming"</p>
                    </body>
                </html>
                """;
        System.out.println("html = " + html);
    
        String st2 = st1.concat("This is my first text block");
        System.out.println("st2 = " + st2);
    
		System.out.println("exit instance public runSection25");
    }
}
