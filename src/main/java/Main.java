import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        logger.info("Начало скачек. Количество участников: {}", horses.size());


        for (int i = 0; i < 10; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println(winnerName + " wins!");
        logger.info("Окончание скачек. Победитель: {}", winnerName);
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(100));
    }
}
