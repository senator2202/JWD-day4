package by.kharitonov.day4.task1.parser;

import by.kharitonov.day4.task1.entity.Array;

public class ArrayParser {
    public Array parseArray(String[] data) {
        Array array;
        int length = Integer.parseInt(data[0]);
        array = new Array(length);
        String[] values = data[1].split("\\s");
        for (int i = 0; i < values.length; i++) {
            int temp = Integer.parseInt(values[i]);
            array.setElement(i, temp);
        }
        return array;
    }
}
