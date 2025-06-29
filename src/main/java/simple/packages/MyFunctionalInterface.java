package simple.packages;

//i think @FunctionalInterface is strictly for lambda
@FunctionalInterface
public interface MyFunctionalInterface {
    // Abstract method
    public void myMethod();

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