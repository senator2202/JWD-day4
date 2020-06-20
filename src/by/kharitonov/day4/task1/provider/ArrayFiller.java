package by.kharitonov.day4.task1.provider;

import by.kharitonov.day4.task1.entity.Array;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Scanner;

public class ArrayFiller {
    private static final int MAX_BOUND = 999;

    public boolean randomFill(@NotNull Array array, int bound) {
        if (!inRange(bound)) {
            return false;
        }
        Random random = new Random();
        for (int i = 0; i < array.getLength(); i++) {
            int randomValue = random.nextInt(bound);
            boolean positiveFlag = random.nextBoolean();
            randomValue = positiveFlag ? randomValue : -randomValue;
            array.setElement(i, randomValue);
        }
        return true;
    }

    private boolean inRange(int bound) {
        return bound > 0 && bound <= MAX_BOUND;
    }

    public boolean consoleFill(@NotNull Array array, int bound) {
        if (!inRange(bound)) {
            return false;
        }
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < array.getLength(); i++) {
            int value = scanner.nextInt();
            array.setElement(i, value);
        }
        return true;
    }
}
