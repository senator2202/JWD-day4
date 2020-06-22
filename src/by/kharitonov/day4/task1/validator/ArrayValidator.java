package by.kharitonov.day4.task1.validator;

import by.kharitonov.day4.task1.entity.IntegerArray;
import by.kharitonov.day4.task1.entity.SortDirection;

public class ArrayValidator {
    public boolean validateIndexes(IntegerArray array, int... indexes) {
        if (array == null || indexes == null) {
            return false;
        }
        for (int index : indexes) {
            if (index < 0 || index >= array.getLength()) {
                return false;
            }
        }
        return true;
    }

    public boolean validateIsSorted(IntegerArray array,
                                    SortDirection direction) {
        if (array == null || direction == null) {
            return false;
        }
        boolean sortFlag = (direction != SortDirection.UP);
        for (int i = 0; i < array.getLength() - 1; i++) {
            if (array.getElement(i).get() <
                    array.getElement(i + 1).get() == sortFlag) {
                return false;
            }
        }
        return true;
    }

    public boolean validateBinarySearchValue(IntegerArray array,
                                             int searchValue) {
        if (array == null) {
            return false;
        }
        return (searchValue >= array.getFirst() &&
                searchValue <= array.getLast());
    }
}
