import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    public void test1() {
        String str = "";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->   str.length()//new Horse(str, 1, 2)
        );
    }
}
