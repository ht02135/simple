package simple.packages.spring.core;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/*
Furthermore, in order to make a class as a bean, we need to apply an annotation on 
the top of the class. There are various annotations that we can apply on top of 
the class, such as @Component, @Controller, @Service, @Repository etc. Most of 
the time we apply @Component annotation as it is the basic annotation
*/
@Component
public class Employee {

	private Long id; 
    private String name;
    private String department;
    
    public Employee() {
    	super();
    }
    
    public Employee(Long id, String name, String department) {
    	super();
		this.id = id;
		this.name = name;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	@Value("#{new Long('1')}")
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Value("name value from @Value")
	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	@Value("department value from @Value")
	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department=" + department + "]";
	}
    
}
