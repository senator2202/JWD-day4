package by.kharitonov.day4.task1.provider;

import by.kharitonov.day4.task1.entity.IntegerArray;
import by.kharitonov.day4.task1.exception.ArrayException;
import by.kharitonov.day4.task1.parser.ArrayParser;
import by.kharitonov.day4.task1.validator.ArrayFillerValidator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ArrayFiller {
    public boolean randomFill(IntegerArray array, int bound) {
        ArrayFillerValidator validator = new ArrayFillerValidator();
        if (!validator.validateFillParameters(array, bound)) {
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

    public boolean consoleFill(IntegerArray array, int bound,
                               InputStream in) throws ArrayException {
        ArrayFillerValidator validator = new ArrayFillerValidator();
        if (!validator.validateFillParameters(array, bound, in)) {
            return false;
        }
        Scanner scanner = new Scanner(in);
        for (int i = 0; i < array.getLength(); i++) {
            int value = 0;
            try {
                value = scanner.nextInt();
            } catch (InputMismatchException e) {
                throw new ArrayException("All values must be integer!");
            }
            if (value <= bound) {
                array.setElement(i, value);
            }
        }
        return true;
    }

    public boolean fileFill(IntegerArray array, String fileName)
            throws ArrayException {
        ArrayFillerValidator validator = new ArrayFillerValidator();
        if (!validator.validateFillParameters(array, fileName)) {
            return false;
        }
        Path path = Paths.get(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(path);
        } catch (IOException e) {
            throw new ArrayException("Wrong file name!");
        }
        ArrayParser parser = new ArrayParser();
        String[] data = new String[2];
        IntegerArray filledArray;
        scanner.useDelimiter(System.getProperty("line.separator"));
        try {
            data[0] = scanner.next();
            data[1] = scanner.next();
        } catch (Exception e) {
            throw new ArrayException("Not enough data!");
        }
        filledArray = parser.parseArray(data);
        copy(filledArray, array);
        return true;
    }

    private void copy(IntegerArray source, IntegerArray destination) {
        int length = Math.min(source.getLength(), destination.getLength());
        for (int i = 0; i < length; i++) {
            int value = source.getElement(i).get();
            destination.setElement(i, value);
        }
    }
}
