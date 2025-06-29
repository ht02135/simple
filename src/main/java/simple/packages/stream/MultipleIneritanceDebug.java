package simple.packages.stream;

import simple.packages.stream.*;

/*
InterfaceA has sumA
InterfaceD also has InterfaceD
you will get
Duplicate default methods named sumA with the parameters (int, int) and (int, int) are 
inherited from the types InterfaceD and Interface
///////////////////////////
java8 force you to resolve multi-inheritance in SUBCLASS.... that is fine....
*/
public class MultipleIneritanceDebug implements InterfaceA, InterfaceD {

	//for above reason, you must override conflict via implement it in  CLASS
    public void sumA(int num1, int num2){
        System.out.println("MultipleIneritanceDebug.sumA=" + (num1 + num2));
    }
    
}
