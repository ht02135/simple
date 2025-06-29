package simple.packages.feature;

public interface MyInterface {
	default void publicMethod() {
		privateMethod(); // reference private
	}
	
	private void privateMethod() {
		System.out.println("Private method in interface.");
	}
}
