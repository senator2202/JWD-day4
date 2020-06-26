package by.kharitonov.task4_1.validator;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.type.SortDirection;

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
            if (array.getElement(i) <
                    array.getElement(i + 1) == sortFlag) {
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

    public boolean validateSortParameters(IntegerArray array,
                                          SortDirection direction) {
        return array != null && direction != null;
    }
}
