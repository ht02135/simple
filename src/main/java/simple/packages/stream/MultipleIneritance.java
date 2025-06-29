package simple.packages.stream;

public class MultipleIneritance implements InterfaceA, InterfaceB, InterfaceC {

	/*
	overrid InterfaceA.sumA to proof
	to show how java8 resolve multi inheritance overrident method
	
	method selector order
	1>implemented class first
    2>the sub interface that extends the interface
    3>then ori interface assume that is say default method
	*/
    public void sumA(int num1, int num2){
        System.out.println("MultipleIneritance.sumA=" + (num1 + num2));
    }
    
    public void sumB(int num1, int num2){
        System.out.println("MultipleIneritance.sumB=" + (num1 + num2));
    }
    
}
