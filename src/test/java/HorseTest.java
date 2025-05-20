import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    //Проверить, что при передаче в конструктор первым параметром null, будет выброшено IllegalArgumentException. Для этого нужно воспользоваться методом assertThrows;
    @Test
    public void whenNameIsNull_ThenThrowsIllegalArgumentException() {
        String str = new String();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(str, 1)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    //Проверить, что при передаче в конструктор первым параметром null, выброшенное исключение будет содержать сообщение "Name cannot be null.". Для этого нужно получить сообщение из перехваченного исключения и воспользоваться методом assertEquals;


    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", " \t\n\r"})
    public void test1(String strings) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(strings, 1, 2)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    // Проверить, что при передаче в конструктор вторым параметром отрицательного числа, будет выброшено IllegalArgumentException;
    //  Проверить, что при передаче в конструктор вторым параметром отрицательного числа, выброшенное исключение будет содержать сообщение "Speed cannot be negative.";
    @Test
    public void test4() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Мурка", -1, 2)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void test5() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Васька", 1, -2)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    //Проверить, что при передаче в конструктор третьим параметром отрицательного числа, будет выброшено IllegalArgumentException;
    //Проверить, что при передаче в конструктор третьим параметром отрицательного числа, выброшенное исключение будет содержать сообщение "Distance cannot be negative."

    //Проверить, что метод возвращает строку, которая была передана первым параметром в конструктор;
    @Test
    public void testGetName() {
        String testName = "TestName";
        Horse horse = new Horse(testName, 300, 150);
        assertEquals(testName, horse.getName());

    }
    //метод getSpeed
    //Проверить, что метод возвращает число, которое было передано вторым параметром в конструктор;
    @Test
    public void testGetSpeed() {
        double testSpeed = 33.5;
        Horse horse = new Horse("Конь", testSpeed,100);
        assertEquals(testSpeed,horse.getSpeed());
    }

    //метод getDistance
    //Проверить, что метод возвращает число, которое было передано третьим параметром в конструктор;
    //Проверить, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами;
    @Test
    public void testGetDistance() {
        double testDistance = 777;
        Horse horse = new Horse("Конь", 32,testDistance);
        assertEquals(testDistance,horse.getDistance());
    }
    @Test
    public void testGetDistanceZiro() {
        Horse horse = new Horse("Конь", 24);
        assertEquals(0,horse.getDistance());
    }
    //метод move
    //Проверить, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9. Для этого нужно использовать MockedStatic и его метод verify;
    //Проверить, что метод присваивает дистанции значение высчитанное по формуле: distance + speed * getRandomDouble(0.2, 0.9). Для этого нужно замокать getRandomDouble, чтобы он возвращал определенные значения, которые нужно задать параметризовав тест.
    @Test
    public void testMove() {
        try (MockedStatic<Horse> mocked = Mockito.mockStatic(Horse.class)) {
            mocked.when(() -> Horse.move())
                    .thenReturn(100);


    }
    @Test
    void whenProcessPaymentCalled_thenStaticMethodInvoked() {
        // 1. Создаем мок статического класса
        try (MockedStatic<PaymentProcessor> mocked = Mockito.mockStatic(PaymentProcessor.class)) {

            // 2. Настраиваем мок
            mocked.when(() -> PaymentProcessor.processPayment(anyString(), anyDouble()))
                    .thenReturn(true);

            // 3. Вызываем тестируемый код
            boolean result = PaymentProcessor.processPayment("order-123", 100.0);

            // 4. Проверяем результат
            assertTrue(result);

            // 5. Проверяем вызов статического метода
            mocked.verify(() -> PaymentProcessor.processPayment(
                    eq("order-123"),
                    eq(100.0)
            ));

            // Можно проверить количество вызовов
            mocked.verify(
                    () -> PaymentProcessor.processPayment(anyString(), anyDouble()),
                    times(1)
            );
        }

        // После закрытия блока try статический мок сбрасывается
    }
}
}
