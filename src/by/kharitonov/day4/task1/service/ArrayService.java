package by.kharitonov.day4.task1.service;

import by.kharitonov.day4.task1.entity.Array;
import by.kharitonov.day4.task1.entity.SortDirection;

public class ArrayService {
    public void selectionSort(Array array, SortDirection direction) {
        boolean sortFlag = (direction == SortDirection.UP) ? true : false;
        int temp;
        for (int i = 0; i < array.getLength(); i++) {
            temp = i;
            for (int j = i + 1; j < array.getLength(); j++) {
                if (!(array.getElement(j).get() <
                        array.getElement(temp).get() ^ sortFlag)) {//!XOR
                    temp = j;
                }
            }
            if (temp != i) {
                swap(array, i, temp);
            }
        }
    }

    private void swap(Array array, int index1, int index2) {
        int value1 = array.getElement(index1).get();
        int value2 = array.getElement(index2).get();
        array.setElement(index1, value2);
        array.setElement(index2, value1);
    }

    public void bubbleSort(Array array, SortDirection direction) {
        boolean sortFlag = (direction == SortDirection.UP) ? false : true;
        boolean cycleFlag;
        do {
            cycleFlag = false;
            for (int i = 0; i < array.getLength() - 1; i++) {
                if (!(array.getElement(i).get() <
                        array.getElement(i + 1).get() ^ sortFlag)) {
                    swap(array, i, i + 1);
                    cycleFlag = true;
                }
            }
        } while (cycleFlag);
    }

    public void quickSort(Array array, SortDirection direction, int low,
                          int high) {
        if (array.getLength() == 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        int middleIndex = low + (high - low) / 2;
        int middleValue = array.getElement(middleIndex).get();
        int i = low;
        int j = high;
        while (i <= j) {
            while (array.getElement(i).get() < middleValue) {
                i++;
            }

            while (array.getElement(j).get() > middleValue) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, direction, low, j);
        if (high > i)
            quickSort(array, direction, i, high);
    }
}
