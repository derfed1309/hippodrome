import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @ParameterizedTest
    @ValueSource(str = {"","cc"})
    public void test1() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->   new Horse(str, 1, 2)
        );
    }
}
