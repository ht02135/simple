package simple.packages.stream;

public class ConcreteBuilding implements Building {
	
    // implementing the abstract method 
    // of building interface
	@Override
    public void heightDisplay()
    {
        System.out.println("ConcreteBuilding.heightDisplay height is 5");
    }
	
	//CANT remove public
	//CANT reduce visibility of  inherited method
	/*
    void heightDisplay2()
    {
        System.out.println("height is 5");
    }
    */
	//interface method is public and abstract by defult need to overrid
	@Override
    public void heightDisplay2() 
    {
        System.out.println("ConcreteBuilding.heightDisplay2 height is 5");
    }
	
    public static void main(String[] args)
    {
    	Building concreteBuilding = new ConcreteBuilding();
        concreteBuilding.heightDisplay();
        concreteBuilding.heightDisplay2();
        
        //calling default method
        concreteBuilding.defaultWidthDisplay2();
        
        //calling static method
        Building.staticWidthDisplay();
        
    }	
}
