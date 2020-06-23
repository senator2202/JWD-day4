package by.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import by.kharitonov.task4_1.entity.SortDirection;
import by.kharitonov.task4_1.exception.ArrayException;
import by.kharitonov.task4_1.validator.ArrayValidator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArrayService {
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

    public int minValue(@NotNull IntegerArray array) {
        return array.minValue();
    }

    public int maxValue(@NotNull IntegerArray array) {
        return array.maxValue();
    }

    public List<Integer> simpleNumbers(@NotNull IntegerArray array) {
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

    public List<Integer> fibonacciNumbers(IntegerArray array) {
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
        fibonacciSequence.add(sign);
        for (int i = 2; sum < Math.abs(number); i++) {
            sum = Math.abs(fibonacciSequence.get(i - 2) +
                    fibonacciSequence.get(i - 1));
            fibonacciSequence.add(sum * sign);
        }
        return fibonacciSequence;
    }

    public List<Integer> threeDifferentDigitNumbers(IntegerArray array) {
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
