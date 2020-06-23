package by.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.entity.SortDirection;
import by.kharitonov.task4_1.exception.ArrayException;
import by.kharitonov.task4_1.validator.ArrayValidator;

public class ArraySearchService {
    public int binarySearch(IntegerArray array, int searchValue)
            throws ArrayException {
        ArrayValidator validator = new ArrayValidator();
        if (!validator.validateIsSorted(array, SortDirection.UP)) {
            throw new ArrayException("IntegerArray is not sorted!");
        }
        if (!validator.validateBinarySearchValue(array, searchValue)) {
            return -1;
        }
        int firstIndex = 0;
        int lastIndex = array.getLength() - 1;
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            int middleValue = array.getElement(middleIndex);
            if (middleValue == searchValue) {
                return middleIndex;
            } else if (middleValue < searchValue) {
                firstIndex = middleIndex + 1;
            } else {
                lastIndex = middleIndex - 1;
            }
        }
        return -1;
    }
}
