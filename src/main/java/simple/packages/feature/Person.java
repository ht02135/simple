package simple.packages.feature;

import java.util.Objects;

public class Person implements Greeting {
	
	/*
	final is a modifier that is used for class, method, and variable also. When a 
	variable is declared with the final keyword, its value can’t be modified
	//////////////////
	that explain why auto generate getter/setter only generate gettes
	*/
    private final String name;
    private final String address;
    
    public Person() {
        this.name = "UNKOWN";
        this.address = "UNKOWN";
    }

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Person)) {
            return false;
        } else {
            Person other = (Person) obj;
            return Objects.equals(name, other.name)
              && Objects.equals(address, other.address);
        }
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", address=" + address + "]";
    }

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

}
