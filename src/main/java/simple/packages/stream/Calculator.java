package simple.packages.stream;

/*
If we have Interface with only one abstract method in it, it is by default Functional 
Interface. Can anyone please explain what additional advantage @FunctionalInterface 
annotation brings? 
It's not mandatory to mark the functional interface with @FunctionalInterface annotation, 
the compiler doesn't throw any error. But it’s good practice to use @FunctionalInterface 
annotation to avoid the addition of extra methods accidentally.
*/
@FunctionalInterface
public interface Calculator {
    
    //public int sum(int num1, int num2);
	//abstract sum method
	int sum(int num1, int num2); // removed public, by method in interface is public by default

    //default method which is subtract
    default int subtract (int num1, int num2){
        return num1 - num2;
    }

    //static method
    static int multiply (int num1, int num2){
        return num1 * num2;
    }
}
