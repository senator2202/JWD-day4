package by.kharitonov.task4_1.validator;

import by.kharitonov.task4_1.entity.IntegerArray;

public class ArrayFillerValidator {
    private static final int MIN_VALUE = -999;
    private static final int MAX_VALUE = 999;
    private static final int RESTRICTED_VALUE = 0;

    public boolean validateFillParameters(IntegerArray array, int bound) {
        return array != null && validateInRange(bound);
    }

    public boolean validateFillParameters(IntegerArray array, String fileName) {
        return array != null && fileName != null && !fileName.isEmpty();
    }

    public boolean validateInRange(int bound) {
        return bound >= MIN_VALUE && bound <= MAX_VALUE &&
                bound != RESTRICTED_VALUE;
    }
}
