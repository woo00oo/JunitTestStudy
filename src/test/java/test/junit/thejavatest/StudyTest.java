package test.junit.thejavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {

        assertThrows(IllegalArgumentException.class, () -> new Study(-10));

        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        Study study = new Study(-10);
        assertNotNull(study);

        assertAll(
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT+ "여야 한다."), //테스트가 성공, 실패 유무 상관 없이 문자열 연산은 항상 실행된다.
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT+ "여야 한다."), //테스트가 실패시에만 문자열 연산이 이루어진다.
                () -> assertTrue(study.getLimit() > 0 , "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );
    }

    @Test
    @Disabled //테스트를 실행하지 않음
    void create_new_study_again() {
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