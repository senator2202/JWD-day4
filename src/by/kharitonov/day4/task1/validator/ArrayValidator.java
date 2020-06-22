package by.kharitonov.day4.task1.validator;

import by.kharitonov.day4.task1.entity.IntegerArray;
import by.kharitonov.day4.task1.entity.SortDirection;
import org.jetbrains.annotations.NotNull;

public class ArrayValidator {
    public boolean validateIndexes(@NotNull IntegerArray array,
                                   @NotNull int... indexes) {
        for (int index : indexes) {
            if (index < 0 || index >= array.getLength()) {
                return false;
            }
        }
        return true;
    }

    public boolean validateIsSorted(@NotNull IntegerArray array,
                                    @NotNull SortDirection direction) {
        boolean sortFlag = (direction != SortDirection.UP);
        for (int i = 0; i < array.getLength() - 1; i++) {
            if (array.getElement(i).get() <
                    array.getElement(i + 1).get() == sortFlag) {
                return false;
            }
        }
        return true;
    }

    public boolean validateBinarySearchValue(@NotNull IntegerArray array,
                                             int searchValue) {
        return (searchValue >= array.getFirst() &&
                searchValue <= array.getLast());
    }
}
