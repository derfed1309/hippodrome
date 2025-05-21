import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    //метод main
    //Проверить, что метод выполняется не дольше 22 секунд. Для этого воспользуйся аннотацией Timeout. После написания этого теста, отключи его (воспользуйся аннотацией Disabled). Так он не будет занимать время при запуске всех тестов, а при необходимости его можно будет запустить вручную.
    @Disabled
    @Test
    @Timeout(value = 22,unit = TimeUnit.SECONDS)
    public void mainTestTimeout() throws Exception {
            Main.main(new String[0]);
    }
}
