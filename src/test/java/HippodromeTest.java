import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    //Проверить, что при передаче в конструктор null, будет выброшено IllegalArgumentException;
    //Проверить, что при передаче в конструктор null, выброшенное исключение будет содержать сообщение "Horses cannot be null.";

    @Test
    public void hippodromeNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    //Проверить, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException;
    //Проверить, что при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение "Horses cannot be empty.";
    @Test
    public void hippodromeisEmpty() {
        List<Horse> emptyList = List.of();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(emptyList)
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    //метод getHorses
    //Проверить, что метод возвращает список, который содержит те же объекты и в той же последовательности, что и список который был передан в конструктор. При создании объекта Hippodrome передай в конструктор список из 30 разных лошадей;
    @Test
    public void getHorsesTest() {
        List<Horse> listHorses = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            listHorses.add(new Horse("Конь-огонь " + i, 2, 3));
        }
        Hippodrome hippodrome = new Hippodrome(listHorses);
        assertEquals(listHorses, hippodrome.getHorses());
    }

    //метод move
    //Проверить, что метод вызывает метод move у всех лошадей. При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и воспользуйся методом verify.
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
    //метод getWinner
    //Проверить, что метод возвращает лошадь с самым большим значением distance.
    @Test
    public void getWinnerTest() {
        List<Horse> listHorses = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            listHorses.add(new Horse("Конь-огонь " + i, 2, i));
        }
        Hippodrome hippodrome = new Hippodrome(listHorses);
        assertEquals(listHorses.get(4), hippodrome.getWinner());
    }
}


















