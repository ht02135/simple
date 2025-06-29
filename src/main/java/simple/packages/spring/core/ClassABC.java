package simple.packages.spring.core;

import org.springframework.stereotype.*;

@Component(value = "classABC")
public class ClassABC implements InterfacePQR {
	  public void display() {
		  System.out.println("Hi... I am ClassABC");  
	  }

	  @Override
	  public String toString() {
		return "ClassABC [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	  }
}
