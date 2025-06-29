package simple.packages.spring.core;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.*;

@Component
@ImportResource(value = "annotation-based-config.xml")
public class ComplexComponent {

	@Autowired
	SimpleComponent simpleComponent = null;
	
	@Autowired
	SimpleComponent simpleComponent2 = null;
	
	public ComplexComponent() {
		super();
	}
	
	public SimpleComponent getSimpleComponent() {
		return simpleComponent;
	}

	public void setSimpleComponent(SimpleComponent simpleComponent) {
		this.simpleComponent = simpleComponent;
	}
	
	public SimpleComponent getSimpleComponent2() {
		return simpleComponent2;
	}

	public void setSimpleComponent2(SimpleComponent simpleComponent2) {
		this.simpleComponent2 = simpleComponent2;
	}

	@Override
	public String toString() {
		return "ComplexComponent [simpleComponent=" + simpleComponent + ", simpleComponent2=" + simpleComponent2 + "]";
	}

}
