package test.junit.chapter1.page2;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import test.junit.chapter1.page1.Calculator;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ParameterizedTest {

    private double expected;
    private double valueOne;
    private double valueTwo;

    @Parameters
    public static Collection<Integer[]> getTestParameters(){
        return Arrays.asList(new Integer[][]{
                {2, 1, 1},
                {3, 2, 1},
                {4, 3, 1}
        });
    }

    public ParameterizedTest(double expected, double valueOne, double valueTwo) {
        this.expected = expected;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    @Test
    public void sum(){
        Calculator calc = new Calculator();
        Assertions.assertEquals(expected, calc.add(valueOne, valueTwo), 0);
    }
}

/*

    파라미터화 테스트 러너를 사용하려면 몇 가지 조건을 만족 시켜야 한다.

    1. 테스트 클래스에는 반드시 @RunWith 어노테이션을 부여해야 하며, 그 파라미터로는 Parameterized 클래스를 사용한다.

    2. 테스트에 사용될 값을 인스턴스 변수로 선언하고, @Parameters라 표시된 메서드가 하나 필요하다.

    3. 이 메서드의 시그니처는 반드시 Collection이어야 하며, 어떠한 파라미터도 입력 받아서는 안된다.

    4. Collection의 원소는 배열(array)이고, 길이는 모두 같아야 한다. 또한 그 길이는 유일한 public 생성자가 받는 파라미터의 수와도 일치해야 한다.


    위 코드의 흐름

    1. JUnit은 먼저 getTestParameters를 호출해 컬렉션 객체를 얻는다.

    2. 다음으로 컬렉션에 저장된 배열의 수만큼 순환한다.

    3. 각 루프에서, JUnit은 유일한 pulbic 생성자를 찾는다. 이때 만약 public 생성자가 두 개 이상이라면 AssertionError를 던진다.

    4. 이제 찾은 생성자에 배열의 원소를 파라미터로 넣어 호출한다.




 */
