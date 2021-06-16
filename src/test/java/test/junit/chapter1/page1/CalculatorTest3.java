package test.junit.chapter1.page1;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest3 {

    @Test
    public void testAdd(){
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);
        assertEquals(60, result, 0);
    }

}