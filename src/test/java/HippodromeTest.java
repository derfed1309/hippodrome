import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void whenHorsesIsNull_ThenIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void whenHorsesIsEmpty_ThenIllegalArgumentException() {
        List<Horse> emptyList = List.of();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(emptyList)
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorsesTest() {
        List<Horse> listHorses = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            listHorses.add(new Horse("Конь-огонь " + i, 2, 3));
        }
        Hippodrome hippodrome = new Hippodrome(listHorses);
        assertEquals(listHorses, hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> listHorses = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            listHorses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(listHorses);
        hippodrome.move();
        for (int i = 0; i < 50; i++) {
            verify(listHorses.get(i)).move();
        }
    }

    @Test
    public void getWinnerTest() {
        List<Horse> listHorses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listHorses.add(new Horse("Конь-огонь " + i, 2, i));
        }
        Hippodrome hippodrome = new Hippodrome(listHorses);
        assertEquals(listHorses.get(4), hippodrome.getWinner());
    }
}


















