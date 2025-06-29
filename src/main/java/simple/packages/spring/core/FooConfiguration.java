package simple.packages.spring.core;

import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.support.*;

import simple.packages.spring.core.*;

/*
Spring @Configuration annotation is part of the spring core framework. Spring 
Configuration annotation indicates that the class has @Bean definition methods. 
So Spring container can process the class and generate Spring Beans to be used 
in the application.
*/
@Configuration
@ImportResource(value = "annotation-based-config.xml")
public class FooConfiguration {
	
	/*
	i have a factory method
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(FooConfiguration.class);
	Foo foo = ctx.getBean(Foo.class);
	*/
	@Bean
	public Foo getFoo() {
		return  new Foo();
	}

	/*
	What is @Bean in a spring Framework?
	@Bean, on the other hand, is a method-level annotation that is used to explicitly declare 
	a bean in a Java configuration class. When a method is annotated with @Bean, it serves as 
	a factory method for creating a bean instance.
	*/
	@Bean(name={"getSomeFoo"})
	public Foo getSomeFoo() {
		return  new Foo();
	}
}
