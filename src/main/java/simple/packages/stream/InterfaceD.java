package simple.packages.stream;

public interface InterfaceD {

	//hmn is this overrid InterfaceA.sumA
    default void sumA(int num1, int num2){
        System.out.println("InterfaceD.sumA=" + (num1 + num2));
    }
}
