package by.kharitonov.day4.task1.service;

import by.kharitonov.day4.task1.entity.Array;
import by.kharitonov.day4.task1.entity.SortDirection;
import by.kharitonov.day4.task1.exception.ArrayException;
import by.kharitonov.day4.task1.validator.ArrayValidador;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayService {
    public void selectionSort(@NotNull Array array,
                              @NotNull SortDirection direction) {
        boolean sortFlag = (direction == SortDirection.UP);
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

    private boolean swap(@NotNull Array array, int index1, int index2) {
        if (!new ArrayValidador().validateIndexes(array, index1, index2)) {
            return false;
        }
        int value1 = array.getElement(index1).get();
        int value2 = array.getElement(index2).get();
        array.setElement(index1, value2);
        array.setElement(index2, value1);
        return true;
    }

    public void bubbleSort(@NotNull Array array,
                           @NotNull SortDirection direction) {
        boolean sortFlag = (direction == SortDirection.UP);
        boolean cycleFlag;
        do {
            cycleFlag = false;
            for (int i = 0; i < array.getLength() - 1; i++) {
                if (!(array.getElement(i+1).get() <
                        array.getElement(i ).get() ^ sortFlag)) {
                    swap(array, i, i + 1);
                    cycleFlag = true;
                }
            }
        } while (cycleFlag);
    }

    public void bogoSort(@NotNull Array array,
                         @NotNull SortDirection direction) {
        ArrayValidador validador = new ArrayValidador();
        while (!validador.validateIsSorted(array, direction)) {
            shuffle(array);
        }
    }

    private void shuffle(@NotNull Array array) {
        Random random = new Random();
        for (int i = 0; i < array.getLength(); i++) {
            int j = random.nextInt(array.getLength());
            swap(array, i, j);
        }
    }

    public int binarySearch(@NotNull Array array, int searchValue)
            throws ArrayException {
        ArrayValidador validador = new ArrayValidador();
        if (!validador.validateIsSorted(array, SortDirection.UP)) {
            throw new ArrayException("Array is not sorted!");
        }
        if (!validador.validateBinarySeachValue(array, searchValue)) {
            return -1;
        }
        int firstIndex = 0;
        int lastIndex = array.getLength() - 1;
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            int middleValue = array.getElement(middleIndex).get();
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

    public int minValue(@NotNull Array array) {
        return array.minValue();
    }

    public int maxValue(@NotNull Array array) {
        return array.maxValue();
    }

    public List<Integer> simpleNumbers(@NotNull Array array) {
        ArrayList<Integer> simpleList = new ArrayList<>();
        for (int i = 0; i < array.getLength(); i++) {
            int element = array.getElement(i).get();
            if (isSimple(element)) {
                simpleList.add(element);
            }
        }
        return simpleList;
    }

    private boolean isSimple(int number) {
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
                if (count > 2) {
                    return false;
                }
            }
        }
        return count == 2;
    }

    public List<Integer> fibonacciNumbers(Array array) {
        ArrayList<Integer> fibonacciList = new ArrayList<>();
        for (int i = 0; i < array.getLength(); i++) {
            Integer temp = array.getElement(i).get();
            if (isFibonacciNumber(temp)) {
                fibonacciList.add(temp);
            }
        }
        return fibonacciList;
    }

    private boolean isFibonacciNumber(int number) {
        ArrayList<Integer> fibonacciList = fibonacciSequence(number);
        return fibonacciList.indexOf(number) != -1;
    }

    private ArrayList<Integer> fibonacciSequence(int number) {
        ArrayList<Integer> fibonacciSequence = new ArrayList<>();
        int sum = 1;
        int sign = number > 0 ? 1 : -1;
        fibonacciSequence.add(0);
        fibonacciSequence.add(1 * sign);
        for (int i = 2; sum < Math.abs(number); i++) {
            sum = Math.abs(fibonacciSequence.get(i - 2) +
                    fibonacciSequence.get(i - 1));
            fibonacciSequence.add(sum * sign);
        }
        return fibonacciSequence;
    }

    public List<Integer> threeDifferentDigitNumbers(Array array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.getLength(); i++) {
            int value = array.getElement(i).get();
            if (threeDigitNumber(value) && differentDigitNumber(value)) {
                list.add(value);
            }
        }
        return list;
    }

    private boolean threeDigitNumber(int number) {
        if (number < 0) {
            number = -number;
        }
        return (number / 100 >= 1 && number / 100 < 10);
    }

    private boolean differentDigitNumber(int number) {
        if (number < 0) {
            number = -number;
        }
        char[] digits = Integer.toString(number).toCharArray();
        return (digits[0] != digits[1] &&
                digits[0] != digits[2] &&
                digits[1] != digits[2]);
    }
}
