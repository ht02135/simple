package simple.packages.spring.core;

//dont auto wire to use @Configuration on other class
public class Foo {
	
	private Integer id;
	
	public Foo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Foo [id=" + id + "]";
	}
	
}
