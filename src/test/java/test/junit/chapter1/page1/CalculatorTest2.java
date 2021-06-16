package test.junit.chapter1.page1;

public class CalculatorTest2 {

    private int nbErrors = 0;

    public void testAdd(){
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);
        if (result != 60){
            throw new IllegalStateException("Bad result: " + result);
        }
    }

    public static void main(String[] args){
        CalculatorTest2 test2 = new CalculatorTest2();
        try {
            test2.testAdd();
        }catch (Throwable e){
            test2.nbErrors++;
            e.printStackTrace();
        }
        if (test2.nbErrors > 0){
            throw new IllegalStateException("There were " + test2.nbErrors + " error(s)");
        }
    }
}
