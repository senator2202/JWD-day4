package by.kharitonov.day4.task1.provider;

import by.kharitonov.day4.task1.entity.IntegerArray;
import by.kharitonov.day4.task1.exception.ArrayException;
import by.kharitonov.day4.task1.parser.ArrayParser;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class ArrayFiller {
    private static final int MAX_BOUND = 999;

    public boolean randomFill(@NotNull IntegerArray array, int bound) {
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

    public boolean consoleFill(@NotNull IntegerArray array, int bound,
                               InputStream in) {
        if (!inRange(bound)) {
            return false;
        }
        Scanner scanner = new Scanner(in);
        for (int i = 0; i < array.getLength(); i++) {
            int value = scanner.nextInt();
            if (value <= bound) {
                array.setElement(i, value);
            }
        }
        return true;
    }

    public void fileFill(@NotNull IntegerArray array,
                         @NotNull String fileName)
            throws ArrayException {
        Path path = Paths.get(fileName);
        try (Scanner scanner = new Scanner(path)) {
            ArrayParser parser = new ArrayParser();
            String[] data = new String[2];
            IntegerArray filledArray;
            scanner.useDelimiter(System.getProperty("line.separator"));
            data[0] = scanner.next();
            data[1] = scanner.next();
            filledArray = parser.parseArray(data);
            copy(filledArray, array);
        } catch (IOException e) {
            throw new ArrayException("Wrong file name!");
        }
    }

    private void copy(IntegerArray source, IntegerArray destination)
            throws ArrayException {
        if (source.getLength() != destination.getLength()) {
            throw new ArrayException("Different array sizes!");
        }
        for (int i = 0; i < source.getLength(); i++) {
            int value = source.getElement(i).get();
            destination.setElement(i, value);
        }
    }
}
