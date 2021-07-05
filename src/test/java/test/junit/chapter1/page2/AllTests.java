package test.junit.chapter1.page2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.junit.chapter1.page1.CalculatorTest;

@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {CalculatorTest.class})
public class AllTests {
}
