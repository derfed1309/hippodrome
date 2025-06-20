import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void whenNameIsNull_ThenThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", " \t\n\r"})
    public void whenNameIsBlank_ThenIllegalArgumentException(String strings) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(strings, 1, 2)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void whenSpeedIsNegative_ThenIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Мурка", -1, 2)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void whenDistanceIsNegative_ThenIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Васька", 1, -2)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameReturnsValidName() {
        String testName = "TestName";
        Horse horse = new Horse(testName, 300, 150);
        assertEquals(testName, horse.getName());

    }

    @Test
    public void getSpeedReturnsValidSpeed() {
        double testSpeed = 33.5;
        Horse horse = new Horse("Конь", testSpeed, 100);
        assertEquals(testSpeed, horse.getSpeed());
    }

    //метод getDistance
    //Проверить, что метод возвращает число, которое было передано третьим параметром в конструктор;
    //Проверить, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами;
    @Test
    public void getDistanceReturnValidDistance() {
        double testDistance = 777;
        Horse horse = new Horse("Конь", 32, testDistance);
        assertEquals(testDistance, horse.getDistance());
    }

    @Test
    public void whenDistanceZiro_ThenGetDistanceReturnZiro() {
        Horse horse = new Horse("Конь", 24);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveTest() {
        Horse horse = new Horse("Конь-огонь", 3, 5);
        double initialDistance = horse.getDistance();
        try (MockedStatic<Horse> mockedHors = Mockito.mockStatic(Horse.class)) {
            mockedHors.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mockedHors.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(initialDistance + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9), horse.getDistance());
        }
    }


}
