package simple.packages.stream;

import java.io.*;
import java.math.*;
import java.security.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

import simple.packages.*;
import simple.packages.stream.*;

public class InterfaceExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exec(args);
	}

	static public void exec(String[] args) {
		System.out.println("run static public exec");
		InterfaceExample me = new InterfaceExample();
		
		me.runSection13(args);
		
	} // exec
	
    public void runSection13(String[] args) {
		System.out.println("run instance public runSection13");
		
		/*
		1>Preferences > General > Search > Reuse editors to show matches
		*/
		
		/*
		all method of interface are public and abstract by default
		java8 allow default and static method
		static method similar to default method except CANT overrid in CLASS
		*/
    	Building concreteBuilding = new ConcreteBuilding();
        concreteBuilding.heightDisplay();
        concreteBuilding.heightDisplay2();
        
        //calling default method
        concreteBuilding.defaultWidthDisplay2();
        
        //calling static method
        Building.staticWidthDisplay();
        
        //--------------------------------------
        //Calculator example
        
        ConcreteCalculator concreteCalculator = new ConcreteCalculator();
        
        //calling abstract method directly
        System.out.println("concreteCalculator.sum=" + concreteCalculator.sum(2,4));

        //override/implement abstract method via lambda
        Calculator calculator = (num1, num2) -> num1 + num2 + 100; //lambda will get extra 100
        System.out.println("lambda implemented Calculator.sum=" + calculator.sum(3,2));
        
        //default method
        System.out.println("default Subtract: " + concreteCalculator.subtract(4,2));
        
        //static method
        System.out.println("static Multiply: " + Calculator.multiply(4,2));
        
        //-----------------------------------------
        //multi inheritance
        
        /*
		method selector order
		1>implemented class first
    	2>the sub interface that extends the interface
    	3>then ori interface assume that is say default method
        */
        /*
        MultipleIneritance.sumA=12
		InterfaceB.sumB=6
		InterfaceC.sumC=3
        */
        MultipleIneritance multipleIneritance = new MultipleIneritance();
        multipleIneritance.sumA(4,8);  // resolve to child
        multipleIneritance.sumB(2,4);
        multipleIneritance.sumC(1,2);
        
        //MultipleIneritanceDebug.sumA12
        MultipleIneritanceDebug multipleIneritanceDebug = new MultipleIneritanceDebug();
        multipleIneritanceDebug.sumA(4,8);  // resolve to child
		
		System.out.println("exit instance public runSection13");
    }
    
}
