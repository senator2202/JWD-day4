package by.kharitonov.task4_1.parser;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.exception.ArrayException;

public class ArrayParser {
    public IntegerArray parseArray(String[] data) throws ArrayException {
        IntegerArray array;
        int length = 0;
        try {
            length = Integer.parseInt(data[0]);
        } catch (NumberFormatException e) {
            throw new ArrayException("Wrong parse parameters!");
        }
        array = new IntegerArray(length);
        String[] values = data[1].split("\\s");
        for (int i = 0; i < values.length; i++) {
            int temp = 0;
            try {
                temp = Integer.parseInt(values[i]);
            } catch (NumberFormatException e) {
                throw new ArrayException("Wrong parse parameters!");
            }
            array.setElement(i, temp);
        }
        return array;
    }
}
