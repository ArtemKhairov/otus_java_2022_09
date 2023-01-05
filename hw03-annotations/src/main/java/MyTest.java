import annotations.*;

public class MyTest {

    @Before
    public void preset() {
        System.out.println("method: preset");
    }

    @Before
    @Test
    public void presetAdditional() {
        System.out.println("method: presetAdditional");
    }

    @Test
    public void firstTest() {
        System.out.println("method: firstTest");
    }

    @Test
    public void secondTest() {
        System.out.println("method: secondTest");
        throw new RuntimeException("RuntimeException secondTest");
    }

    @Test
    public void thirdTest() {
        System.out.println("method: thirdTest");
        throw new RuntimeException("RuntimeException thirdTest");
    }

    @After
    public void clean() {
        System.out.println("method: clean");
    }
}