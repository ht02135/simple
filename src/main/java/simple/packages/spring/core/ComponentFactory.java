package simple.packages.spring.core;

import org.springframework.context.annotation.*;

public class ComponentFactory {
	
	private static ComponentFactory instance = null;
	 
    private ComponentFactory() {
    	super();
    	// comment for now, bc dont have bookService in config.xml
    	/*
        ApplicationContext applicationContext = 
            	new ClassPathXmlApplicationContext("classpath:resources/config.xml");
        instance = (BookService) applicationContext.getBean("bookService");
        */
    }

    public static synchronized ComponentFactory getInstance() {
        if (instance == null) { instance = new ComponentFactory(); }
        return instance;
    }
    
    public ComplexComponent getComplexComponent() {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("simple.packages.spring.core");
        context.refresh();
         
        ComplexComponent complex = context.getBean(ComplexComponent.class);
        System.out.println("ComponentFactory before call setNameString complex.toString()="+
        	complex.toString());
        complex.getSimpleComponent().setNameString("simple in complex");
        complex.getSimpleComponent2().setNameString("simple2 in complex");
        System.out.println("ComponentFactory after call setNameString complex.toString()="+
            	complex.toString());
        return complex;
    }
    
    public SimpleComponent getSimpleComponent() {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("simple.packages.spring.core");
        context.refresh();
         
        SimpleComponent complex = context.getBean(SimpleComponent.class);
        System.out.println("ComponentFactory before call setNameString complex.toString()="+
            	complex.toString());
        complex.setNameString("simple");
        System.out.println("ComponentFactory after call setNameString complex.toString()="+
            	complex.toString());
        return complex;
    }
    
    public Employee getEmployee() {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("simple.packages.spring.core");
        context.refresh();
         
        Employee employee = context.getBean(Employee.class);
        System.out.println("ComponentFactory before call setName employee="+employee);
        employee.setName("ComponentFactory setName call");
        employee.setDepartment("ComponentFactory setName call");
        System.out.println("ComponentFactory after call setName employee="+employee);
        return employee;
    }
    
    public ClassKLM getClassKLM() {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("simple.packages.spring.core");
        context.refresh();
         
        ClassKLM klm = context.getBean(ClassKLM.class);
        System.out.println("ComponentFactory klm="+klm);
        return klm;
    }
}
