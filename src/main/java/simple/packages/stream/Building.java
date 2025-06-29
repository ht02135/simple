package simple.packages.stream;

public interface Building {
	//By default, all methods are public and abstract
	// abstract methods
    void heightDisplay();
    
    void heightDisplay2();
    
    //abstract method cant have body
    /*
    void widthDisplay()
    {
        System.out.pritnln("width is 1");
    }
    */
    
    // static method
    static void staticWidthDisplay()
    {
        System.out.println("Building.staticWidthDisplay width is 1");
    }
    
    // default method
    default void defaultWidthDisplay2()
    {
        System.out.println("Building.defaultWidthDisplay2 width is 1");
    }
}
