package simple.packages.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ClassKLM {
	
	/*
	you have ClassABC and ClassXYZ implements same InterfacePQR,
	you will run to throws NoUniqueBeanDefinitionException
	//////////////////
	The @Qualifier annotation in Spring Boot is used to resolve ambiguity when 
	you have multiple beans of the same type but want to inject a specific one
	//////////////////
	you need to use qualifier to pick specific one....
	*/
	
	@Autowired
	@Qualifier("classABC")
    InterfacePQR pqr;

	@Override
	public String toString() {
		return "ClassKLM [pqr=" + pqr + "]";
	}
}
