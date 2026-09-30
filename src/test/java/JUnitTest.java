import org.junit.jupiter.api.*;

public class JUnitTest {
    @DisplayName("1 + 2는 3이다")
    @Test
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;
        Assertions.assertEquals(sum, a + b); // 값이 같은지 확인

        System.out.println("1+2=3");
    }

    @DisplayName("1 + 3는 3이다.")
    @Test
    public void junitFailedTest() {
        int a = 1;
        int b = 3;
        int sum = 3;
        Assertions.assertEquals(sum, a + b);
        System.out.println("1+3=3");
    }

    @BeforeEach
    public void prepare(){
        System.out.println("测试准备");
    }

    @BeforeAll
    public static void prepareAll(){
        System.out.println("测试最初准备");
    }

    @AfterEach
    public void clean(){
        System.out.println("测试后。。。");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("测试最终结束");
    }
}