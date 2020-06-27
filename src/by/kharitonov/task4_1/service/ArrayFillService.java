package by.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.exception.IntegerArrayException;
import by.kharitonov.task4_1.parser.ArrayParser;
import by.kharitonov.task4_1.validator.ArrayFillerValidator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class ArrayFillService {
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

    public boolean consoleFill(IntegerArray array)
            throws IntegerArrayException {
        ArrayFillerValidator validator = new ArrayFillerValidator();
        if (array == null) {
            return false;
        }
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < array.getLength(); i++) {
            int value;
            try {
                value = scanner.nextInt();
            } catch (InputMismatchException e) {
                throw new IntegerArrayException("All values must be integer!");
            } catch (NoSuchElementException e) {
                throw new IntegerArrayException("Not enough data!");
            }
            if (validator.validateInRange(value)) {
                array.setElement(i, value);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean fileFill(IntegerArray array, String fileName)
            throws IntegerArrayException {
        ArrayFillerValidator validator = new ArrayFillerValidator();
        if (!validator.validateFillParameters(array, fileName)) {
            return false;
        }
        Path path = Paths.get(fileName);
        Scanner scanner;
        try {
            scanner = new Scanner(path);
        } catch (IOException e) {
            throw new IntegerArrayException("Wrong file name!");
        }
        ArrayParser parser = new ArrayParser();
        String[] data = new String[2];
        IntegerArray filledArray;
        scanner.useDelimiter(System.getProperty("line.separator"));
        try {
            data[0] = scanner.next();
            data[1] = scanner.next();
        } catch (Exception e) {
            throw new IntegerArrayException("Not enough data!");
        } finally {
            scanner.close();
        }
        filledArray = parser.parseArray(data);
        copy(filledArray, array);
        return true;
    }

    private void copy(IntegerArray source, IntegerArray destination) {
        int length = Math.min(source.getLength(), destination.getLength());
        for (int i = 0; i < length; i++) {
            int value = source.getElement(i);
            destination.setElement(i, value);
        }
    }
}
