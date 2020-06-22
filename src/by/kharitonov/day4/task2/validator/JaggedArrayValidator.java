package by.kharitonov.day4.task2.validator;

import by.kharitonov.day4.task2.entity.SortType;

public class JaggedArrayValidator {
    public boolean validateSortingParameters(int[][] jArray,
                                             SortType sortType) {
        if (jArray == null || sortType == null) {
            return false;
        }
        for (int i = 0; i < jArray.length; i++) {
            if (!validateRow(jArray, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateRow(int[][] jArray, int row) {
        return (jArray != null &&
                row >= 0 &&
                row < jArray.length &&
                jArray[row] != null);
    }
}
