package by.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.entity.SortDirection;
import by.kharitonov.task4_1.validator.ArrayValidator;

import java.util.Random;

public class ArraySortService {
    public void selectionSort(IntegerArray array, SortDirection direction) {
        ArrayValidator validator = new ArrayValidator();
        if (!validator.validateSortParameters(array, direction)) {
            return;
        }
        boolean sortFlag = (direction == SortDirection.UP);
        int temp;
        for (int i = 0; i < array.getLength(); i++) {
            temp = i;
            for (int j = i + 1; j < array.getLength(); j++) {
                if (array.getElement(j) <
                        array.getElement(temp) == sortFlag) {
                    temp = j;
                }
            }
            if (temp != i) {
                swap(array, i, temp);
            }
        }
    }

    private void swap(IntegerArray array, int index1, int index2) {
        int value1 = array.getElement(index1);
        int value2 = array.getElement(index2);
        array.setElement(index1, value2);
        array.setElement(index2, value1);
    }

    public void bubbleSort(IntegerArray array, SortDirection direction) {
        ArrayValidator validator = new ArrayValidator();
        if (!validator.validateSortParameters(array, direction)) {
            return;
        }
        boolean sortFlag = (direction == SortDirection.UP);
        boolean cycleFlag;
        do {
            cycleFlag = false;
            for (int i = 0; i < array.getLength() - 1; i++) {
                if (array.getElement(i + 1) <
                        array.getElement(i) == sortFlag) {
                    swap(array, i, i + 1);
                    cycleFlag = true;
                }
            }
        } while (cycleFlag);
    }

    public void stupidSort(IntegerArray array, SortDirection direction) {
        ArrayValidator validator = new ArrayValidator();
        if (!validator.validateSortParameters(array, direction)) {
            return;
        }
        while (!validator.validateIsSorted(array, direction)) {
            shuffle(array);
        }
    }

    private void shuffle(IntegerArray array) {
        Random random = new Random();
        for (int i = 0; i < array.getLength(); i++) {
            int j = random.nextInt(array.getLength());
            swap(array, i, j);
        }
    }
}
