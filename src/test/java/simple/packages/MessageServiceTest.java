package simple.packages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
mvn clean install -DskipTests=true
*/

public class MessageServiceTest {

    @Test
    public void testOne(){
        System.out.println( "Test One - " + this );
    }

    @Test
    public void testTwo(){
        System.out.println( "Test Two - " + this );
    }

    @Test
    void testGet() {
        assertEquals("Hello JUnit 5", "Hello JUnit 5");
    }
    
    @Test
    void testGet2() {
        assertEquals("Hello JUnit 5", "Hello JUnit 4");
    }

}