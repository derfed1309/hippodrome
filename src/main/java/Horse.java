import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import static java.util.Objects.isNull;

public class Horse {
    private static final Logger logger = (Logger) LogManager.getLogger(Horse.class);

    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            //Если в конструктор вместо имени передан null, то перед пробросом исключения, добавить в лог запись вида: 2022-05-31 17:34:59,483 ERROR Horse: Name is null
            logger.error("Horse: Name is null");
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            logger.error("Horse: Name is blank");
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            logger.error("Horse: Speed is negative");
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            logger.error("Horse: Distance is negative");
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;
        //Создание Horse, имя [Лобстер], скорость [2.8]
        logger.debug("Horse: Создание Horse, имя {}, скорость {}",name,speed);
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
