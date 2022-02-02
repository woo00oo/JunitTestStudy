package test.junit.thejavatest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    void create() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }

    @Test
    @Disabled //테스트를 실행하지 않음
    void create1() {
        System.out.println("create1");
    }

    @BeforeAll //전체 테스트를 실행하기 전 딱 한번 실행됨
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll //전체 테스트를 실행한 후 딱 한번 실행됨
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach //전체 테스트 중 각 테스트를 실행하기 전 매번 실행됨
    void beforeEach() {
        System.out.println("after each");
    }

    @AfterEach //전체 테스트 중 각 테스트를 실행한 후 매번 실행됨
    void afterEach() {
        System.out.println("Before each");
    }

}