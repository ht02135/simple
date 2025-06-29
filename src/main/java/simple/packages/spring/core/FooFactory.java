package simple.packages.spring.core;

import org.springframework.context.annotation.*;

public class FooFactory {
    private static FooFactory instance = null;
    
    private FooFactory() {
    	super();
    }

    public static synchronized FooFactory getInstance() {
        if (instance == null) { instance = new FooFactory(); }
        return instance;
    }
    
	public Foo getFoo() {
		AnnotationConfigApplicationContext ctx = new 
			AnnotationConfigApplicationContext(FooConfiguration.class);
		/*
		org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 
		'simple.packages.spring.core.Foo' available: expected single matching bean but found 
		2: getFoo,getSomeFoo 
		*/
		//Foo foo = ctx.getBean(Foo.class); <-- not work with multi @Bean in @Configuration
		Foo foo = (Foo) ctx.getBean("getFoo");
		foo.setId(0);
		return foo;
	}
	
	public Foo getFoo(Integer id) {
		Foo foo = getFoo();
		foo.setId(id);
		return foo;
	}
	
	public Foo getSomeFoo() {
		AnnotationConfigApplicationContext ctx = new 
			AnnotationConfigApplicationContext(FooConfiguration.class);
		Foo foo = (Foo) ctx.getBean("getSomeFoo");
		foo.setId(0);
		return foo;
	}
}
