package simple.packages.spring.core;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

//Use @Component to declare a class as a Spring bean.
@Component
@ImportResource(value = "annotation-based-config.xml")
@PropertySource(value = "annotation-based-config.properties")
public class SimpleComponent {
	
	String nameString = null;
	
	public SimpleComponent() {
		super();
	}

	public String getNameString() {
		return nameString;
	}
	
	@Value( "${SimpleComponent.name}" )
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	@Override
	public String toString() {
		return "SimpleComponent [nameString=" + nameString + "]";
	}

}
