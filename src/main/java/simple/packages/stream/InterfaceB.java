package simple.packages.stream;

public interface InterfaceB extends InterfaceA{
	
	//inherit and override InterfaceA.sum
    default void sumA(int num1, int num2){
        System.out.println("InterfaceB.sumA=" + (num1 + num2));
    }
    
    default void sumB(int num1, int num2){
        System.out.println("InterfaceB.sumB=" + (num1 + num2));
    }


}
