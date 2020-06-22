package by.kharitonov.day4.task1.parser;

import by.kharitonov.day4.task1.entity.IntegerArray;
import by.kharitonov.day4.task1.exception.ArrayException;

public class ArrayParser {
    public IntegerArray parseArray(String[] data) throws ArrayException {
        try {
            IntegerArray array;
            int length = Integer.parseInt(data[0]);
            array = new IntegerArray(length);
            String[] values = data[1].split("\\s");
            for (int i = 0; i < values.length; i++) {
                int temp = Integer.parseInt(values[i]);
                array.setElement(i, temp);
            }
            return array;
        } catch (NumberFormatException e) {
            throw new ArrayException("Wrong parse parameters!");
        }
    }
}
