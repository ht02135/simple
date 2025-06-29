package simple.packages.spring.core;

import org.springframework.stereotype.*;

@Component
public class ClassXYZ implements InterfacePQR {
	public void display() 
    {
        System.out.println("Hi... I am ClassXYZ");
    }

	@Override
	public String toString() {
		return "ClassXYZ [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
