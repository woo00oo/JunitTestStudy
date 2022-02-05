package test.junit.thejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //하나의 클래스 -> 하나의 인스턴스
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //테스트 순서 지정
class StudyTest {

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

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
    @DisplayName("조건에 따라 테스트 실행하기")
    void create_new_study2() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println("test_env = " + test_env);
        assumeTrue("LOCAL".equalsIgnoreCase(test_env)); //대소문자를 구분하지 않고 비교

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("조건에 따라 테스트 실행하기2")
    void create_new_study3() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println("test_env = " + test_env);

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

        assumingThat("GLOBAL".equalsIgnoreCase(test_env), () -> {
            Study actual = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });
    }

    /**
     * @EnabledOnOs -> 특정 OS
     * @DisabledOnOs
     * @EnabledOnJre -> 특정 자바 버전
     * @EnabledIfEnvironmentVariable -> 특정 환견병수
     */

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

    @FastTest
    void create_fast_study() {

    }

    @DisplayName("스터디 만들기")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetition}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있습니다"})
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있습니다"})
    @NullAndEmptySource
    void parameterizedTest2(String message) {
        System.out.println(message);
    }
}