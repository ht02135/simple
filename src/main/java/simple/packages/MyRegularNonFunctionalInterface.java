package simple.packages;

//this CANT BE functional interface for lambda
public interface MyRegularNonFunctionalInterface {
    // Abstract method
    public void myMethod();

    public void myMethod2();

    // Default methods (optional)
    default void defaultMethod() {
        System.out.println("run defaultMethod");
    }

    default void defaultMethod2() {
        System.out.println("run defaultMethod2");
    }

    // Static methods (optional)
    static void staticMethod() {
        System.out.println("run staticMethod");
    }

    static void staticMethod2() {
        System.out.println("run staticMethod2");
    }
}
