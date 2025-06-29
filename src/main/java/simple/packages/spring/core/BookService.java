package simple.packages.spring.core;

import org.springframework.beans.*;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

import simple.packages.spring.core.*;

public class BookService {

    private static BookService instance = null;
    
    private BookService() {
    	super();
    	// comment for now, bc dont have bookService in config.xml
    	/*
        ApplicationContext applicationContext = 
            	new ClassPathXmlApplicationContext("classpath:resources/config.xml");
        instance = (BookService) applicationContext.getBean("bookService");
        */
    }

    public static synchronized BookService getInstance() {
        if (instance == null) { instance = new BookService(); }
        return instance;
    }

    public static synchronized Book getBook() throws BeansException {
    	//Maven, has standard directory for resources. Which is src/main/resources
        ApplicationContext applicationContext = 
            new ClassPathXmlApplicationContext("xml-based-config.xml");
        return (Book) applicationContext.getBean("book");
    }
    
}
