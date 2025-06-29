package simple.packages.spring.core;

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

import org.springframework.beans.*;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

import simple.packages.*;
import simple.packages.datetime.*;
import simple.packages.stream.*;
import simple.packages.feature.*;
import simple.packages.spring.core.*;

import java.sql.*;

public class SpringCore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exec(args);
	}

	static public void exec(String[] args) {
		System.out.println("run static public exec");
		SpringCore me = new SpringCore();

		//https://javatechonline.com/spring-core-tutorials/
		try {
			me.runChapter01(args);		
		} catch (RuntimeException re) { //unchecked RuntimeException
        	System.out.println("runchecked exception RuntimeException re="+re);
        	System.out.println("runchecked exception RuntimeException re.getSuppressed()="+re.getSuppressed());
        	re.printStackTrace();
        } catch (Exception e) { //checked Exception
        	System.out.println("checked exception Exception re="+e);
        	System.out.println("checked exception Exception e.getSuppressed()="+e.getSuppressed());
        	e.printStackTrace();
        }
		
	} // exec

    public void runChapter01(String[] args) throws BeansException {
		System.out.println("run instance public runChapter01");
		
		/*
		Spring Container has the following responsibilities:
		1) Find and Scan Spring Beans
		2) Create Objects for the beans that it found after scanning
		3) Link/Inject objects if they have some relations such as HAS-A
		4) Destroy the objects
		////////////////////////
		types Of Spring Containers? two Java Interfaces,
		1>BeanFactory interface provides a most commonly used implementation class which is XmlBeanFactory.
		2>ApplicationContext
		  It supports all the forms of configuration: XML, Java and Annotation
		(A) ClassPathXmlApplicationContext : We should use it when configuration file is 
		at project’s class path.
		(B) FileSystemXmlApplicationContext : We should use it when configuration file is 
		at server’s file system.
		(C) AnnotationConfigApplicationContext : We should use it when we are using 
		annotation-based configuration.
		*/
		
		//STEPS
		/*
		1>Step#1: Create a Simple Maven Project in Eclipse/STS
			1) Open your Eclipse IDE, then go to new -> project-> Maven Project and click on next.
			2) Click on ‘Create a simple Project (Skip archetype selection)’
			3) Provide the values of Group Id & Artifact Id. Group Id uniquely identifies your 
			project among all. So it may be something like com.dev.spring.example. Artifact Id 
			may be something like project name, so here we can put artifact id as 
			SpringFirstExample.
			4) Click on ‘Finish’. You will get a new maven based project.
		//////////////////////
		2>Step#2: Modify pom.xml
		<dependency>
     		<groupId>org.springframework</groupId>
     		<artifactId>spring-core</artifactId>
     		<version>4.1.4.RELEASE</version>
		</dependency>

		<dependency>
    		<groupId>org.springframework</groupId>
    		<artifactId>spring-context</artifactId>
    		<version>4.1.4.RELEASE</version>
		</dependency>
        //////////////////////////////
        3>Step#3: Create a Pojo Class 
        src/main/java	Application/Library sources
        src/main/resources	Application/Library resources
        //////////////////
        if you get he constructor ClassPathXmlApplicationContext(String) refers to the missing type BeansException
        then Project >> update maven project
		*/
		
		//Spring Container (BeanFactory/ApplicationContext)  provides getBean() method to get bean from it.
		Book book = BookService.getBook();
		System.out.println("book="+book);
		//book=Book [Id=94568, name=Spring In Action]
		
		/*
		Spring Bean is a class in spring, which follows the rules provided by the Spring 
		Container. Below are the rules that Spring Container expects from developers to 
		follow while creating a class that will act as a bean:
		/////////////////////
		1) Class must be public.
		2) Variables are recommended to be private.
		3) Methods should be public.
		4) Class should be under a package. It can be either under a base package or its sub package.
		5) Provide any one of the two combinations given below. There is no harm in providing both of 
		them, but any one will be sufficient.
			(A) Default constructor with  setter & getter methods
			(B) Parameterized constructor
		6) Class can extend or implement only those classes or interfaces which are part of Spring 
		Framework’s API. We can identify classes and interfaces from Spring Framework API by 
		finding their package names. They come under package org.springframework.
		7) Class can override 3 methods from Object class: toString(), hashcode() and equals().
		8) Class can implement java.io.Serializable interface.
		9) Class can use Core Java Annotations, Spring Framework Annotations, and some Integration 
		Annotations. Core Java annotations comes under package java.lang, such as Deprecated, 
		Override, SafeVarargs, SupressWarnings. Integration annotations are used in very rare cases.
		*/
		
		/*
		Spring container requires two inputs from a programmer to create an object: 
		Spring Bean & Spring Configuration. As aforementioned, 
		1>Spring Bean is a specific type of class that follows rules provided by the Spring Container.
		  translate to CLASS
		2>Spring configuration generally contains the name of the bean, linking details with other 
		beans and some other information if any. Spring Configuration is also known as Configuration 
		metadata.
		  translate to config.xml
		/////////////////////////////
		///Spring Configuration can be in any of the three forms:
		1) XML-based Configuration
		2) Java-based Configuration
		3) Annotation-based Configuration
		*/
		
		//----------------------------------------------------
		//XML-based Configuration via config.xml
		/*
		<bean name="book" class="simple.packages.spring.core.Book">
  			<property name="id" value="94568"/>
  		<property name="name" value="Spring In Action"/>
		</bean
		*/
		Book book2 = BookService.getBook();
		System.out.println("book2="+book2);
		
		//----------------------------------------------------
		/*
		Annotation-based Configuration
		By default, annotation wiring is not turned on in the Spring framework. So, in order 
		to use annotation-based configuration, we need to enable it before using it by 
		specifying <context:annotation-config/> tag as shown below. Once this tag is 
		configured, we can start annotating our code.
		<beans>
   			<context:annotation-config/>
   			<!-- bean definitions go here -->
		</beans>
		///////////////////////
		@Component
		public class Employee{ 
		*/
		
		//----------------------------------
		/*
		Java-based Configuration
		1>Spring 3.0, a pure-Java configuration container was provided
		2>The key features in Spring Framework’s new Java-configuration support are @Configuration 
		annotated classes and @Bean annotated methods. Using @Configuration annotation represents 
		that Spring container will use it as a source of Beans definitions. Using the @Bean tells 
		Spring container that method will return an object which should be registered as a bean 
		in Spring application context
		A>@Bean annotation plays the same role as the <bean/> element.
		B>@Configuration classes allow define inter-bean dependencies by simply calling other 
		@Bean methods in the same class.
		///////////////////////
		1>@Component in a java class is to tell to spring: Register me automatically at the startup in your context so any other class could use me using dependency injection: @Autowire
		2>@Configuration and @Bean are used to add manually a java class to the spring context at the startup.
		if Foo.java is inside of a library, is not compatible with spring, is an old library or just a simple instantation is not enough, @Configuration and @Bean comes to rescue
		*/
		
		//--------------------------------------------
		/*
		How to create your first Spring Application using Annotation based Configuration?
		*/
		FooFactory fooFactory = FooFactory.getInstance();
		Foo foo = fooFactory.getFoo();
		System.out.println("foo="+foo);
		
		Foo foo2 = fooFactory.getFoo(2);
		System.out.println("foo2="+foo2);
		
		Foo someFoo = fooFactory.getSomeFoo();
		System.out.println("someFoo="+someFoo);
		
		/*
		@Bean
		@Bean is an annotation based configuration and hence is used in @Configuration based class.
		This is an explicit way of defining a bean and is also used on the methods defined in 
		configuration class.
		////////////////////
		@Component
		This is used in classes which you create in your app. This will only work after you enable 
		component scan on the package that contains your class.
		With component scan, Spring framework will scan the classpath and add all the classes that 
		are marked with a @Component annotation.
		-This is also called the automated way of binding and discovering your bean.
		////////////////
		@Component
		Purpose: Marks a class as a Spring-managed component. This annotation indicates that 
		the class is a candidate for component scanning and will be instantiated and managed 
		by the Spring container.
		////////////////
		@Autowired
		Purpose: Indicates that a field, constructor, or method should be automatically wired 
		by the Spring container with a bean of the appropriate type. It is used for 
		dependency injection.
		/////////////////
	    Use @Component to declare a class as a Spring bean.
		Use @Autowired to inject dependencies into Spring beans.
		1>SimpleComponent
		2>ComplexComponent autowire 2 SimpleComponent
		3>ComponentFactory get ComplexComponent and SimpleComponent
		*/
		ComponentFactory componentFactory = ComponentFactory.getInstance();
		ComplexComponent complex = componentFactory.getComplexComponent();
		System.out.println("complex="+complex);
		
		SimpleComponent simple = componentFactory.getSimpleComponent();
		System.out.println("simple="+simple);
		
		Employee employee = componentFactory.getEmployee();
		System.out.println("employee="+employee);
		
		ClassKLM klm = componentFactory.getClassKLM();
		System.out.println("klm="+klm);
		
		
		System.out.println("exit instance public runChapter01");
    }
}
