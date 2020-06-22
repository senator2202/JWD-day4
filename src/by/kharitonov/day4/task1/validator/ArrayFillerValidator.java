package by.kharitonov.day4.task1.validator;

import by.kharitonov.day4.task1.entity.IntegerArray;

import java.io.InputStream;

public class ArrayFillerValidator {
    private static final int MAX_BOUND = 999;

    public boolean validateFillParameters(IntegerArray array, int bound) {
        return array != null && inRange(bound);
    }

    public boolean validateFillParameters(IntegerArray array, int bound,
                                          InputStream in) {
        return array != null && in != null && inRange(bound);
    }

    public boolean validateFillParameters(IntegerArray array, String fileName) {
        return array != null && fileName != null && !fileName.isEmpty();
    }

    private boolean inRange(int bound) {
        return bound > 0 && bound <= MAX_BOUND;
    }
}
